package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.entity

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
    var data: Set<DataItemEntity>,
    @Column(nullable = false)
    var href: String,

) : BaseEntity() {
}