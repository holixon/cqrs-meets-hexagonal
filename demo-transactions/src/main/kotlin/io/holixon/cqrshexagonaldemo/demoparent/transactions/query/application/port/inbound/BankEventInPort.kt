package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.inbound

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.MoneyDepositedEvent

interface BankEventInPort {
    fun moneyDeposit(event: MoneyDepositedEvent)
}