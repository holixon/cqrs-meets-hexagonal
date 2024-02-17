package io.holixon.cqrshexagonaldemo.demoparent.command.application

import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.nasaapi.NasaApiOutPort
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class CommandService @Autowired constructor(val nasaApi: NasaApiOutPort) {

    companion object : KLogging()

    @Async
    @EventListener
    fun init(applicationReadyEvent: ApplicationReadyEvent) {
        callApi()
    }

    fun callApi() {

        nasaApi.callNasaApi("Ceres")
            .doOnNext { pictureData -> logger.info("nasaId: {} - title: {}", pictureData.nasaId, pictureData.title) }
            .flatMapIterable { pictureData -> pictureData.uri }
            .subscribe { uriString -> logger.info("uri {}", uriString) }
    };

}
