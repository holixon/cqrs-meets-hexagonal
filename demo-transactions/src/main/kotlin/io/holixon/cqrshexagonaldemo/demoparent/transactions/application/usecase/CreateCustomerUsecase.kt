package io.holixon.cqrshexagonaldemo.demoparent.transactions.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.inbound.customer.CreateCustomerInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.customer.CustomerOutPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.event.CustomerCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service.CustomerNumberCreationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.framework.Usecase

@Usecase
open class CreateCustomerUsecase(
        private val customerOutPort: CustomerOutPort,
        private val customerNumberCreationService: CustomerNumberCreationService,
        private val eventingOutAdapter: EventingOutAdapter
) : CreateCustomerInPort {

    override fun createCustomer(customerName: Name): Customer {
        val customer = Customer(
                customerNumber = customerNumberCreationService.generateNextCustomerNumber(),
                name = customerName)
        val createdCustomer = customerOutPort.createCustomer(customer)

        val customerCreatedEvent = CustomerCreatedEvent(customerName = customerName, customerNumber = createdCustomer.customerNumber)
        eventingOutAdapter.publishEvent(customerCreatedEvent)

        return createdCustomer
    }

}
