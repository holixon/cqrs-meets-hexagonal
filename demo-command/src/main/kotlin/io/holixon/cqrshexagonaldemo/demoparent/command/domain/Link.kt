package io.holixon.cqrshexagonaldemo.demoparent.command.domain

data class Link(
    var id: Long,
    var href: String,
    var rel: String,
    var render: String?,
)

