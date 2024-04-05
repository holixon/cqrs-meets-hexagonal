package io.holixon.cqrshexagonaldemo.demoparent.transactions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
open class CommandApplication

fun main(args: Array<String>) {
    runApplication<CommandApplication>(*args) { }
}
