package io.holixon.cqrshexagonaldemo.demoparent.transactions.infrastructure.database.flyway

import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
open class FlywayConfig(
    @Autowired private val dataSource: DataSource,
    @Value("\${spring.flyway.schemas[0]}") private val writeSchema: String,
    @Value("\${spring.flyway.schemas[1]}") private val readSchema: String,
    @Value("\${spring.flyway.locations[0]}") private val writeLocation: String,
    @Value("\${spring.flyway.locations[1]}") private val readLocation: String,

    ) {

    @PostConstruct
    fun migrate() {
        // Migrate write database
        Flyway.configure()
            .dataSource(dataSource)
            .schemas(writeSchema)
            .locations(writeLocation)
            .load()
            .migrate()

        // Migrate read database
        Flyway.configure()
            .dataSource(dataSource)
            .schemas(readSchema)
            .locations(readLocation)
            .load()
            .migrate()
    }
}
