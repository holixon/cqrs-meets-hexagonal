package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer.entity

import jakarta.persistence.*
import java.time.Instant


@Entity
@Table(
    name = "customer",
    schema = "transactions"
)
class CustomerEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,
    var customerNumber: String?,
    created: Instant?,
    updated: Instant?,
    createdBy: String?,
    updatedBy: String?

) : BaseEntity(created, updated, createdBy, updatedBy) {
}