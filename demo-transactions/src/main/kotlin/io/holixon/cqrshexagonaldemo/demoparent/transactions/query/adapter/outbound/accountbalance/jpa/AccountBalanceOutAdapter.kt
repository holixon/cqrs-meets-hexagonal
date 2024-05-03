package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.outbound.accountbalance.jpa

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.OutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.outbound.accountbalance.jpa.mapper.AccountBalanceEntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.outbound.accountbalance.AccountBalanceOutPort

@OutAdapter
class AccountBalanceOutAdapter(
    private val jpaAccountBalanceOutAdapter: JpaAccountBalanceOutAdapter,
    private val accountBalanceEntityMapper: AccountBalanceEntityMapper
) : AccountBalanceOutPort {
    override fun getAccountBalance(iban: Iban): AccountBalance {
        val entity = jpaAccountBalanceOutAdapter.findById(iban.value).orElseThrow {
            AccountNotFoundException("Account not found for Iban: ${iban.value}")
        }

        return accountBalanceEntityMapper.toDomain(entity)
    }
}