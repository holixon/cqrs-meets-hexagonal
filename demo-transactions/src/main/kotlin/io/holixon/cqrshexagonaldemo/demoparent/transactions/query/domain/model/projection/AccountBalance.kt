package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Money

data class AccountBalance(
    var iban: Iban,
    var balance: Money
) {
    fun addAmount(amount: Amount) : AccountBalance {
        val newBalance = this.balance.amount + amount
        return this.copy(balance = Money(newBalance))
    }
}
