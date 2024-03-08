package io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.service

import io.holixon.cqrshexagonaldemo.demoparent.transactions.domain.model.account.Iban
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong

@Service
class IbanCreationService {

    // TODO: read last iban as seed for generator
    private val generator: AtomicLong = AtomicLong()

    fun generateNextIban(): Iban {
        val accountNumber = String.format("%010d", generator.incrementAndGet())
        val bankNumber = "12345678"
        val checkSum = "01" // TODO: make smarter
        return Iban("DE$checkSum$bankNumber$accountNumber")
    }
}