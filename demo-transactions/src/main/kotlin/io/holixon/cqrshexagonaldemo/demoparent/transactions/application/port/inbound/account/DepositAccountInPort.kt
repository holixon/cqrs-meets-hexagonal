package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban

interface DepositAccountInPort {
    fun deposit(iban: Iban, amount: Amount)
}