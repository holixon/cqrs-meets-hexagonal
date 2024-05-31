package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.service

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.DomainService

@DomainService
class CustomerAccountVerificationService {
    fun canAccountBeCreatedForCustomer(customer: Customer): Boolean {
        // TODO: only one account per customer
        return true
    }
}