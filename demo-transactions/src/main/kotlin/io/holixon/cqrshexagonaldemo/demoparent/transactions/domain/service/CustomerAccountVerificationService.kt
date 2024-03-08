package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.DomainService

@DomainService
class CustomerAccountVerificationService {
    fun canAccountBeCreatedForCustomer(customer: Customer): Boolean {
        // TODO: only one account per customer
        return true
    }
}