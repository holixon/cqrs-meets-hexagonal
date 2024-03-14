package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.AccountDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber

fun Account.toDto(): AccountDto {
    return AccountDto(this.customerNumber.value, this.iban.value)
}

fun AccountDto.toDomain(): Account {
    return Account(
            customerNumber = CustomerNumber(this.customerNumber),
            iban = Iban(this.iban),
            balance = Money()
    )
}
