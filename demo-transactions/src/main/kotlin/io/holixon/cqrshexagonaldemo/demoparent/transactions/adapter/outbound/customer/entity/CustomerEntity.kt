package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(
        name = "customer",
        schema = "transactions"
)
class CustomerEntity(
        @Id
        var customerNumber: String,
        var customerName: String
)