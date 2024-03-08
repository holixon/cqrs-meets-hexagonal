package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber

data class CustomerCreatedEvent(val customerName: Name, val customerNumber: CustomerNumber) : Event
