package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.AccountBalanceResponseDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.AccountCreatedResponseDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.projection.AccountBalance

fun Account.toAccountCreatedResponseDto(): AccountCreatedResponseDto {
    return AccountCreatedResponseDto(this.customerNumber.value, this.iban.value)
}

fun AccountBalance.toAccountBalanceResponseDto(): AccountBalanceResponseDto {
    return AccountBalanceResponseDto(
        this.iban.value,
        this.balance.amount.toLong()
    )
}
