package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.inbound.accountbalance.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.inbound.dto.AccountBalanceResponseDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance

fun AccountBalance.toAccountBalanceResponseDto(): AccountBalanceResponseDto {
    return AccountBalanceResponseDto(
        this.iban.value,
        this.balance.amount.value.toLong()
    )
}