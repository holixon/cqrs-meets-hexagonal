package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.mapper.AccountDtoMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.mapper.CustomerDtoMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.model.AccountDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.model.CreateAccountRequestDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.model.CreateCustomerRequestDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.model.CustomerDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.service.gen.CreateAccountApi
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer.CustomerOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.searchquery.CreateAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.CustomerNumberCreationService
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateAccountInAdapter(
        private val inPort: CreateAccountInPort,
        private val accountDtoMapper: AccountDtoMapper,
        private val customerDtoMapper: CustomerDtoMapper,
        private val customerNumberCreationService: CustomerNumberCreationService,
        private val customerOutAdapter: CustomerOutAdapter
) : CreateAccountApi {

    companion object : KLogging()

    override fun createCustomer(createCustomerRequestDto: CreateCustomerRequestDto): ResponseEntity<CustomerDto> {

        val customer = Customer(
                customerNumber = customerNumberCreationService.generateNextCustomerNumber(),
                name = Name(createCustomerRequestDto.name))
        val createCustomer = customerOutAdapter.createCustomer(customer)
        return ResponseEntity.ok(customerDtoMapper.toDto(createCustomer))
    }

    override fun createAccount(createAccountRequestDto: CreateAccountRequestDto): ResponseEntity<AccountDto> {
        val createdAccount = inPort.createAccount(CustomerNumber(createAccountRequestDto.customerNumber))
        return ResponseEntity.ok(accountDtoMapper.toDto(createdAccount))
    }
}