package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.mapper.EntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.repo.ItemRepository
import io.holixon.cqrshexagonaldemo.demoparent.command.application.port.out.searchresult.SearchResultOutPort
import io.holixon.cqrshexagonaldemo.demoparent.command.domain.SearchResultItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class SearchResultOutAdapter @Autowired constructor(
    val mapper: EntityMapper,
    val itemRepository: ItemRepository
) : SearchResultOutPort {

    override fun saveSearchResult(searchResultItem: SearchResultItem): Mono<SearchResultItem> {
        val itemEntity = mapper.toEntity(searchResultItem)
        itemEntity.data.forEach { dataItemEntity ->
            dataItemEntity.searchResultItem = itemEntity
            dataItemEntity.links.forEach { linkEntity ->
                linkEntity.dataItem = dataItemEntity
            }
        }

        return Mono
            .fromCallable { itemRepository.save(itemEntity) }
            .subscribeOn(Schedulers.boundedElastic())
            .map { savedEntity -> mapper.toDomainObject(savedEntity) }
    }
}