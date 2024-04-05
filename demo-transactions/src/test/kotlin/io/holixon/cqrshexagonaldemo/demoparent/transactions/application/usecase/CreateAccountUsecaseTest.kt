package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.AccountCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.CustomerAccountVerificationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.IbanCreationService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class CreateAccountUsecaseTest {

    @Mock
    private lateinit var accountOutPort: AccountOutPort

    @Mock
    private lateinit var customerOutPort: CustomerOutPort

    @Mock
    private lateinit var customerAccountVerificationService: CustomerAccountVerificationService

    @Mock
    private lateinit var ibanCreationService: IbanCreationService

    @Mock
    private lateinit var eventingOutAdapter: EventingOutAdapter

    @InjectMocks
    private lateinit var createAccountUsecase: CreateAccountUsecase

    @Test
    fun `should create an account`() {
        // given
        val customerNumber = CustomerNumber("123456789")
        val customer = Customer(customerNumber, Name("Dagobert Duck"))
        whenever(customerOutPort.findCustomer(customerNumber)).thenReturn(customer)
        whenever(customerAccountVerificationService.canAccountBeCreatedForCustomer(customer)).thenReturn(true)
        val iban = Iban("DE00123456789")
        whenever(ibanCreationService.generateNextIban()).thenReturn(iban)
        val account = Account(customerNumber, iban)
        whenever(accountOutPort.createAccount(any())).thenReturn(account)

        // when
        val createdAccount = createAccountUsecase.createAccount(customerNumber)

        // then
        Assertions.assertThat(createdAccount).isNotNull
        verify(accountOutPort).createAccount(account)
        Mockito.verify(eventingOutAdapter).publishEvent(AccountCreatedEvent(customerNumber, iban))
    }

}