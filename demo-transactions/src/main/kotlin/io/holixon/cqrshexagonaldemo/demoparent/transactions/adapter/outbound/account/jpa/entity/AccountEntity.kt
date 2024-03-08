package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(
        name = "account",
        schema = "transactions"
)
class AccountEntity(
        @Id
        var iban: String,
        var customerNumber: String
)