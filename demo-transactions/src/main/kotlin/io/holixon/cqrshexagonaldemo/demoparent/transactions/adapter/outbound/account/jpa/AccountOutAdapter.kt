package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa.mapper.AccountEntityMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.OutAdapter

@OutAdapter
class AccountOutAdapter(
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

    override fun deposit(account: Account): Account {
        TODO("Not implemented yet")
    }

    override fun withdraw(account: Account): Account {
        TODO("Not implemented yet")
    }
}
