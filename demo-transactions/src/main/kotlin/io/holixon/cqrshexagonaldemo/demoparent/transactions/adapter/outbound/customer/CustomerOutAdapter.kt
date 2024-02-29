package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer.mapper.EntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CustomerOutAdapter @Autowired constructor(
    val mapper: EntityMapper,
) : CustomerOutPort {

    override fun createAccount(searchResultItem: Customer): Customer {

        return Customer(
            created = Instant.now(),
            createdBy = "me",
            customerNumber = "123",
            id = -1,
            updated = null,
            updatedBy = null
        )
    }
}