package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.DepositAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.MoneyDepositedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.Usecase

@Usecase
class DepositUsecase(
    private val accountOutPort: AccountOutPort,
    private val eventingOutAdapter: EventingOutAdapter
) : DepositAccountInPort {

    override fun deposit(accountNumber: String, amount: Amount) {
        val account = accountOutPort.findAccount(Iban(accountNumber))

        val depositedAccount = accountOutPort.deposit(account, amount)

        eventingOutAdapter.publishEvent(MoneyDepositedEvent(
            depositedAccount.iban,
            depositedAccount.balance,
            System.currentTimeMillis()
        ))
    }

}
