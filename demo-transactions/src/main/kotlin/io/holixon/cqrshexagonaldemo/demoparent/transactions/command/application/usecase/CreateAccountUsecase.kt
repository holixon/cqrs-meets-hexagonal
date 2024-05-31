package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.account.CreateAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.account.AccountService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.customer.CustomerService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.AccountCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.service.CustomerAccountVerificationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.service.IbanCreationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.Usecase
import mu.KLogging

@Usecase
open class CreateAccountUsecase(
    private val accountService: AccountService,
    private val customerService: CustomerService,
    private val customerAccountVerificationService: CustomerAccountVerificationService,
    private val ibanCreationService: IbanCreationService,
    private val eventingOutAdapter: EventingOutAdapter
) : CreateAccountInPort {

    companion object : KLogging()

    override fun createAccount(customerNumber: CustomerNumber): Account {
        logger().info { "create new account for customer $customerNumber" }

        val customer = customerService.findCustomer(customerNumber) ?: throw RuntimeException("no customer")

        if (!customerAccountVerificationService.canAccountBeCreatedForCustomer(customer)) {
            throw RuntimeException("customer already has an account")
        }

        val newAccount = Account(
                customerNumber,
                ibanCreationService.generateNextIban(),
                Money.ZERO
        )

        val createdAccount = accountService.createAccount(newAccount)

        val accountCreatedEvent = AccountCreatedEvent(customerNumber, createdAccount.iban)
        eventingOutAdapter.publishEvent(accountCreatedEvent)

        logger().info { "newly created account: $createdAccount" }
        return createdAccount
    }
}
