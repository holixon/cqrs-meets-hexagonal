package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.nasaapi.model

data class CollectionItemDto(
    val href: String,
    val items: List<SearchResultItemDto>,
    val links: List<LinkDto>,
    val metadata: MetaDataDto,
    val version: String
)
