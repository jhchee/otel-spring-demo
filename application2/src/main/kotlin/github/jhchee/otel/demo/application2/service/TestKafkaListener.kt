package github.jhchee.otel.demo.application2.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class TestKafkaListener {
    @KafkaListener(topics = ["test-otel"], groupId = "test-otel-application-1")
    fun listen(message: String) {
        println("Received Message in group test-otel: $message")
    }
}
