package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.customer.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.CustomerDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import org.springframework.stereotype.Component

@Component
class CustomerDtoMapper {
    fun toDto(customer: Customer): CustomerDto {
        return CustomerDto().apply {
            name = customer.name.value
            customerNumber = customer.customerNumber.value
        }
    }

    fun toDomain(dto: CustomerDto): Customer {
        return Customer(
                customerNumber = CustomerNumber(dto.customerNumber),
                name = Name(dto.name))
    }
}