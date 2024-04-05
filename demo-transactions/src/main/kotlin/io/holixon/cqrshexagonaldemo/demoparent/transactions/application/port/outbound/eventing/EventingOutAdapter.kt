package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.Event

interface EventingOutAdapter {
    fun publishEvent(event: Event)
}