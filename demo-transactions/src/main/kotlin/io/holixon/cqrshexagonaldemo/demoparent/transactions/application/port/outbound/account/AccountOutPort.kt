package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.projection.AccountBalance

interface AccountOutPort {
    fun findAccount(iban: Iban): Account
    fun createAccount(account: Account): Account
    fun deposit(account: Account, amount: Amount): Account
    fun getAccountBalance(iban: Iban): AccountBalance //TODO: Should be in another port (?!)
}
