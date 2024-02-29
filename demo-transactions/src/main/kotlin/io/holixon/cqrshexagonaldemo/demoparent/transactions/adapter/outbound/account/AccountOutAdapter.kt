package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.Account
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AccountOutAdapter : AccountOutPort {

    override fun findAccount(accountNumber: String): Account? {
        return null;
    }

    override fun createAccount(account: Account): Account {
        return Account(
            created = Instant.now(),
            createdBy = "me",
            accountNumber = "123",
            customerNumber = "456",
            id = -1,
            updated = null,
            updatedBy = null
        )
    }

}