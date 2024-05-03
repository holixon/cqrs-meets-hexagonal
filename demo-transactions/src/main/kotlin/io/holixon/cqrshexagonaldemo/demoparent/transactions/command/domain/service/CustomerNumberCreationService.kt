package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.service

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.DomainService
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