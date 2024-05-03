package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.Customer

interface CreateCustomerInPort {
    fun createCustomer(customerName: Name): Customer
}