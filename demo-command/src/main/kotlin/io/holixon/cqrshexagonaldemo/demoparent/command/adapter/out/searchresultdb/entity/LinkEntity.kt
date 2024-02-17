package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresultdb.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "link",
    schema = "command"
)

class LinkEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,
    @Column(nullable = false)
    var href: String,
    @Column(nullable = false)
    var rel: String,
    @Column(nullable = true)
    var render: String?,
    @JoinColumn(nullable = false, name = "item_id", foreignKey = ForeignKey(name = "FK_LINK_ITEM"))
    @ManyToOne(fetch = FetchType.LAZY)
    var item: ItemEntity
) : BaseEntity() {
}