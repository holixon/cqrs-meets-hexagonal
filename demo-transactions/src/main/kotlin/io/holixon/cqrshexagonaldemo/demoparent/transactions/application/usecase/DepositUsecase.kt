package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.DepositAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.MoneyDepositedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.AccountValidationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.Usecase
import java.math.BigDecimal

@Usecase
class DepositUsecase(
    private val accountOutPort: AccountOutPort,
    private val accountValidationService: AccountValidationService,
    private val eventingOutAdapter: EventingOutAdapter
) : DepositAccountInPort {
    override fun deposit(accountNumber: String, amount: BigDecimal) {
        //Validate if the iban and amount are correct before trying to deposit it
        if(!accountValidationService.isAccountNumberValid(accountNumber))
            throw RuntimeException("Invalid account number")

        if(amount <= BigDecimal.ZERO) {
            throw RuntimeException("Amount to deposit must be above zero")
        }

        val depositedAccount = accountOutPort.deposit(Iban(accountNumber), amount)

        eventingOutAdapter.publishEvent(MoneyDepositedEvent(
            depositedAccount.iban,
            depositedAccount.balance,
            System.currentTimeMillis()
        ))
    }
}
