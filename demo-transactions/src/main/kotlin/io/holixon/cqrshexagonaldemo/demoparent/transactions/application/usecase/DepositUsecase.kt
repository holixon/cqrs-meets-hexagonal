package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.DepositAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.MoneyDepositedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.AccountValidationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.Usecase

@Usecase
class DepositUsecase(
    private val accountOutPort: AccountOutPort,
    private val accountValidationService: AccountValidationService,
    private val eventingOutAdapter: EventingOutAdapter
) : DepositAccountInPort {

    override fun deposit(accountNumber: String, amount: Amount) {
        //Validate if the iban and amount are correct before trying to deposit it
        if(!accountValidationService.isAccountNumberValid(accountNumber))
            throw RuntimeException("Invalid account number")

        val depositedAccount = accountOutPort.deposit(Iban(accountNumber), amount)

        //Should be done in the domain (From EDM day talk)
        eventingOutAdapter.publishEvent(MoneyDepositedEvent(
            depositedAccount.iban,
            depositedAccount.balance,
            System.currentTimeMillis()
        ))
    }

}
