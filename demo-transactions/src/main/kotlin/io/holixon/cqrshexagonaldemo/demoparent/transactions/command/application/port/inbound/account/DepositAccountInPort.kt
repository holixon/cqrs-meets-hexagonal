package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban

interface DepositAccountInPort {
    fun deposit(iban: Iban, amount: Amount)
}