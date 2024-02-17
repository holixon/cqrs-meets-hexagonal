package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresultdb.mapper

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresultdb.entity.LinkEntity
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.Link
import org.mapstruct.Mapper

@Mapper
interface LinkEntityMapper {

    fun toDomainObject(entity: LinkEntity): Link

    fun toEntity(domainObject: Link): LinkEntity
}