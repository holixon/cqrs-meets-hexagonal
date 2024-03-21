package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber

data class Account(
    var customerNumber: CustomerNumber,
    var iban: Iban,
    var balance: Money
) {
    fun deposit(amount: Amount) {
        this.balance.amount += amount.value
    }

    fun withdraw(amount: Amount) {
        require(this.balance.amount > amount.value) {
            "Cannot withdraw more than account balance"
        }
        this.balance.amount -= amount.value
    }
}
