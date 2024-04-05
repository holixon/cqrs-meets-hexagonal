package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.CustomerNumber
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.CustomerCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.CustomerNumberCreationService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class CreateCustomerUsecaseTest {

    @Mock
    private lateinit var customerOutPort: CustomerOutPort
    @Mock
    private lateinit var customerNumberCreationService: CustomerNumberCreationService
    @Mock
    private lateinit var eventingOutAdapter: EventingOutAdapter

    @InjectMocks
    private lateinit var createCustomerUsecase: CreateCustomerUsecase

    @Test
    fun `should create a new customer`() {
      // angenommen
        val customerNumber = CustomerNumber("000123")
        whenever(customerNumberCreationService.generateNextCustomerNumber()).thenReturn(customerNumber)
        val customerName = Name("Richi Rich")
        val customer = Customer(customerNumber, customerName)
        whenever(customerOutPort.createCustomer(any())).thenReturn(customer)

      // wenn
        val createdCustomer = createCustomerUsecase.createCustomer(customerName)

        // dann
        Assertions.assertThat(createdCustomer).isNotNull
        verify(customerOutPort).createCustomer(customer)
        Mockito.verify(eventingOutAdapter).publishEvent(CustomerCreatedEvent(customerName, customerNumber))
    }
}