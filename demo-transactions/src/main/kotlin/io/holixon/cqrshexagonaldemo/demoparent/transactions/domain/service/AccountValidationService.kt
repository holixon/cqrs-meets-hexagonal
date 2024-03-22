package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service

import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.DomainService

@DomainService
class AccountValidationService {

    /**
     * Validates an account number using the specified IBAN pattern.
     *
     * @param accountNumber The account number to validate.
     * @return `true` if the account number is valid according to the IBAN pattern, otherwise `false`.
     */
    fun isAccountNumberValid(accountNumber: String): Boolean {
        val ibanPattern = Regex("^DE\\d{2}\\d{8}\\d{10}\$")
        return ibanPattern.matches(accountNumber)
    }

}
