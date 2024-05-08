package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.inbound.events

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.MoneyDepositedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.inbound.BankEventInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.InAdapter
import org.springframework.context.event.EventListener

@InAdapter
class QueryEventListener( //TODO: Rename class
    private val bankEventInPort: BankEventInPort
) {

    @EventListener
    fun handle(event: MoneyDepositedEvent) = bankEventInPort.moneyDeposit(event)

}