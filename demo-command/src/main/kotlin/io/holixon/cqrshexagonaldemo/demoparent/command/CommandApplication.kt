package io.holixon.cqrshexagonaldemo.demoparent.command

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class CommandApplication

fun main(args: Array<String>) {
    runApplication<CommandApplication>(*args)

}
