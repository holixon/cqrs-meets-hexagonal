package io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.searchresult

import io.holixon.cqrshexagonaldemo.demoparent.command.domain.SearchResultItem

interface SearchResultOutPort {
    fun saveSearchResult(searchResultItem: SearchResultItem): SearchResultItem
}