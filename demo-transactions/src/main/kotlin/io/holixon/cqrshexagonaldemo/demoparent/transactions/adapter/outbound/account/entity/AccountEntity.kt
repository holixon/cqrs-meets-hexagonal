package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.entity

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer.entity.BaseEntity
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
    name = "account",
    schema = "transactions"
)
class AccountEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,

    var accountNumber: String?,
    var customerNumber: String?,
    created: Instant?,
    updated: Instant?,
    createdBy: String?,
    updatedBy: String?

) : BaseEntity(created, updated, createdBy, updatedBy) {
}