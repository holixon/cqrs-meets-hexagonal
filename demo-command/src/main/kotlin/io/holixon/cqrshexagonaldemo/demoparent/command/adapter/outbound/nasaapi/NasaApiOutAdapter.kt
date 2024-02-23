package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.nasaapi

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.nasaapi.mapper.CycleAvoidingMappingContext
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.nasaapi.mapper.NasaApiMapper
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.nasaapi.model.LinkDto
import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.outbound.nasaapi.NasaApiOutPort
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.SearchResultItem
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class NasaApiOutAdapter @Autowired constructor(
    val restClient: RestClient,
    val mapper: NasaApiMapper
) : NasaApiOutPort {

    companion object : KLogging()

    override fun findItemsBySearchTerm(searchTerm: String): List<SearchResultItem> {
        return restClient.getSearchResults(searchTerm)
            .stream()
            .map { item ->
                val links = item.links;
                val uriList = links.stream().map(LinkDto::href).toList()
                item.data[0].links = links
                logSearchResults(uriList, searchTerm)
                mapper.toDomainObject(item, CycleAvoidingMappingContext())
            }
            .toList();
    }

    protected fun logSearchResults(allLinks: Collection<String?>, searchTerm: String?) {
        allLinks.forEach(Consumer<String?> { link: String? ->
            logger.info(
                "{} - {}", searchTerm, link
            )
        })
    }
}