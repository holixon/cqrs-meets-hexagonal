package io.holixon.cqrshexagonaldemo.demoparent.command.domain

data class CollectionItem(
    val href: String,
    val items: List<Item>,
    val links: List<Link>,
    val metadata: MetaData,
    val version: String
)
