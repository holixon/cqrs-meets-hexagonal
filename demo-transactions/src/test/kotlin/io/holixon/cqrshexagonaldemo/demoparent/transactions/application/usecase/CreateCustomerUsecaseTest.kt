package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.CustomerCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.CustomerNumberCreationService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class CreateCustomerUsecaseTest {

    @MockK
    private lateinit var customerOutPort: CustomerOutPort

    @MockK
    private lateinit var customerNumberCreationService: CustomerNumberCreationService

    @RelaxedMockK
    private lateinit var eventingOutAdapter: EventingOutAdapter

    @InjectMockKs
    private lateinit var createCustomerUsecase: CreateCustomerUsecase

    @Test
    fun `should create a new customer`() {
        // angenommen
        val customerNumber = CustomerNumber("000123")
        every { customerNumberCreationService.generateNextCustomerNumber() } returns customerNumber
        val customerName = Name("Richi Rich")
        val customer = Customer(customerNumber, customerName)

        every { customerOutPort.createCustomer(this.any()) } returns customer

        // wenn
        val createdCustomer = createCustomerUsecase.createCustomer(customerName)

        // dann
        Assertions.assertThat(createdCustomer).isNotNull
        verify { customerOutPort.createCustomer(customer) }
        verify { eventingOutAdapter.publishEvent(CustomerCreatedEvent(customerName, customerNumber)) }
    }
}