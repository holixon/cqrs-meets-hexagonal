package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.CollectionItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.SearchResultDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.SearchResultItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.config.ReactiveProperties
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * [API docs](https://images.nasa.gov/docs/images.nasa.gov_api_docs.pdf)
 */
@Component
class RestClient @Autowired constructor(
    restTemplateBuilder: RestTemplateBuilder,
    var properties: ReactiveProperties
) {
    private val restTemplate: RestTemplate

    companion object : KLogging()

    init {
        restTemplate = restTemplateBuilder.build()
    }

    fun getSearchResults(searchTerm: String?): List<SearchResultItemDto> {
        return getSearchResults(searchTerm, null, null)
    }

    fun getSearchResults(searchTerm: String?, page: Long?, pageSize: Long?): List<SearchResultItemDto> {
        val queryParams = LinkedMultiValueMap<String, String?>()
        queryParams.add("q", searchTerm)
        val nullSafePage = Optional.ofNullable(page)
            .orElse(1L)
            .toString()
        queryParams.add("page", nullSafePage)
        //queryParams.add("api_key", properties.getApiKey());
        val nullSafePageSize = Optional.ofNullable(pageSize)
            .orElse(12L)
            .toString()
        queryParams.add("page_size", nullSafePageSize)
        val apiResponse = restTemplate.exchange(
            buildUri(queryParams),
            HttpMethod.GET,
            HttpEntity<SearchResultDto>(prepareHttpHeaders()),
            SearchResultDto::class.java
        )
        val collection = apiResponse.body?.collection
        val size = collection?.items?.size
        logger.info(
            "response size: {} / page number {} / page size {} / has next page: {}",
            size,
            nullSafePage,
            nullSafePageSize,
            hasNextPage(collection)
        )
        return apiResponse.body?.collection?.items ?: ArrayList()
    }

    fun hasNextPage(collection: CollectionItemDto?): Boolean {
        return collection?.links?.stream()?.anyMatch { l -> "next" == l.rel } ?: false
    }

    fun buildUri(queryParams: MultiValueMap<String, String?>?): URI {
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
