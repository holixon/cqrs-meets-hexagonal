package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain

import java.time.Instant

class Account(
    var id: Long,
    var accountNumber: String?,
    var customerNumber: String?,
    var created: Instant?,
    var updated: Instant?,
    var createdBy: String?,
    var updatedBy: String?

)