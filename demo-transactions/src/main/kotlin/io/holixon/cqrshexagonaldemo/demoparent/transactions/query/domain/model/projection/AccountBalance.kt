package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Money

data class AccountBalance(
    var iban: Iban,
    var balance: Money
) {
    fun deposit(amount: Amount) {
        //should it be immutable ?
        this.balance.amount += amount
    }
}
