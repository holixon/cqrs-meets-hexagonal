package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import java.math.BigDecimal

data class Account(
    var customerNumber: CustomerNumber,
    var iban: Iban,
    var balance: Money
) {
    fun deposit(amount: BigDecimal) {
        this.balance.amount.plus(amount)
    }

    fun withdraw(amount: BigDecimal) {
        require(this.balance.amount > amount) {
            "Cannot withdraw more than account balance"
        }
        this.balance.amount -= amount
    }
}
