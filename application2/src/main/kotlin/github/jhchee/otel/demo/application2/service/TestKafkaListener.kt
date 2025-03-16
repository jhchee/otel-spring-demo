package github.jhchee.otel.demo.application2.service

import io.opentelemetry.api.trace.Span
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class TestKafkaListener {
    @KafkaListener(topics = ["test-otel"], groupId = "test-otel-application-1")
    fun listen(message: String) {
        val currentSpan = Span.current()
        val spanContext = currentSpan.spanContext
        println("Current Span ID: ${spanContext.spanId}")
        println("Current Trace ID: ${spanContext.traceId}")
        println("Received Message in group test-otel: $message")
    }
}
