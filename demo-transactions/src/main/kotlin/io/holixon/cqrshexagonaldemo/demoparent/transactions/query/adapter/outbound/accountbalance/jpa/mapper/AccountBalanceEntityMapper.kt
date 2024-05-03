package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.outbound.accountbalance.jpa.mapper

import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.outbound.accountbalance.jpa.entity.AccountBalanceEntity
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance
import org.springframework.stereotype.Component

@Component
class AccountBalanceEntityMapper {
    fun toDomain(accountBalanceEntity: AccountBalanceEntity) : AccountBalance {
        return AccountBalance(
            iban = Iban(accountBalanceEntity.iban),
            balance = Money(Amount(accountBalanceEntity.balance))
        )
    }
}