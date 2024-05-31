package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.CustomerNumber

data class Account(
    var customerNumber: CustomerNumber,
    var iban: Iban,
    var balance: Money
) {
    fun deposit(amount: Amount) {
        // TODO: better immutable?
        this.balance.amount += amount
    }

}
