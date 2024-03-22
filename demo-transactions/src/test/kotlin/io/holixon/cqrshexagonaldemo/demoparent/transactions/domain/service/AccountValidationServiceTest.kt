package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AccountValidationServiceTest {

    private val accountValidationService = AccountValidationService()

    @Test
    fun `valid account number should return true`() {
        // Arrange
        val validAccountNumber = "DE01123456780000000011"

        // Act
        val result = accountValidationService.isAccountNumberValid(validAccountNumber)

        // Assert
        assertTrue(result)
    }

    @Test
    fun `invalid account number should return false`() {
        // Arrange
        val validAccountNumber = "DE0112345678000000"

        // Act
        val result = accountValidationService.isAccountNumberValid(validAccountNumber)

        // Assert
        assertFalse(result)
    }

    @Test
    fun `account number with non-DE prefix should return false`() {
        // Arrange
        val nonDEAccountNumber = "PT01123456780000000001"

        // Act
        val result = accountValidationService.isAccountNumberValid(nonDEAccountNumber)

        // Assert
        assertFalse(result)
    }

}
