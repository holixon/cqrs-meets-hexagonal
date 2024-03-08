package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import org.springframework.stereotype.Service

@Service
class CustomerAccountVerificationService {
    fun canAccountBeCreatedForCustomer(customer: Customer): Boolean {
        // TODO: only one account per customer
        return true
    }
}