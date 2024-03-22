package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa.mapper.AccountEntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.OutAdapter
import jakarta.transaction.Transactional
import mu.KLogging
import java.math.BigDecimal

@OutAdapter
open class AccountOutAdapter(
    private val jpaAccountOutAdapter: JpaAccountOutAdapter,
    private val accountEntityMapper: AccountEntityMapper
) : AccountOutPort {

    companion object : KLogging()

    override fun findAccount(iban: Iban): Account? {
        val entity = jpaAccountOutAdapter.findById(iban.value).orElseThrow {
            AccountNotFoundException("Account not found for Iban: ${iban.value}")
        }

        return accountEntityMapper.toDomain(entity)
    }

    override fun createAccount(account: Account): Account {
        val toEntity = accountEntityMapper.toEntity(account)
//        toEntity.updated = Instant.now()

        val savedAccount = jpaAccountOutAdapter.save(toEntity)
        return accountEntityMapper.toDomain(savedAccount)
    }

    @Transactional
    override fun deposit(iban: Iban, amount: BigDecimal): Account {
        val entity = jpaAccountOutAdapter.findById(iban.value)
            .orElseThrow {
                AccountNotFoundException("Account not found for Iban: ${iban.value}")
            }

        val account = accountEntityMapper.toDomain(entity)
        account.deposit(amount)
        jpaAccountOutAdapter.save(accountEntityMapper.toEntity(account))

        return account
    }

    @Transactional
    override fun withdraw(iban: Iban, amount: BigDecimal): Account {
        require(amount > BigDecimal.ZERO) {
            "Amount to withdraw must be above zero"
        }

        val entity = jpaAccountOutAdapter.findById(iban.value)
            .orElseThrow {
                AccountNotFoundException("Account not found for Iban: ${iban.value}")
            }

        val account = accountEntityMapper.toDomain(entity)
        account.withdraw(amount)
        jpaAccountOutAdapter.save(accountEntityMapper.toEntity(account))
        return account
    }
}
