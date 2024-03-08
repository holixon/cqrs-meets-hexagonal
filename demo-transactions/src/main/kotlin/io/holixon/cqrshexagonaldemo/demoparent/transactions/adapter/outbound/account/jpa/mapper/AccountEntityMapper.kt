package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa.entity.AccountEntity
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import org.springframework.stereotype.Component

@Component
class AccountEntityMapper {
    fun toEntity(account: Account): AccountEntity {
        return AccountEntity(
                customerNumber = account.customerNumber.value,
                iban = account.iban.value)
    }

    fun toDomain(accountEntity: AccountEntity): Account {
        return Account(
                customerNumber = CustomerNumber(accountEntity.customerNumber),
                iban = Iban(accountEntity.iban))
    }
}