package io.holixon.cqrshexagonaldemo.demoparent.transactions.shared.infrastructure.database.flyway

import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
open class FlywayConfig(
    @Autowired private val dataSource: DataSource,
    @Value("\${spring.flyway.schemas[0]}") private val commandSchema: String,
    @Value("\${spring.flyway.schemas[1]}") private val querySchema: String,
    @Value("\${spring.flyway.locations[0]}") private val commandLocation: String,
    @Value("\${spring.flyway.locations[1]}") private val queryLocation: String,

    ) {

    @PostConstruct
    fun migrate() {
        // Migrate command model database
        Flyway.configure()
            .dataSource(dataSource)
            .schemas(commandSchema)
            .locations(commandLocation)
            .load()
            .migrate()

        // Migrate read model database
        Flyway.configure()
            .dataSource(dataSource)
            .schemas(querySchema)
            .locations(queryLocation)
            .load()
            .migrate()
    }
}
