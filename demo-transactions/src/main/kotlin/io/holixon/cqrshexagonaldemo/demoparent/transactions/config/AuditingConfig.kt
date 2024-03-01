package io.holixon.cqrshexagonaldemo.demoparent.transactions.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*


@Configuration
@EnableJpaAuditing
open class AuditingConfig {
    @Bean
    open fun transactionsAuditorProvider(): AuditorAware<String> {
        return AuditorAware { Optional.of("cqrs-meets-hexagonal") }
    }

}
