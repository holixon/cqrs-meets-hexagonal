package io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.nasaapi

import io.holixon.cqrshexagonaldemo.demoparent.command.domain.SearchResultItem
import reactor.core.publisher.Flux

fun interface NasaApiOutPort {
    fun findItemsBySearchTerm(searchTerm: String): List<SearchResultItem>
}