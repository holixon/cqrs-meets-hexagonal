package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.projection

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Money

data class AccountBalance(
    var iban: Iban,
    var balance: Money
)
