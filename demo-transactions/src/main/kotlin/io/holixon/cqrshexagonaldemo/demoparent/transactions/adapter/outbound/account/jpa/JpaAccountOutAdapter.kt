package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account.jpa.entity.AccountEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaAccountOutAdapter : CrudRepository<AccountEntity, String> {
}