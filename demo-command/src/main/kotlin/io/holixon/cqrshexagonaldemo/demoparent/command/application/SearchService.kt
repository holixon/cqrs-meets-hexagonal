package io.holixon.cqrshexagonaldemo.demoparent.command.application

import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.outbound.nasaapi.NasaApiOutPort
import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.outbound.searchresult.SearchResultOutPort
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service
import java.util.stream.Stream

@Service
open class SearchService @Autowired constructor(
    val nasaApi: NasaApiOutPort,
    val outPort: SearchResultOutPort
) {

    companion object : KLogging()

    @EventListener
    @Order(10)
    fun init(applicationReadyEvent: ApplicationReadyEvent) {
        findItems("Ceres")
    }

    fun findItems(searchTerm: String) {
        val toList = nasaApi.findItemsBySearchTerm(searchTerm)
            .stream()
            .map { searchResultItem -> outPort.saveSearchResult(searchResultItem) }
            .map { searchResultItem ->
                val dataItem = searchResultItem.data[0]
                logger.info("nasaId: {} - title: {}", dataItem.nasaId, dataItem.title)
                dataItem
            }
            .flatMap { item -> item.links?.stream() ?: Stream.empty() }
            .toList()
    };

}
