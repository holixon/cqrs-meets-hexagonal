package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.mapper.AccountMapper
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.model.AccountDto
import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.inbound.searchquery.service.gen.CreateAccountApi
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.searchquery.CreateAccountInPort
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
open class CreateAccountInAdapter @Autowired constructor(
    private val inPort: CreateAccountInPort,
    private val mapper: AccountMapper
) : CreateAccountApi {

    companion object : KLogging()

    override fun createAccount(accountDto: AccountDto?): ResponseEntity<MutableList<AccountDto>> {
        TODO("Not yet implemented")
    }

}