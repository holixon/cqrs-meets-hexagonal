package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.nasaapi.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MetaDataDto(@field:JsonProperty("total_hits") @param:JsonProperty("total_hits") val totalHits: Long)