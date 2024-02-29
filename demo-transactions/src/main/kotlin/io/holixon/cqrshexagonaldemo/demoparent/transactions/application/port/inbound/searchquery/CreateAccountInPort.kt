package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.searchquery

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.Account

interface CreateAccountInPort {
    fun createAccount(customerNumber: String): Account
}