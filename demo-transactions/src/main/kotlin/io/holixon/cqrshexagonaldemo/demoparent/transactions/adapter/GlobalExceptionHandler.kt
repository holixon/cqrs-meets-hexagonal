package io.holixon.cqrshexagonaldemo.demoparent.transactions.adapter

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.exception.AccountNotFoundException
import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.exception.InvalidInputException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException::class)
    fun handleException(request: HttpServletRequest, exception: AccountNotFoundException): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }

    @ExceptionHandler(InvalidInputException::class)
    fun handleException(request: HttpServletRequest, exception: InvalidInputException): ResponseEntity<Unit> {
        return ResponseEntity.badRequest().build()
    }

}
