package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.account.AccountService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.usecase.WithdrawUseCase
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.exception.InsufficientFundsException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.MoneyWithdrawnEvent
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
class WithdrawUsecaseTest {

    @MockK
    private lateinit var accountService: AccountService

    @RelaxedMockK
    private lateinit var eventingOutAdapter: EventingOutAdapter

    @InjectMockKs
    private lateinit var withdrawUseCase: WithdrawUseCase

    private val validIban = Iban("DE00123456789")

    @Test
    fun `Should withdrawn on valid account`() {
        //arrange
        val account = Account(
            CustomerNumber("000123"),
            validIban,
            Money(Amount(BigDecimal.valueOf(123.45)))
        )

        every {
            accountService.findAccount(any())
        } returns account

        every {
            accountService.withdraw(any(), Amount(BigDecimal.ONE))
        } returns Account(
            account.customerNumber,
            validIban,
            Money(Amount(BigDecimal.valueOf(122.45)))
        )

        //Act
        withdrawUseCase.withdraw(validIban, Amount(BigDecimal.ONE))

        //Assert
        verify {
            eventingOutAdapter.publishEvent(any(MoneyWithdrawnEvent::class))
        }
    }

    @Test
    fun `should not withdrawn with insufficient funds on valid account`() {
        //arrange
        val account = Account(
            CustomerNumber("000123"),
            validIban,
            Money(Amount(BigDecimal.valueOf(0)))
        )

        every {
            accountService.findAccount(any())
        } returns account

        every {
            accountService.withdraw(any(), Amount(BigDecimal.ONE))
        } throws (InsufficientFundsException("Not enough funds to be withdrawn"))

        //Act
        assertThrows<InsufficientFundsException> {
            withdrawUseCase.withdraw(validIban, Amount(BigDecimal.ONE))
        }

    }

    @Test
    fun `Should not withdrawn from an non existent account`() {
        //Arrange
        every {
            accountService.withdraw(any(Account::class), Amount(BigDecimal.ONE))
        } throws AccountNotFoundException("Account not found")

        every {
            accountService.findAccount(any())
        } returns Account(CustomerNumber("abc"), validIban, Money(Amount(BigDecimal.ONE)))

        // Act and Assert
        assertThrows<AccountNotFoundException> {
            withdrawUseCase.withdraw(validIban, Amount(BigDecimal.ONE))
        }

        verify(exactly = 0) {
            eventingOutAdapter.publishEvent(any(MoneyWithdrawnEvent::class))
        }
    }

    @Test
    fun `should not withdraw with negative deposit value`() {
        //Assert
        assertThrows<RuntimeException> {
            withdrawUseCase.withdraw(validIban, Amount(BigDecimal.ONE.negate()))
        }

        verify(exactly = 0) {
            eventingOutAdapter.publishEvent(any(MoneyWithdrawnEvent::class))
        }
    }

}
