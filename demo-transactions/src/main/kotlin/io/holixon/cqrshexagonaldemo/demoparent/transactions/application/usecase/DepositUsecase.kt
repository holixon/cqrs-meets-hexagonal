package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.DepositAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.MoneyDepositedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.Usecase
import mu.KLogging

@Usecase
class DepositUsecase(
    private val accountOutPort: AccountOutPort,
    private val eventingOutAdapter: EventingOutAdapter
) : DepositAccountInPort {

    companion object : KLogging()

    override fun deposit(iban: Iban, amount: Amount) {
        logger().info { "deposit $amount to account ${iban.value}" }

        val account = accountOutPort.findAccount(iban)

        val updatedAccount = accountOutPort.deposit(account, amount)

        eventingOutAdapter.publishEvent(MoneyDepositedEvent(
            updatedAccount.iban,
            updatedAccount.balance,
            System.currentTimeMillis()
        ))

        logger().info { "updated account $updatedAccount" }
    }

}
