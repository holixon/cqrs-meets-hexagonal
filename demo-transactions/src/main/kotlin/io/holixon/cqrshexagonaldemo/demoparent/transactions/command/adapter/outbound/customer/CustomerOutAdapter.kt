package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.adapter.outbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.adapter.outbound.customer.mapper.CustomerEntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.customer.CustomerService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.OutAdapter
import org.springframework.beans.factory.annotation.Autowired

@OutAdapter
class CustomerOutAdapter @Autowired constructor(
    private val jpaCustomerOutAdapter: JpaCustomerOutAdapter,
    private val customerEntityMapper: CustomerEntityMapper
) : CustomerService {

    override fun findCustomer(customerNumber: CustomerNumber): Customer? {
        return jpaCustomerOutAdapter.findByCustomerNumber(customerNumber.value)
                ?.let { entity -> customerEntityMapper.toDomain(entity) }
    }

    override fun createCustomer(customer: Customer): Customer {
        return jpaCustomerOutAdapter.save(customerEntityMapper.toEntity(customer))
                .let { createdCustomer -> customerEntityMapper.toDomain(createdCustomer) }
    }
}