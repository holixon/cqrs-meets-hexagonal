package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.exception.InsufficientFundsException
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

    fun withdraw(amount: Amount) {
        if(amount.value > this.balance.amount.value) {
            throw InsufficientFundsException("Not enough funds to be withdrawn")
        }
        this.balance.amount -= amount
    }

}
