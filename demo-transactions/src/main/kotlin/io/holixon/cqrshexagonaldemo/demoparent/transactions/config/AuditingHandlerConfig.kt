package io.holixon.cqrshexagonaldemo.demoparent.transactions.config

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.core.annotation.Order
import org.springframework.data.auditing.AuditingHandler

@Configuration
open class AuditingHandlerConfig(
        val auditingHandler: AuditingHandler
) {


    @EventListener(ApplicationReadyEvent::class)
    @Order(1)
    fun doAfterStartup() {
        auditingHandler.setModifyOnCreation(false)
    }
}
