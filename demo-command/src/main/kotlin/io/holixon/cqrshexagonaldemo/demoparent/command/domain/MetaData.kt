package io.holixon.cqrshexagonaldemo.demoparent.command.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class MetaData(@field:JsonProperty("total_hits") @param:JsonProperty("total_hits") val totalHits: Long)
