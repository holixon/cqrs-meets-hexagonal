package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.entity.SearchResultItemEntity
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.mapper.EntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.repo.ItemRepository
import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.searchresult.SearchResultOutPort
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.SearchResultItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SearchResultOutAdapter @Autowired constructor(
    val mapper: EntityMapper,
    val itemRepository: ItemRepository
) : SearchResultOutPort {

    override fun saveSearchResult(searchResultItem: SearchResultItem): SearchResultItem {
        val itemEntity = mapper.toEntity(searchResultItem)
        itemEntity.data?.forEach { dataItemEntity ->
            dataItemEntity.searchResultItem = itemEntity
            dataItemEntity.links?.forEach { linkEntity ->
                linkEntity.dataItem = dataItemEntity
            }
        }

        val savedEntity: SearchResultItemEntity = itemRepository.save(itemEntity)
        val toDomainObject = mapper.toDomainObject(savedEntity)
        return toDomainObject
    }
}