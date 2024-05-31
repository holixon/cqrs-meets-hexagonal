package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban

interface AccountService {
    fun findAccount(iban: Iban): Account
    fun createAccount(account: Account): Account
    fun deposit(account: Account, amount: Amount): Account
}
