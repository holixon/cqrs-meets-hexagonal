package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.mapper.CycleAvoidingMappingContext
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.mapper.NasaApiMapper
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.LinkDto
import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.nasaapi.NasaApiOutPort
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.Item
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.function.Consumer

@Service
class NasaApiOutAdapter @Autowired constructor(
    val restClient: ReactiveRestClient,
    val mapper: NasaApiMapper
) : NasaApiOutPort {

    companion object : KLogging()

    override fun findItemsBySearchTerm(searchTerm: String): Flux<Item> {
        return restClient.getSearchResults(searchTerm)
            .map { item ->
                val links = item.links;
                val uriList = links.stream().map(LinkDto::href).toList()
                logSearchResults(uriList, searchTerm)
                mapper.toDomainObject(item, CycleAvoidingMappingContext())
            };
    }

    protected fun logSearchResults(allLinks: Collection<String?>, searchTerm: String?) {
        allLinks.forEach(Consumer<String?> { link: String? ->
            logger.info(
                "{} - {}", searchTerm, link
            )
        })
    }
}