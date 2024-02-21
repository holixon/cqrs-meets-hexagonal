package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.repo

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.entity.SearchResultItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<SearchResultItemEntity, Long> {
}