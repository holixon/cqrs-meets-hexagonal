package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.account.AccountService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.customer.CustomerService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.usecase.CreateAccountUsecase
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.AccountCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.service.CustomerAccountVerificationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.service.IbanCreationService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreateAccountUsecaseTest {

    @MockK
    private lateinit var accountService: AccountService

    @MockK
    private lateinit var customerService: CustomerService

    @MockK
    private lateinit var customerAccountVerificationService: CustomerAccountVerificationService

    @MockK
    private lateinit var ibanCreationService: IbanCreationService

    @RelaxedMockK
    private lateinit var eventingOutAdapter: EventingOutAdapter

    @InjectMockKs
    private lateinit var createAccountUsecase: CreateAccountUsecase

    @Test
    fun `should create an account`() {
        // given
        val customerNumber = CustomerNumber("123456789")
        val customer = Customer(customerNumber, Name("Dagobert Duck"))
        every { customerService.findCustomer(customerNumber) } returns customer
        every { customerAccountVerificationService.canAccountBeCreatedForCustomer(customer) } returns true

        val iban = Iban("DE00123456789")

        every { ibanCreationService.generateNextIban() } returns iban
        val balance = Money.ZERO
        val account = Account(customerNumber, iban, balance)

        every { accountService.createAccount(this.any()) } returns account

        // when
        val createdAccount = createAccountUsecase.createAccount(customerNumber)

        // then
        Assertions.assertThat(createdAccount).isNotNull
        verify { accountService.createAccount(account) }
        verify { eventingOutAdapter.publishEvent(AccountCreatedEvent(customerNumber, iban)) }
    }

}
