package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.AccountApiDelegate
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account.mapper.AccountDtoMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.AccountDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.CreateAccountRequestDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.CreateAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.InAdapter
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
@InAdapter
class CreateAccountInAdapter(
        private val createAccountInPort: CreateAccountInPort,
        private val accountDtoMapper: AccountDtoMapper
) : AccountApiDelegate {

    companion object : KLogging()

    override fun createAccount(createAccountRequestDto: CreateAccountRequestDto): ResponseEntity<AccountDto> {
        val createdAccount = createAccountInPort.createAccount(CustomerNumber(createAccountRequestDto.customerNumber))
        return ResponseEntity.ok(accountDtoMapper.toDto(createdAccount))
    }
}