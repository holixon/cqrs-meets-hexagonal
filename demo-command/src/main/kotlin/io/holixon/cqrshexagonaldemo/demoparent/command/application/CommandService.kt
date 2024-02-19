package io.holixon.cqrshexagonaldemo.demoparent.command.application

import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.nasaapi.NasaApiOutPort
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
open class CommandService @Autowired constructor(val nasaApi: NasaApiOutPort) {

    companion object : KLogging()

    @Async
    @EventListener
    fun init(applicationReadyEvent: ApplicationReadyEvent) {
        findItems("Ceres")
    }

    fun findItems(searchTerm: String) {
        nasaApi.findItemsBySearchTerm(searchTerm)
            .doOnNext { item ->
                val dataItem = item.data[0]
                logger.info("nasaId: {} - title: {}", dataItem.nasaId, dataItem.title)
            }
            .flatMapIterable { item -> item.links }
            .subscribe { link -> logger.info("uri {}", link.href) }
    };

}
