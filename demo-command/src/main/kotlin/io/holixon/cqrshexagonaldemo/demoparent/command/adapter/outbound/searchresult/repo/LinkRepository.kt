package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.repo

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.entity.LinkEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LinkRepository : JpaRepository<LinkEntity, Long> {

    fun findByHref(href: String)
}