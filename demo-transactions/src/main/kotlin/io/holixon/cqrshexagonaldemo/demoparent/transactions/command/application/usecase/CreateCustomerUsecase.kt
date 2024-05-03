package io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.usecase

import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.inbound.customer.CreateCustomerInPort
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.customer.CustomerService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.application.port.outbound.eventing.EventingOutAdapter
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.common.Name
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.customer.Customer
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.model.event.CustomerCreatedEvent
import io.holixon.cqrshexagonaldemo.demoparent.transactions.command.domain.service.CustomerNumberCreationService
import io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.framework.Usecase
import mu.KLogging

@Usecase
open class CreateCustomerUsecase(
    private val customerService: CustomerService,
    private val customerNumberCreationService: CustomerNumberCreationService,
    private val eventingOutAdapter: EventingOutAdapter
) : CreateCustomerInPort {

    companion object : KLogging()

    override fun createCustomer(customerName: Name): Customer {
        logger().info { "register new customer ${customerName.value}" }
        val customer = Customer(
                customerNumber = customerNumberCreationService.generateNextCustomerNumber(),
                name = customerName)
        val createdCustomer = customerService.createCustomer(customer)

        val customerCreatedEvent = CustomerCreatedEvent(customerName = customerName, customerNumber = createdCustomer.customerNumber)
        eventingOutAdapter.publishEvent(customerCreatedEvent)

        logger().info { "newly registered customer: $createdCustomer" }
        return createdCustomer
    }

}
