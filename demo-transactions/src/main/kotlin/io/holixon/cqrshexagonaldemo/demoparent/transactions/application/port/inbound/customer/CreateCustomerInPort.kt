package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer

interface CreateCustomerInPort {
    fun createCustomer(customerName: Name): Customer
}