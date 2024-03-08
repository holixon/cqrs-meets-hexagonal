package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.eventing

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.Event
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.OutAdapter
import org.springframework.context.ApplicationEventPublisher

@OutAdapter
class SpringEventingOutAdapter(
        private val applicationEventPublisher: ApplicationEventPublisher
) : EventingOutAdapter {

    override fun publishEvent(event: Event) {
        applicationEventPublisher.publishEvent(event)
    }
}