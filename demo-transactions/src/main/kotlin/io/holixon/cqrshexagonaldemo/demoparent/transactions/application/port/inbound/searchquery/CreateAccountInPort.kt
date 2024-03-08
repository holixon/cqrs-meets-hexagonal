package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.searchquery

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber

interface CreateAccountInPort {
    fun createAccount(customerNumber: CustomerNumber): Account
}