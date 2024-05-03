package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.Usecase
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.inbound.accountbalance.AccountBalanceInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.outbound.accountbalance.AccountBalanceService

@Usecase
class AccountBalanceUsecase(
    private val accountBalanceService: AccountBalanceService
) : AccountBalanceInPort {

    override fun getAccountBalance(accountNumber: String): AccountBalance {
        val accountBalance = accountBalanceService.getAccountBalance(Iban(accountNumber))
        return accountBalance
    }
}
