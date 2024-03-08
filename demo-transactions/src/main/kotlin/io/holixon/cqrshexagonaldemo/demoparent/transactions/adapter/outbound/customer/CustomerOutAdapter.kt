package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer.mapper.CustomerEntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerOutAdapter @Autowired constructor(
        private val jpaCustomerOutAdapter: JpaCustomerOutAdapter,
        private val customerEntityMapper: CustomerEntityMapper
) : CustomerOutPort {

    override fun findCustomer(customerNumber: CustomerNumber): Customer? {
        return jpaCustomerOutAdapter.findByCustomerNumber(customerNumber.value)
                ?.let { entity -> customerEntityMapper.toDomain(entity) }
    }

    override fun createCustomer(customer: Customer): Customer {
        return jpaCustomerOutAdapter.save(customerEntityMapper.toEntity(customer))
                .let { createdCustomer -> customerEntityMapper.toDomain(createdCustomer) }
    }
}