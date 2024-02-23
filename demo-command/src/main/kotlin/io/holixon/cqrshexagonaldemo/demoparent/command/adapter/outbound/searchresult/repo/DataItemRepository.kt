package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.repo

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.entity.DataItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DataItemRepository : JpaRepository<DataItemEntity, Long> {
}