package io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresultdb.entity

import io.holixon.cqrshexagonaldemo.demoparent.command.adapter.out.searchresultdb.converter.StringListConverter
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
    @JoinColumn(nullable = false, name = "item_id", foreignKey = ForeignKey(name = "FK_DATA_ITEM_ITEM"))
    @ManyToOne(fetch = FetchType.LAZY)
    var item: ItemEntity
) {
}