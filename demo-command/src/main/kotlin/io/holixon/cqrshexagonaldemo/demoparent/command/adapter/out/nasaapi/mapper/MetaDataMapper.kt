package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.mapper

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.nasaapi.model.MetaDataDto
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.MetaData
import org.mapstruct.Mapper

@Mapper
interface MetaDataMapper {
    fun toDomainObject(dto: MetaDataDto): MetaData

    fun toDto(domainObject: MetaData): MetaDataDto
}