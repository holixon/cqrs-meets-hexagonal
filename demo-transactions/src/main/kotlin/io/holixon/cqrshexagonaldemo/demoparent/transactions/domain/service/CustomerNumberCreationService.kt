package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong

@Service
class CustomerNumberCreationService {

    // TODO: read last customer number as seed for generator
    private val generator: AtomicLong = AtomicLong()

    fun generateNextCustomerNumber(): CustomerNumber {
        val accountNumber = String.format("%06d", generator.incrementAndGet())
        return CustomerNumber(accountNumber)

    }
}