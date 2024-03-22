package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account

import java.math.BigDecimal

interface DepositAccountInPort {
    fun deposit(accountNumber: String, amount: BigDecimal)
}