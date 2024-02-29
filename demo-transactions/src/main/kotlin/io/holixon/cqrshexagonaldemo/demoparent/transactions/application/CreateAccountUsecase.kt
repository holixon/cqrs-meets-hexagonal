package io.holixon.cqrshexagonaldemo.demoparent.transactions.application

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.searchquery.CreateAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.account.AccountOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.Account

open class CreateAccountUsecase(
    val accountOutPort: AccountOutPort,
    val customerOutPort: CustomerOutPort
) : CreateAccountInPort {

    override fun createAccount(customerNumber: String): Account {
        val account = accountOutPort.findAccount(customerNumber);

        return accountOutPort.createAccount(account!!)
    }

}
