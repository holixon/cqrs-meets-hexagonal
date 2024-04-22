package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account

import java.math.BigDecimal

/**
 * For now this class can be value class in future when currency is introduced must be revised to support currency
 */
@JvmInline
value class Amount(val value: BigDecimal) {
    operator fun plus(value2: Amount) : Amount {
        return Amount(this.value.plus(value2.value))
    }

    override fun toString(): String {
        return value.toString()
    }
}
