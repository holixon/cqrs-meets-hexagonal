package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common

@JvmInline
value class Name(val value: String) {
    override fun toString(): String {
        return value
    }
}
