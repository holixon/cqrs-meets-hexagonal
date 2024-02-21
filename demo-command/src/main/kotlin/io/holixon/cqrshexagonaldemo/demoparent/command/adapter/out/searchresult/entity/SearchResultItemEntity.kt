package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "search_result_item",
    schema = "command"
)
class SearchResultItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "searchResultItem")
    var data: Set<DataItemEntity>,
    @Column(nullable = false)
    var href: String,

) : BaseEntity() {
}