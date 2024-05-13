package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.account.WithdrawAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.account.AccountService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.MoneyWithdrawnEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.Usecase
import mu.KLogging
import java.math.BigDecimal

@Usecase
class WithdrawUseCase(
    private val accountService: AccountService,
    private val eventingOutAdapter: EventingOutAdapter
) : WithdrawAccountInPort {

    companion object : KLogging()

    override fun withdraw(iban: Iban, amount: Amount) {
        logger().info { "withdraw $amount from account ${iban.value}" }

        require(amount.value >= BigDecimal.ZERO) {
            logger().error {
                "Amount must be positive"
            }
        }

        val account = accountService.findAccount(iban)

        val updatedAccount = accountService.withdraw(account, amount)

        eventingOutAdapter.publishEvent(
            MoneyWithdrawnEvent(
                updatedAccount.iban,
                updatedAccount.balance,
                System.currentTimeMillis()
            )
        )

        logger().info { "updated account $updatedAccount" }
    }
}
