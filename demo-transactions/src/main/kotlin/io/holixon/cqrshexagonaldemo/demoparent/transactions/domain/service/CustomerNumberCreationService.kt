package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.DomainService
import java.util.concurrent.atomic.AtomicLong

@DomainService
class CustomerNumberCreationService {

    // TODO: read last customer number as seed for generator
    private val generator: AtomicLong = AtomicLong()

    fun generateNextCustomerNumber(): CustomerNumber {
        val accountNumber = String.format("%06d", generator.incrementAndGet())
        return CustomerNumber(accountNumber)

    }
}