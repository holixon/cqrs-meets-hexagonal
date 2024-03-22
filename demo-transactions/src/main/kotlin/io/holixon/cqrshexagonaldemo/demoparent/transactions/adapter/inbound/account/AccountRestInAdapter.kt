package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.AccountApiDelegate
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account.mapper.toDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.AccountDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.CreateAccountRequestDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.DepositRequestDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.CreateAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.DepositAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.InAdapter
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@InAdapter
class AccountRestInAdapter(
    private val createAccountInPort: CreateAccountInPort,
    private val depositAccountInPort: DepositAccountInPort
) : AccountApiDelegate {

    companion object : KLogging()

    override fun createAccount(createAccountRequestDto: CreateAccountRequestDto): ResponseEntity<AccountDto> {
        val createdAccount = createAccountInPort.createAccount(CustomerNumber(createAccountRequestDto.customerNumber))
        return ResponseEntity.ok(createdAccount.toDto())
    }

    override fun deposit(depositRequestDto: DepositRequestDto): ResponseEntity<Void> {
        depositAccountInPort.deposit(depositRequestDto.accountNumber, BigDecimal.valueOf(depositRequestDto.amount))
        return ResponseEntity.noContent().build()
    }
}
