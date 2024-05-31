package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.adapter.inbound.account.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.adapter.inbound.dto.AccountCreatedResponseDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Account

fun Account.toAccountCreatedResponseDto(): AccountCreatedResponseDto {
    return AccountCreatedResponseDto(this.customerNumber.value, this.iban.value)
}
