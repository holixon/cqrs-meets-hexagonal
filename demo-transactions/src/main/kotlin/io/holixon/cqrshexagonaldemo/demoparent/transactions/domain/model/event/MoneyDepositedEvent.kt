package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Money

data class MoneyDepositedEvent(val iban: Iban, val balance: Money, val timeStamp: Long) : Event
