package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer

import io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter.outbound.customer.entity.CustomerEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaCustomerOutAdapter : CrudRepository<CustomerEntity, String> {
    fun findByCustomerNumber(customerNumber: String): CustomerEntity?
}