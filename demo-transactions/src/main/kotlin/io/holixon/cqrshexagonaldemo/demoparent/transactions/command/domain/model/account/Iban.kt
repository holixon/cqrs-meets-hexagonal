package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account

@JvmInline
value class Iban(val value: String) {

    override fun toString(): String {
        return value
    }
}
