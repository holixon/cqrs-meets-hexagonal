package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.Usecase
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.inbound.accountbalance.AccountBalanceInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.outbound.accountbalance.AccountBalanceOutPort

@Usecase
class AccountBalanceUsecase(
    private val accountBalanceOutPort: AccountBalanceOutPort
) : AccountBalanceInPort {

    override fun getAccountBalance(accountNumber: String): AccountBalance {
        val accountBalance = accountBalanceOutPort.getAccountBalance(Iban(accountNumber))
        return accountBalance
    }
}
