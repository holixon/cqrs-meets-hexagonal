package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.account.DepositAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.account.AccountService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.MoneyDepositedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.Usecase
import mu.KLogging
import java.math.BigDecimal

@Usecase
class DepositUsecase(
    private val accountService: AccountService,
    private val eventingOutAdapter: EventingOutAdapter
) : DepositAccountInPort {

    companion object : KLogging()

    override fun deposit(iban: Iban, amount: Amount) {
        logger().info { "deposit $amount to account ${iban.value}" }

        require(amount.value >= BigDecimal.ZERO) {
            logger().error {
                "Amount must be positive"
            }
        }

        val account = accountService.findAccount(iban)

        val updatedAccount = accountService.deposit(account, amount)

        eventingOutAdapter.publishEvent(
            MoneyDepositedEvent(
                updatedAccount.iban,
                updatedAccount.balance,
                System.currentTimeMillis()
            )
        )

        logger().info { "updated account $updatedAccount" }
    }

}
