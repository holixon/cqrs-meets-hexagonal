package io.holixon.cqrshexagonaldemo.demoparent.command

import io.holixon.cqrshexagonaldemo.demoparent.command.application.SearchUsecase
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
open class CommandApplication

fun main(args: Array<String>) {
    runApplication<CommandApplication>(*args) {
        addInitializers(
            beans {
                bean<SearchUsecase>()
            }
        )
    }

}
