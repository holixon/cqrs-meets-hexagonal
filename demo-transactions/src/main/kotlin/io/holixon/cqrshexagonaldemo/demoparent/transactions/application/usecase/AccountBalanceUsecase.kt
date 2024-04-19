package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.AccountBalanceInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.projection.AccountBalance
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.Usecase

@Usecase
class AccountBalanceUsecase(
    private val accountOutPort: AccountOutPort
) : AccountBalanceInPort {

    override fun getAccountBalance(accountNumber: String): AccountBalance {
        val accountBalance = accountOutPort.getAccountBalance(Iban(accountNumber))
        return accountBalance
    }
}
