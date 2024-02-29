package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain

import java.time.Instant

class Customer(
    var id: Long,
    var customerNumber: String?,
    var created: Instant?,
    var updated: Instant?,
    var createdBy: String?,
    var updatedBy: String?

)