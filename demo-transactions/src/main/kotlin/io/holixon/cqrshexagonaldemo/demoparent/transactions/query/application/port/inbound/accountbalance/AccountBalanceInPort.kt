package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.inbound.accountbalance

import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance

interface AccountBalanceInPort {
    fun getAccountBalance(accountNumber: String) : AccountBalance
}
