package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.entity

import jakarta.persistence.*
import java.time.Instant


@Entity
@Table(
    name = "search_result_item",
    schema = "command"
)
class SearchResultItemEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "searchResultItem", cascade = [CascadeType.PERSIST])
    var data: Set<DataItemEntity>?,

    @Column(nullable = false)
    var href: String,
    created: Instant?,
    updated: Instant?,
    createdBy: String?,
    updatedBy: String?

) : BaseEntity(created, updated, createdBy, updatedBy) {
}