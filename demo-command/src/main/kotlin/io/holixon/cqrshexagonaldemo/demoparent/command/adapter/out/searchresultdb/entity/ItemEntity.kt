package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresultdb.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "item",
    schema = "command"
)
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    var links: Set<LinkEntity>,
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    var data: Set<DataItemEntity>
) : BaseEntity() {
}