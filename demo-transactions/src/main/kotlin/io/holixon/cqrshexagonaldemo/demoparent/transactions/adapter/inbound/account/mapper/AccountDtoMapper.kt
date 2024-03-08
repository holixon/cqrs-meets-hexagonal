package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.account.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.dto.AccountDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import org.springframework.stereotype.Component

@Component
class AccountDtoMapper {
    fun toDto(account: Account): AccountDto {
        return AccountDto().apply {
            iban = account.iban.value
            customerNumber = account.customerNumber.value
        }
    }

    fun toDomain(dto: AccountDto): Account {
        return Account(
                customerNumber = CustomerNumber(dto.customerNumber),
                iban = Iban(dto.iban))
    }
}