package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber

data class AccountCreatedEvent(val customerNumber: CustomerNumber, val iban: Iban) : Event
