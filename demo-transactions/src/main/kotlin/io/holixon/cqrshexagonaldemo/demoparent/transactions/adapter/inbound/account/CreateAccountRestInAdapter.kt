package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.AccountApiDelegate
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account.mapper.toDto
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
class CreateAccountRestInAdapter(
        private val createAccountInPort: CreateAccountInPort
) : AccountApiDelegate {

    companion object : KLogging()

    override fun createAccount(createAccountRequestDto: CreateAccountRequestDto): ResponseEntity<AccountDto> {
        val createdAccount = createAccountInPort.createAccount(CustomerNumber(createAccountRequestDto.customerNumber))
        return ResponseEntity.ok(createdAccount.toDto())
    }
}