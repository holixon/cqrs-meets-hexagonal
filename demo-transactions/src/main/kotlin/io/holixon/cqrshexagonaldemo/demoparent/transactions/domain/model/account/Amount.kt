package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account

import java.math.BigDecimal

class Amount(val value: BigDecimal) {
    init {
        require(value > BigDecimal.ZERO){
            "Amount must not be negative or zero"
        }
    }
}