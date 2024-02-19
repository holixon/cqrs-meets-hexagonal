package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.entity

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
    @JoinColumn(nullable = false, name = "data_item_id", foreignKey = ForeignKey(name = "FK_LINK_DATA_ITEM"))
    @ManyToOne(fetch = FetchType.LAZY)
    var dataItem: DataItemEntity
) : BaseEntity() {
}