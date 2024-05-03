package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.common.Name

data class Customer(
    var customerNumber: CustomerNumber,
    var name: Name
)
