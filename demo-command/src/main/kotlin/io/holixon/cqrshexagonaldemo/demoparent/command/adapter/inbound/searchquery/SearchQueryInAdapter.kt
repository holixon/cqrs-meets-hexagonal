package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery.model.SearchResultItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery.service.gen.ImageSearchApi
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
open class SearchQueryInAdapter: ImageSearchApi {
    override fun apiV1SearchGet(q: String): ResponseEntity<List<SearchResultItemDto>> {
        TODO("Not yet implemented")
    }

}