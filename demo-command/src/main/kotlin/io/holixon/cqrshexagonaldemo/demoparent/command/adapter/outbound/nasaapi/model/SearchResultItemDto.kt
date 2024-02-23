package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.nasaapi.model

data class SearchResultItemDto(val data: List<DataItemDto>, val href: String, val links: List<LinkDto>)