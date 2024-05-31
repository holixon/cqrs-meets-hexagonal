package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.outbound.accountbalance.jpa

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.OutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.outbound.accountbalance.jpa.mapper.AccountBalanceEntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.outbound.accountbalance.AccountBalanceService

@OutAdapter
class AccountBalanceOutAdapter(
    private val jpaAccountBalanceOutAdapter: JpaAccountBalanceOutAdapter,
    private val accountBalanceEntityMapper: AccountBalanceEntityMapper
) : AccountBalanceService {

    override fun getAccountBalance(iban: Iban): AccountBalance {
        val entity = jpaAccountBalanceOutAdapter.findById(iban.value).orElseThrow {
            AccountNotFoundException("Account not found for Iban: ${iban.value}")
        }

        return accountBalanceEntityMapper.toDomain(entity)
    }
}
