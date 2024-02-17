package io.holixon.cqrshexagonaldemo.demoparent.command.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class DataItem(
    val center: String,
    @field:JsonProperty("date_created") @param:JsonProperty("date_created") val dateCreated: Instant,
    val description: String,
    val keywords: List<String>,
    @field:JsonProperty("media_type") @param:JsonProperty("media_type") val mediaType: String,
    @field:JsonProperty("nasa_id") @param:JsonProperty("nasa_id") val nasaId: String,
    val title: String
)
