package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.customer.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.CustomerDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber

fun Customer.toDto(): CustomerDto {
    return CustomerDto(this.customerNumber.value, this.name.value)
}

fun CustomerDto.toDomain(): Customer {
    return Customer(
            customerNumber = CustomerNumber(this.customerNumber),
            name = Name(this.name))
}