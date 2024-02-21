package io.holixon.cqrshexagonaldemo.demoparent.command.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.Instant
import java.util.*


@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")

open class AuditingConfig {
    @Bean
    open fun aircraftdocumentAuditorProvider(): AuditorAware<String> {
        return AuditorAware { Optional.of("cqrs-meets-hexagonal") }
    }

    @Bean // Makes ZonedDateTime compatible with auditing fields
    open fun auditingDateTimeProvider(): DateTimeProvider {
        return DateTimeProvider { Optional.of(Instant.now()) }
    }
}
