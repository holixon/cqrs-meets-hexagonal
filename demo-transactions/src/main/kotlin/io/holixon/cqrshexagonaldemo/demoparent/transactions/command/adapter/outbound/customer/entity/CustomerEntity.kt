package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.adapter.outbound.customer.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "customer")
class CustomerEntity(
    @Id
    var customerNumber: String = "", //Default constructor needed for JPA
    var customerName: String = ""
)