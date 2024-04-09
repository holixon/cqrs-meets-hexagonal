package io.holixon.cqrshexagonaldemo.demoparent.transactions.integrationtest

import io.holixon.cqrshexagonaldemo.demoparent.transactions.integrationtest.testcontainers.MySqlTestContainerExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("IT")
@Testcontainers(disabledWithoutDocker = true)
@ExtendWith(
        MySqlTestContainerExtension::class
)
abstract class IntegrationTextBase {

    @Autowired
    protected lateinit var mockMvc: MockMvc
}