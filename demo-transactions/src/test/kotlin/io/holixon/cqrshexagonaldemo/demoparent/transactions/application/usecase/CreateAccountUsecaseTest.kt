package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.AccountCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.CustomerAccountVerificationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.IbanCreationService
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
    private lateinit var accountOutPort: AccountOutPort

    @MockK
    private lateinit var customerOutPort: CustomerOutPort

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
        every { customerOutPort.findCustomer(customerNumber) } returns customer
        every { customerAccountVerificationService.canAccountBeCreatedForCustomer(customer) } returns true

        val iban = Iban("DE00123456789")

        every { ibanCreationService.generateNextIban() } returns iban
        val balance = Money()
        val account = Account(customerNumber, iban, balance)

        every { accountOutPort.createAccount(this.any()) } returns account

        // when
        val createdAccount = createAccountUsecase.createAccount(customerNumber)

        // then
        Assertions.assertThat(createdAccount).isNotNull
        verify { accountOutPort.createAccount(account) }
        verify { eventingOutAdapter.publishEvent(AccountCreatedEvent(customerNumber, iban)) }
    }

}
