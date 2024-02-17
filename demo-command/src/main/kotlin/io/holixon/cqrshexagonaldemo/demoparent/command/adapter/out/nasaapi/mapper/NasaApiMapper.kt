package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.mapper

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.DataItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.ItemDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.LinkDto
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.MetaDataDto
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.DataItem
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.Item
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.Link
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.MetaData
import org.mapstruct.Mapper

@Mapper
interface NasaApiMapper {
    fun toDomainObject(dto: MetaDataDto): MetaData

    fun toDto(domainObject: MetaData): MetaDataDto

    fun toDomainObject(dto: DataItemDto): DataItem

    fun toDto(domainObject: DataItem): DataItemDto

    fun toDomainObject(dto: ItemDto): Item

    fun toDto(domainObject: Item): ItemDto

    fun toDomainObject(dto: LinkDto): Link

    fun toDto(domainObject: Link): LinkDto
}