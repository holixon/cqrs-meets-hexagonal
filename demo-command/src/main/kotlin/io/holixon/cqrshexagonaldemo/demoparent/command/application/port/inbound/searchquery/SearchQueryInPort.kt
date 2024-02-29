package io.holixon.cqrshexagonaldemo.demoparent.command.application.port.inbound.searchquery

import io.holixon.cqrshexagonaldemo.demoparent.command.domain.DataItem

interface SearchQueryInPort {
    fun search(searchTerm: String): MutableList<DataItem>
}