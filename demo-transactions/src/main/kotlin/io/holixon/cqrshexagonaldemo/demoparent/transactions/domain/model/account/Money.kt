package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account

import java.math.BigDecimal

data class Money(var amount: BigDecimal) {
    companion object {
        val ZERO = Money(BigDecimal.ZERO)
    }
}
