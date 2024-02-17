package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model

data class CollectionItemDto(
    val href: String,
    val items: List<ItemDto>,
    val links: List<LinkDto>,
    val metadata: MetaDataDto,
    val version: String
)
