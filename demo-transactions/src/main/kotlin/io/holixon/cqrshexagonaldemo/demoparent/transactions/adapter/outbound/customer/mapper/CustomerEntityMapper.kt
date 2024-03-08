package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer.entity.CustomerEntity
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import org.springframework.stereotype.Component

@Component
class CustomerEntityMapper {
    fun toEntity(customer: Customer): CustomerEntity {
        return CustomerEntity(
                customerNumber = customer.customerNumber.value,
                name = customer.name.value
        )
    }

    fun toDomain(customerEntity: CustomerEntity): Customer {
        return Customer(
                customerNumber = CustomerNumber(customerEntity.customerNumber),
                name = Name(customerEntity.name)
        )
    }
}