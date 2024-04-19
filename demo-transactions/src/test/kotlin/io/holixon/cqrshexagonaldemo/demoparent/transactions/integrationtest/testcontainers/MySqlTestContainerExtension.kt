package io.holixon.cqrshexagonaldemo.demoparent.transactions.integrationtest.testcontainers

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.MySQLContainer


class MySqlTestContainerExtension : BeforeAllCallback, AfterAllCallback {

    companion object {
        const val CONTAINER_IMAGE_TAG = "mysql:8.0.36"
    }

    private lateinit var mysql: MySQLContainer<*>

    override fun beforeAll(p0: ExtensionContext?) {
        mysql = MySQLContainer(CONTAINER_IMAGE_TAG)
            .withDatabaseName("cqrs-meets-hexagonal-test")
            .withUsername("dbuser")
            .withPassword("password")

        mysql.start()

        System.setProperty("spring.datasource.url", mysql.jdbcUrl)
        System.setProperty("spring.datasource.username", mysql.username)
        System.setProperty("spring.datasource.password", mysql.password)
    }

    override fun afterAll(p0: ExtensionContext?) {
        // do nothing, Testcontainers handles container shutdown
    }
}