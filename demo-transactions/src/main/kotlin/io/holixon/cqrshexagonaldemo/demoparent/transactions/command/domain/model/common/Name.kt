package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.common

@JvmInline
value class Name(val value: String) {
    override fun toString(): String {
        return value
    }
}
