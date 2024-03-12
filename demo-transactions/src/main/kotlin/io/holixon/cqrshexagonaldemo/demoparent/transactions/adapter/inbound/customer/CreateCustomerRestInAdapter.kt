package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.CustomerApiDelegate
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.customer.mapper.toDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.CreateCustomerRequestDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.CustomerDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.customer.CreateCustomerInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.InAdapter
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
@InAdapter
class CreateCustomerRestInAdapter(
        private val createCustomerInPort: CreateCustomerInPort,
) : CustomerApiDelegate {

    companion object : KLogging()

    override fun createCustomer(createCustomerRequestDto: CreateCustomerRequestDto): ResponseEntity<CustomerDto> {
        val customer = createCustomerInPort.createCustomer(customerName = Name(createCustomerRequestDto.name))
        return ResponseEntity.ok(customer.toDto())
    }
}