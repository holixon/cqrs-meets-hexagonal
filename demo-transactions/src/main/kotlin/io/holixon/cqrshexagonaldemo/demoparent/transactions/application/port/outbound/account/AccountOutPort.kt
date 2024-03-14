package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import java.math.BigDecimal

interface AccountOutPort {
    fun findAccount(iban: Iban): Account?
    fun createAccount(account: Account): Account
    fun deposit(iban: Iban, amount: BigDecimal): Account
    fun withdraw(iban: Iban, amount: BigDecimal): Account
}
