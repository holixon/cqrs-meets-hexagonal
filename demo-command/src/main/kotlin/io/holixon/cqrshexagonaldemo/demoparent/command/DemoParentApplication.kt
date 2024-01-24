package io.holixon.cqrshexagonaldemo.demoparent.command

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoParentApplication

fun main(args: Array<String>) {
	runApplication<DemoParentApplication>(*args)
}
