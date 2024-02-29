package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.Account

interface AccountOutPort {
    fun findAccount(accountNumber: String): Account?
    fun createAccount(account: Account): Account
}