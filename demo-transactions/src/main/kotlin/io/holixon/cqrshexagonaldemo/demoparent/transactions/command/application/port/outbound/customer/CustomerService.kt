package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.CustomerNumber

interface CustomerService {
    fun findCustomer(customerNumber: CustomerNumber): Customer?
    fun createCustomer(searchResultItem: Customer): Customer
}