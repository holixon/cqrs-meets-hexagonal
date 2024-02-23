package io.holixon.cqrshexagonaldemo.demoparent.command.application.port.outbound.nasaapi

import io.holixon.cqrshexagonaldemo.demoparent.command.domain.SearchResultItem

fun interface NasaApiOutPort {
    fun findItemsBySearchTerm(searchTerm: String): List<SearchResultItem>
}