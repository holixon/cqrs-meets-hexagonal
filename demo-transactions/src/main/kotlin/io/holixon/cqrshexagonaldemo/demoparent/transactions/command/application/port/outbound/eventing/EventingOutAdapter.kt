package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.eventing

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.Event

interface EventingOutAdapter {
    fun publishEvent(event: Event)
}