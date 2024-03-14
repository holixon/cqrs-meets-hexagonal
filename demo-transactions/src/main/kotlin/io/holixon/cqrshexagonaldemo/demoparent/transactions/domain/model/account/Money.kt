package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account

import java.math.BigDecimal

//TODO: Find a better name (!?)
class Money(var amount: BigDecimal = BigDecimal.ZERO) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        return amount == other.amount
    }

    override fun hashCode(): Int {
        return amount.hashCode()
    }

    override fun toString(): String {
        return "Money(amount=$amount)"
    }

}
