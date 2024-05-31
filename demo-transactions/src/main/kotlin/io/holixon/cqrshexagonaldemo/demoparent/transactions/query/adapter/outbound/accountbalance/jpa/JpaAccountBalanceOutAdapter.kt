package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.outbound.accountbalance.jpa

import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.adapter.outbound.accountbalance.jpa.entity.AccountBalanceEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaAccountBalanceOutAdapter : CrudRepository<AccountBalanceEntity, String>