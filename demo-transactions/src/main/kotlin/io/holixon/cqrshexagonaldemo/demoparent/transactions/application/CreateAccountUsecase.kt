package io.holixon.cqrshexagonaldemo.demoparent.transactions.application

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.searchquery.CreateAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.event.AccountCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.CustomerAccountVerificationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.IbanCreationService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
open class CreateAccountUsecase(
        private val accountOutPort: AccountOutPort,
        private val customerOutPort: CustomerOutPort,
        private val customerAccountVerificationService: CustomerAccountVerificationService,
        private val ibanCreationService: IbanCreationService,
        private val applicationEventPublisher: ApplicationEventPublisher
) : CreateAccountInPort {

    override fun createAccount(customerNumber: CustomerNumber): Account {

        val customer = customerOutPort.findCustomer(customerNumber) ?: throw RuntimeException("no customer")

        if (!customerAccountVerificationService.canAccountBeCreatedForCustomer(customer)) {
            throw RuntimeException("customer already has an account")
        }

        val newAccount = Account(
                customerNumber,
                ibanCreationService.generateNextIban()
        )

        val createdAccount = accountOutPort.createAccount(newAccount)

        val accountCreatedEvent = AccountCreatedEvent(customerNumber, createdAccount.iban)
        applicationEventPublisher.publishEvent(accountCreatedEvent)

        return createdAccount
    }

}
