package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.ItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.LinkDto
import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.nasaapi.NasaApiOutPort
import io.holixon.cqrshexagonaldemo.demoparent.command.config.ReactiveProperties
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.NasaPictureData
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.function.Consumer

@Service
class NasaApiOutAdapter @Autowired constructor(
    val restClient: ReactiveRestClient, val properties: ReactiveProperties
) : NasaApiOutPort {

    companion object : KLogging()


    override fun callNasaApi(searchTerm: String): Flux<NasaPictureData> {
        return restClient.getSearchResults(searchTerm).map { item -> buildDataItemEntity(item, searchTerm) };
    }

    protected fun buildDataItemEntity(item: ItemDto, searchTerm: String?): NasaPictureData {
        val dataItem = item.data.get(0)
        val uriList = item.links.stream().map(LinkDto::href).toList()
        logSearchResults(uriList, searchTerm)
        return NasaPictureData(dataItem.nasaId, dataItem.title, uriList)
    }

    protected fun logSearchResults(allLinks: Collection<String?>, searchTerm: String?) {
        allLinks.forEach(Consumer<String?> { link: String? ->
            logger.info(
                "{} - {}", searchTerm, link
            )
        })
    }
}