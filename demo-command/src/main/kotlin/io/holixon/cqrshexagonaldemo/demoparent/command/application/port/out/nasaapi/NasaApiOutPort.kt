package io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.nasaapi

import io.holixon.cqrshexagonaldemo.demoparent.command.domain.NasaPictureData
import reactor.core.publisher.Flux

fun interface NasaApiOutPort {
    fun callNasaApi(searchTerm: String): Flux<NasaPictureData>
}