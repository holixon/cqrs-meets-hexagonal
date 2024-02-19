package io.holixon.cqrshexagonaldemo.demoparent.command.domain

import java.time.Instant

data class DataItem(
    var id: Long,
    val center: String,
    val dateCreated: Instant,
    val description: String,
    val keywords: List<String>,
    val mediaType: String,
    val nasaId: String,
    val title: String,
    var links: List<Link>?
)
