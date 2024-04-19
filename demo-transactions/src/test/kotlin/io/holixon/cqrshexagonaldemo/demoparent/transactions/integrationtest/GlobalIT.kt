package io.holixon.cqrshexagonaldemo.demoparent.transactions.integrationtest

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.account.AccountBalanceInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.customer.CreateCustomerInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class GlobalIT : IntegrationTextBase() {

    @Autowired
    private lateinit var createCustomerInPort: CreateCustomerInPort

    @Autowired
    private lateinit var accountBalanceInPort: AccountBalanceInPort

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
    fun `should fetch account balance for existing account`() {
        //given
        val accountBalance = accountBalanceInPort.getAccountBalance("123")

        //when
        val response = this.mockMvc.perform(
            get("/api/v1/account/balance")
                .queryParam("accountNumber", "123")
                .accept(MediaType.ALL)
                .contentType(MediaType.APPLICATION_JSON)
        )

        // then
        response.andExpect(status().isOk)
    }
}