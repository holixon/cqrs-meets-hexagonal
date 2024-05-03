package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.outbound.accountbalance

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance

interface AccountBalanceOutPort {
    fun getAccountBalance(iban: Iban): AccountBalance
}
