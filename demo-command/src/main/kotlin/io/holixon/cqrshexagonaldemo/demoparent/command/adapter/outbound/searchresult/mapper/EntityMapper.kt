package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.mapper

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.entity.DataItemEntity
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.entity.SearchResultItemEntity
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.entity.LinkEntity
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.DataItem
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.SearchResultItem
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.Link
import org.mapstruct.Mapper

@Mapper
interface EntityMapper {

    fun toDomainObject(entity: LinkEntity): Link

    fun toEntity(domainObject: Link): LinkEntity

    fun toDomainObject(entity: DataItemEntity): DataItem

    fun toEntity(domainObject: DataItem): DataItemEntity

    fun toDomainObject(entity: SearchResultItemEntity): SearchResultItem

    fun toEntity(domainObject: SearchResultItem): SearchResultItemEntity
}