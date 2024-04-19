package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.projection.AccountBalance

interface AccountBalanceInPort {
    fun getAccountBalance(accountNumber: String) : AccountBalance
}