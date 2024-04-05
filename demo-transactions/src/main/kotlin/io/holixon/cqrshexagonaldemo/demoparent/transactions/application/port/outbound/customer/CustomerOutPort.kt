package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber

interface CustomerOutPort {
    fun findCustomer(customerNumber: CustomerNumber): Customer?
    fun createCustomer(searchResultItem: Customer): Customer
}