package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer

@JvmInline
value class CustomerNumber(val value: String) {
    override fun toString(): String {
        return value
    }
}
