package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.entity

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresult.converter.StringListConverter
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
    name = "data_item",
    schema = "command"
)
class DataItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,
    @Column
    var center: String,
    @Column
    var dateCreated: Instant,
    @Column
    var description: String,
    @Column
    @Convert(converter = StringListConverter::class)
    var keywords: List<String>,
    @Column
    var mediaType: String,
    @Column
    var nasaId: String,
    @Column
    var title: String,
    @JoinColumn(
        nullable = false,
        name = "search_result_item_id",
        foreignKey = ForeignKey(name = "FK_DATA_ITEM__SEARCH_RESULT_ITEM")
    )
    @ManyToOne(fetch = FetchType.LAZY)
    var searchResultItem: SearchResultItemEntity?,
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dataItem", cascade = [CascadeType.PERSIST])
    var links: Set<LinkEntity>?,
    created: Instant?,
    updated: Instant?,
    createdBy: String?,
    updatedBy: String?

) : BaseEntity(created, updated, createdBy, updatedBy) {

}