package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.CreateAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.AccountCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.CustomerAccountVerificationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.IbanCreationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.Usecase

@Usecase
open class CreateAccountUsecase(
        private val accountOutPort: AccountOutPort,
        private val customerOutPort: CustomerOutPort,
        private val customerAccountVerificationService: CustomerAccountVerificationService,
        private val ibanCreationService: IbanCreationService,
        private val eventingOutAdapter: EventingOutAdapter
) : CreateAccountInPort {

    override fun createAccount(customerNumber: CustomerNumber): Account {

        val customer = customerOutPort.findCustomer(customerNumber) ?: throw RuntimeException("no customer")

        if (!customerAccountVerificationService.canAccountBeCreatedForCustomer(customer)) {
            throw RuntimeException("customer already has an account")
        }

        val newAccount = Account(
                customerNumber,
                ibanCreationService.generateNextIban(),
                Money()
        )

        val createdAccount = accountOutPort.createAccount(newAccount)

        val accountCreatedEvent = AccountCreatedEvent(customerNumber, createdAccount.iban)
        eventingOutAdapter.publishEvent(accountCreatedEvent)

        return createdAccount
    }
}