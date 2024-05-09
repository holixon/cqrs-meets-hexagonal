package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.MoneyDepositedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.inbound.BankEventInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.outbound.accountbalance.AccountBalanceService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.Usecase
import mu.KLogging

/**
 * This class is the application service class that handles bankEvents(deposits,withdraws,etc.)
 */
@Usecase
class BankEventUsecase(
    private val accountBalanceService: AccountBalanceService
) : BankEventInPort {

    companion object : KLogging()

    override fun handleMoneyDeposit(event: MoneyDepositedEvent) { //TODO rename class
        logger.debug { "Received MoneyDepositEvent for iban ${event.iban} with amount ${event.balance}" }
        try {
            val account = accountBalanceService.getAccountBalance(event.iban)

            account.deposit(event.balance.amount)

            accountBalanceService.deposit(account, event.balance.amount)
        }catch (accountNotFound: AccountNotFoundException) {
            createAccountBalance(event)
        }
    }

    private fun createAccountBalance(event: MoneyDepositedEvent) {
        val newAccount = AccountBalance(event.iban, event.balance)
        accountBalanceService.createAccountBalance(newAccount)
    }

}
