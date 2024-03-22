package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.MoneyDepositedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.AccountValidationService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal

@ExtendWith(MockKExtension::class)
class DepositUsecaseTest {

    @MockK
    private lateinit var accountOutPort: AccountOutPort

    @MockK
    private lateinit var accountValidationService: AccountValidationService

    @RelaxedMockK
    private lateinit var eventingOutAdapter: EventingOutAdapter

    @InjectMockKs
    private lateinit var depositUsecase: DepositUsecase

    private val validIban = "DE00123456789"

    @Test
    fun `should deposit on valid account`() {
        //Arrange
        val account = Account(
            CustomerNumber("000123"),
            Iban(validIban),
            Money(BigDecimal.ZERO)
        )

        every {
            accountValidationService.isAccountNumberValid(this.any())
        } returns true

        every {
            accountOutPort.deposit(this.any(), this.any())
        } returns Account(
            account.customerNumber,
            Iban(validIban),
            Money(BigDecimal.ONE)
        )

        //Act
        depositUsecase.deposit(validIban, BigDecimal.ONE)

        //Assert
        verify {
            eventingOutAdapter.publishEvent(this.any(MoneyDepositedEvent::class))
        }
    }

    @Test
    fun `should not deposit with non existent account`() {
        //Arrange
        every {
            accountValidationService.isAccountNumberValid(this.any())
        } returns true

        every {
            accountOutPort.deposit(this.any(), this.any())
        } throws AccountNotFoundException("Account not found")

        // Act and Assert
        assertThrows<AccountNotFoundException> {
            depositUsecase.deposit(validIban, BigDecimal.ONE)
        }

        verify(exactly = 0) {
            eventingOutAdapter.publishEvent(this.any(MoneyDepositedEvent::class))
        }
    }

    @Test
    fun `should not deposit with negative deposit value`() {
        //Assert
        assertThrows<RuntimeException> {
            depositUsecase.deposit(validIban, BigDecimal.ONE.negate())
        }

        verify(exactly = 0) {
            eventingOutAdapter.publishEvent(this.any(MoneyDepositedEvent::class))
        }
    }

}
