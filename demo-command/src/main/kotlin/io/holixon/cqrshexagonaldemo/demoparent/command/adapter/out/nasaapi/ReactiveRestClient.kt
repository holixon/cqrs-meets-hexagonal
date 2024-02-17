package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.CollectionItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.ItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.SearchResultDto
import io.holixon.cqrshexagonaldemo.demoparent.command.config.ReactiveProperties
import mu.KLogging
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Flux
import java.net.URI
import java.net.URISyntaxException
import java.util.*
import java.util.function.Consumer
import java.util.function.Function

@Component
class ReactiveRestClient(webBuilder: WebClient.Builder, properties: ReactiveProperties) {

    private final val webBuilder: WebClient.Builder

    private final val properties: ReactiveProperties

    private final val webClient: WebClient

    companion object : KLogging()

    init {
        this.webBuilder = webBuilder
        this.properties = properties
        webClient = WebClient.builder().build()
    }

    fun getSearchResults(searchTerm: String?): Flux<ItemDto> {
        return getSearchResults(searchTerm, null, null)
            .map<ItemDto>(Function<ItemDto, ItemDto> { item: ItemDto ->
                check(!item.data.get(0).nasaId.contains("GS")) { "error" }
                item
            })
    }

    fun getSearchResults(searchTerm: String?, page: Long?, pageSize: Long?): Flux<ItemDto> {
        val queryParams: LinkedMultiValueMap<String?, String?> = LinkedMultiValueMap<String?, String?>()
        queryParams.add("q", searchTerm)
        val nullSafePage = Optional.ofNullable(page)
            .orElse(1L)
            .toString()
        queryParams.add("page", nullSafePage)
        val nullSafePageSize = Optional.ofNullable(pageSize)
            .orElse(12L)
            .toString()
        queryParams.add("page_size", nullSafePageSize)
        //queryParams.add("api_key", properties.getApiKey());
        return webClient
            .get()
            .uri(buildUri(queryParams))
            .headers(Consumer<HttpHeaders> { h: HttpHeaders -> h.addAll(prepareHttpHeaders()) })
            .retrieve()
            .bodyToFlux(SearchResultDto::class.java)
            .doOnNext { searchResult ->
                val collection: CollectionItemDto = searchResult.collection
                val size = collection.items.size
                logger.debug(
                    "response size: {} / page number {} / page size {} / has next page: {}",
                    size,
                    nullSafePage,
                    nullSafePageSize,
                    hasNextPage(collection)
                )
            }
            .flatMapIterable { searchResult -> searchResult.collection.items }
            .onBackpressureBuffer()
    }

    private fun hasNextPage(collection: CollectionItemDto): Boolean {
        return collection.links
            .stream()
            .anyMatch { l -> "next" == l.rel }
    }

    fun buildUri(queryParams: MultiValueMap<String?, String?>?): URI {
        return try {
            val uri = URI(
                properties.nasaApiSchema, properties.nasaApiHostname,
                properties.nasaApiSearchEndpoint, null
            )
            UriComponentsBuilder.fromUri(uri)
                .queryParams(queryParams)
                .build()
                .toUri()
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }
    }

    fun prepareHttpHeaders(): HttpHeaders {
        val httpHeaders = HttpHeaders()
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json")
        return httpHeaders
    }
}
