package io.holixon.cqrshexagonaldemo.demoparent.transactions.integrationtest.testcontainers

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.MySQLContainer


class MySqlTestContainerExtension : BeforeAllCallback, AfterAllCallback {

    companion object {
        const val CONTAINER_IMAGE_TAG = "mysql:8.0.36"
        private const val ROOT_PASSWORD_ENV = "MYSQL_ROOT_PASSWORD"
    }

    private lateinit var mysql: MySQLContainer<*>

    override fun beforeAll(p0: ExtensionContext?) {
        mysql = MySQLContainer(CONTAINER_IMAGE_TAG)
            .withDatabaseName("cqrs-meets-hexagonal-test")
            .withUsername("root")
            .withPassword("password")
            .withEnv(ROOT_PASSWORD_ENV, "password")

        mysql.start()

        System.setProperty("spring.datasource.url", mysql.jdbcUrl)
        System.setProperty("spring.datasource.username", mysql.username)
        System.setProperty("spring.datasource.password", mysql.password)
    }

    override fun afterAll(p0: ExtensionContext?) {
        // do nothing, Testcontainers handles container shutdown
    }
}