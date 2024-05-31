package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.usecase.AccountBalanceUsecase
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Money
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.domain.model.projection.AccountBalance
import io.holixon.cqrshexagonaldemo.demoparent.transactions.query.application.port.outbound.accountbalance.AccountBalanceService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal

@ExtendWith(MockKExtension::class)
class AccountBalanceUsecaseTest {

    @MockK
    private lateinit var accountBalanceService: AccountBalanceService

    @InjectMockKs
    private lateinit var accountBalanceUsecase: AccountBalanceUsecase

    @Test
    fun `should fetch account balance`() {
        //arrange
        val validIban = Iban("iban test")
        val accountBalance = AccountBalance(
            validIban,
            Money(Amount(BigDecimal.ONE))
        )
        every {
            accountBalanceService.getAccountBalance(this.any())
        } returns accountBalance
        //Act
        val actualBalance = accountBalanceUsecase.getAccountBalance(validIban.value)
        //assert
        assertThat(actualBalance).isNotNull
        assertEquals(accountBalance, actualBalance)

    }

    @Test
    fun `should throw account not found for invalid account`() {
        //arrange
        val iban = Iban("Invalid iban")
        every {
            accountBalanceService.getAccountBalance(iban)
        } throws AccountNotFoundException("Account not found for Iban: ${iban.value}")
        //Act & assert
        assertThrows<AccountNotFoundException> {
            accountBalanceUsecase.getAccountBalance(iban.value)
        }
    }
}