package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account

import java.math.BigDecimal

/**
 * For now this class can be value class in future when currency is introduced must be revised to support currency
 */
@JvmInline
value class Amount(val value: BigDecimal) {
    init {
        require(value > BigDecimal.ZERO){
            "Amount must not be negative or zero"
        }
    }
}
