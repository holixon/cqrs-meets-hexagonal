package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.usecase.DepositUsecase
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.MoneyDepositedEvent
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

    @RelaxedMockK
    private lateinit var eventingOutAdapter: EventingOutAdapter

    @InjectMockKs
    private lateinit var depositUsecase: DepositUsecase

    private val validIban = Iban("DE00123456789")

    @Test
    fun `should deposit on valid account`() {
        //Arrange
        val account = Account(
            CustomerNumber("000123"),
            validIban,
            Money(Amount(BigDecimal.valueOf(123.45)))
        )

        every {
            accountOutPort.findAccount(any())
        } returns account

        every {
            accountOutPort.deposit(any(), Amount(BigDecimal.ONE))
        } returns Account(
            account.customerNumber,
            validIban,
            Money(Amount(BigDecimal.valueOf(124.45)))
        )

        //Act
        depositUsecase.deposit(validIban, Amount(BigDecimal.ONE))

        //Assert
        verify {
            eventingOutAdapter.publishEvent(any(MoneyDepositedEvent::class))
        }
    }

    @Test
    fun `should not deposit with non existent account`() {
        //Arrange
        every {
            accountOutPort.deposit(any(Account::class), Amount(BigDecimal.ONE))
        } throws AccountNotFoundException("Account not found")

        every {
            accountOutPort.findAccount(any())
        } returns Account(CustomerNumber("abc"), validIban, Money(Amount(BigDecimal.ONE)))

        // Act and Assert
        assertThrows<AccountNotFoundException> {
            depositUsecase.deposit(validIban, Amount(BigDecimal.ONE))
        }

        verify(exactly = 0) {
            eventingOutAdapter.publishEvent(any(MoneyDepositedEvent::class))
        }
    }

    @Test
    fun `should not deposit with negative deposit value`() {
        //Assert
        assertThrows<RuntimeException> {
            depositUsecase.deposit(validIban, Amount(BigDecimal.ONE.negate()))
        }

        verify(exactly = 0) {
            eventingOutAdapter.publishEvent(any(MoneyDepositedEvent::class))
        }
    }

}
