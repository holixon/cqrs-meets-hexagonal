package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account

import java.math.BigDecimal

data class Money(var amount: Amount) {
    companion object {
        val ZERO = Money(Amount(BigDecimal.ZERO))
    }
}
