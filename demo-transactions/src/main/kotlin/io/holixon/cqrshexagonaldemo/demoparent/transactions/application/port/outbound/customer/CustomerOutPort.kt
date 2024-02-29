package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.Customer

interface CustomerOutPort {
    fun createAccount(searchResultItem: Customer): Customer
}