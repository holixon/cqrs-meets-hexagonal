package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.outbound.searchresult.entity

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@EntityListeners(AuditingEntityListener::class)
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
    @JoinColumn(nullable = false, name = "data_item_id", foreignKey = ForeignKey(name = "FK_LINK__DATA_ITEM"))
    @ManyToOne(fetch = FetchType.LAZY)
    var dataItem: DataItemEntity?,
    created: Instant?,
    updated: Instant?,
    createdBy: String?,
    updatedBy: String?

) : BaseEntity(created, updated, createdBy, updatedBy) {
}