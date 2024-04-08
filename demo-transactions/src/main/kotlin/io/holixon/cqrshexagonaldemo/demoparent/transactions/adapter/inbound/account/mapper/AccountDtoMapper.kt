package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.AccountCreatedResponseDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account

fun Account.toAccountCreatedResponseDto(): AccountCreatedResponseDto {
    return AccountCreatedResponseDto(this.customerNumber.value, this.iban.value)
}
