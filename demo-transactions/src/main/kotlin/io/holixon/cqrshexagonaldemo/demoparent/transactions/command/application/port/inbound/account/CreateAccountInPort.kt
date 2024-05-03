package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Account
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.CustomerNumber

interface CreateAccountInPort {
    fun createAccount(customerNumber: CustomerNumber): Account
}