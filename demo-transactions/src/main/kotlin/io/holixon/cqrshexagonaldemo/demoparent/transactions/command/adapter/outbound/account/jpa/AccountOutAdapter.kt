package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.adapter.outbound.account.jpa

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.adapter.outbound.account.jpa.mapper.AccountEntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.account.AccountService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.OutAdapter
import mu.KLogging

@OutAdapter
class AccountOutAdapter(
    private val jpaAccountOutAdapter: JpaAccountOutAdapter,
    private val accountEntityMapper: AccountEntityMapper
) : AccountService {

    companion object : KLogging()

    override fun findAccount(iban: Iban): Account {
        val entity = jpaAccountOutAdapter.findById(iban.value).orElseThrow {
            AccountNotFoundException("Account not found for Iban: ${iban.value}")
        }

        return accountEntityMapper.toDomain(entity)
    }

    override fun createAccount(account: Account): Account {
        val toEntity = accountEntityMapper.toEntity(account)

        val savedAccount = jpaAccountOutAdapter.save(toEntity)
        return accountEntityMapper.toDomain(savedAccount)
    }

    override fun deposit(account: Account, amount: Amount): Account {
        account.deposit(amount)

        jpaAccountOutAdapter.save(accountEntityMapper.toEntity(account))

        return account
    }
}
