package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban

interface AccountOutPort {
    fun findAccount(iban: Iban): Account?
    fun createAccount(account: Account): Account
    fun deposit(account: Account): Account
    fun withdraw(account: Account): Account
}
