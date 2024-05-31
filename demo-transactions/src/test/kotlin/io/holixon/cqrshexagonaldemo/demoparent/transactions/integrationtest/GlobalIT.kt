package io.holixon.cqrshexagonaldemo.demoparent.transactions.integrationtest

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.account.CreateAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.account.DepositAccountInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.customer.CreateCustomerInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.account.AccountService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.account.Amount
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.common.Name
import org.assertj.core.api.Assertions
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

class GlobalIT : IntegrationTextBase() {

    @Autowired
    private lateinit var createCustomerInPort: CreateCustomerInPort

    @Autowired
    private lateinit var createAccountInPort: CreateAccountInPort

    @Autowired
    private lateinit var depositAccountInPort: DepositAccountInPort

    @Autowired
    private lateinit var accountService: AccountService

    @Test
    fun `should create customer`() {
        // given
        val payload = """
            {
              "name": "Dagobert Duck"
            }
        """.trimIndent()

        // when
        val response = this.mockMvc.perform(
            post("/api/v1/customer/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        )

        // then
        response.andExpect(status().isOk)
            .andExpect(content().string(containsString("\"name\":\"Dagobert Duck\"")))
            .andExpect(content().string(containsString("\"customerNumber\":")))
    }

    @Test
    fun `should create account for existing customer`() {
        // given
        val customer = createCustomerInPort.createCustomer(Name("Richie Rich"))

        val payload = """
            {
              "customerNumber": "${customer.customerNumber.value}"
            }
        """.trimIndent()

        // when
        val response = this.mockMvc.perform(
            post("/api/v1/account/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        )

        // then
        response.andExpect(status().isOk)
            .andExpect(content().string(containsString("\"customerNumber\":\"${customer.customerNumber.value}\"")))
            .andExpect(content().string(containsString("\"iban\":")))
    }


    @Test
    fun `should deposit money on existing account`() {
        // given
        val customer = createCustomerInPort.createCustomer(Name("Charles Montgomery Burns"))
        val account = createAccountInPort.createAccount(customer.customerNumber)
        depositAccountInPort.deposit(account.iban, Amount(BigDecimal.valueOf(123.45)))

        val payload = """
            {
              "iban": "${account.iban}",
              "amount": 111.11
            }
        """.trimIndent()

        // when
        val response = this.mockMvc.perform(
            post("/api/v1/account/deposit")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        )

        // then
        response.andExpect(status().isNoContent)
        val currentAccount = accountService.findAccount(account.iban)
        Assertions.assertThat(currentAccount.balance.amount).isEqualTo(Amount(BigDecimal.valueOf(234.56)))
    }

    //TODO: When the sync with write model with read model is done we should change the test to expect 200 http and not 404.
    @Disabled
    fun `should fetch account balance for existing account`() {
        //given
        val customer = createCustomerInPort.createCustomer(Name("Charles Montgomery Burns"))
        val account = createAccountInPort.createAccount(customer.customerNumber)

        //when
        val response = this.mockMvc.perform(
            get("/api/v1/account/balance")
                .queryParam("accountNumber", account.iban.value)
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON)
        )

        // then
        response.andExpect(status().isOk)
    }
}