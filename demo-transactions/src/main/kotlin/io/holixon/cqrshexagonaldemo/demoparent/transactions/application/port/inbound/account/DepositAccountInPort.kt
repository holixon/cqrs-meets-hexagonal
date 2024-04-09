package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Amount

interface DepositAccountInPort {
    fun deposit(accountNumber: String, amount: Amount)
}