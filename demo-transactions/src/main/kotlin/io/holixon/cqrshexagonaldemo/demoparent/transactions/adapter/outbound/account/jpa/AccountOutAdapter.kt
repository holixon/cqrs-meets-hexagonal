package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa.mapper.AccountEntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.OutAdapter
import jakarta.transaction.Transactional
import java.math.BigDecimal

@OutAdapter
open class AccountOutAdapter(
    private val jpaAccountOutAdapter: JpaAccountOutAdapter,
    private val accountEntityMapper: AccountEntityMapper
) : AccountOutPort {

    override fun findAccount(iban: Iban): Account? {
        val entity = jpaAccountOutAdapter.findById(iban.value)
        if (!entity.isPresent) return null

        return accountEntityMapper.toDomain(entity.get())
    }

    override fun createAccount(account: Account): Account {
        val toEntity = accountEntityMapper.toEntity(account)
//        toEntity.updated = Instant.now()

        val savedAccount = jpaAccountOutAdapter.save(toEntity)
        return accountEntityMapper.toDomain(savedAccount)
    }

    //Refactor code similar to withdraw...
    @Transactional
    override fun deposit(iban: Iban, amount: BigDecimal): Account {
        require(amount > BigDecimal.ZERO) {
            "Amount to deposit must be above zero"
        }

        val entity = jpaAccountOutAdapter.findById(iban.value)
            .orElseThrow {
                IllegalArgumentException("Account not found for Iban: ${iban.value}")
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
                IllegalArgumentException("Account not found for Iban: ${iban.value}")
            }

        val account = accountEntityMapper.toDomain(entity)
        account.withdraw(amount)
        jpaAccountOutAdapter.save(accountEntityMapper.toEntity(account))
        return account
    }
}
