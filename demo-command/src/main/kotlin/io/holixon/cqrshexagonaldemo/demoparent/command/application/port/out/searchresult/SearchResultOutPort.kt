package io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.searchresult

import io.holixon.cqrshexagonaldemo.demoparent.command.domain.SearchResultItem
import reactor.core.publisher.Mono

interface SearchResultOutPort {
    fun saveSearchResult(searchResultItem: SearchResultItem): Mono<SearchResultItem>
}