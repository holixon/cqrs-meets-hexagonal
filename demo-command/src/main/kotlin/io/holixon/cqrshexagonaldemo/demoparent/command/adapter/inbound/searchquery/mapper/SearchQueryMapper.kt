package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery.mapper

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery.model.UserDataItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.inbound.searchquery.model.UserLinkDto
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.DataItem
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.Link
import org.mapstruct.Mapper

@Mapper
interface SearchQueryMapper {

    fun toUserDto(dataItem: DataItem): UserDataItemDto

    fun toUserDto(link: Link): UserLinkDto

}