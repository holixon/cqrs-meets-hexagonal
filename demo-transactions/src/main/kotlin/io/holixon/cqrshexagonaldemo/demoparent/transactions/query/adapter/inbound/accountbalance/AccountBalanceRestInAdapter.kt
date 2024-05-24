package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.inbound.accountbalance

import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.inbound.AccountBalanceApiDelegate
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.inbound.accountbalance.mapper.toAccountBalanceResponseDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.inbound.dto.AccountBalanceResponseDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.inbound.accountbalance.AccountBalanceInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.InAdapter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
@InAdapter
class AccountBalanceRestInAdapter(
    private val accountBalanceInPort: AccountBalanceInPort
) : AccountBalanceApiDelegate {

    override fun getBalance(accountNumber: String): ResponseEntity<AccountBalanceResponseDto> {
        val accountBalance = accountBalanceInPort.getAccountBalance(accountNumber)
        return ResponseEntity.ok(accountBalance.toAccountBalanceResponseDto())
    }
}
