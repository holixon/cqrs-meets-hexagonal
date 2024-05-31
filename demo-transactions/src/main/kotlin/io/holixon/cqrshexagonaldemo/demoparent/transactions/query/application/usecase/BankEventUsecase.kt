package io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.MoneyDepositedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.inbound.BankEventInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.outbound.accountbalance.AccountBalanceService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.Usecase
import mu.KLogging
import java.math.BigDecimal

/**
 * This class is the application service class that handles bankEvents(deposits,withdraws,etc.)
 */
@Usecase
class BankEventUsecase(
    private val accountBalanceService: AccountBalanceService
) : BankEventInPort {

    companion object : KLogging()

    override fun handleMoneyDeposit(event: MoneyDepositedEvent) {
        logger.debug { "Received MoneyDepositEvent for iban ${event.iban} with amount ${event.balance}" }
        try {
            val accountBalanceProjection = accountBalanceService.getAccountBalance(event.iban)

            accountBalanceProjection.addAmount(event.balance.amount)

            accountBalanceService.persistAccountBalance(accountBalanceProjection)
        } catch (accountNotFound: AccountNotFoundException) {
            logger.debug { "Creating account balance for the first time for iban ${event.iban} with amount ${event.balance}" }
            val accountBalanceProjection = AccountBalance(event.iban, Money(Amount(BigDecimal.ZERO)))
                    .addAmount(event.balance.amount)

            accountBalanceService.persistAccountBalance(accountBalanceProjection)
        }
    }
}
