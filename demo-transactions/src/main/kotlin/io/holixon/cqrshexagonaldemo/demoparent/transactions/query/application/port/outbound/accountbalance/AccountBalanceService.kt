package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.outbound.accountbalance

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance

interface AccountBalanceService {
    fun getAccountBalance(iban: Iban): AccountBalance
    fun deposit(account: AccountBalance, balance: Amount): AccountBalance
    fun createAccountBalance(account: AccountBalance): AccountBalance
}
