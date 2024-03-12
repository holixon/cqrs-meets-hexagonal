package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber

data class Account(
        var customerNumber: CustomerNumber,
        var iban: Iban

)