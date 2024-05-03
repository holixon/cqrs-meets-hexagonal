package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.outbound.accountbalance.jpa.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "account_balance", catalog = "cqrs-meets-hexagonal-read-db")
class AccountBalanceEntity(
    @Id
    var iban: String = "",
    var balance: BigDecimal = BigDecimal.ZERO
)