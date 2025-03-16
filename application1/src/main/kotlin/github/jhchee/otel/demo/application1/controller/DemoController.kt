package github.jhchee.otel.demo.application1.controller

import github.jhchee.otel.demo.application1.model.Item
import github.jhchee.otel.demo.application1.repository.InventoryRepository
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class DemoController(private val inventoryRepository: InventoryRepository, private val sqsTemplate: SqsTemplate, private val kafkaTemplate: KafkaTemplate<String, String>) {

    @GetMapping("/hello")
    fun hello(): String {
        inventoryRepository.save(Item().apply {
            name = "Item #" + Random.nextInt()
        })
        kafkaTemplate.send("test-otel", "hello");
        return "Hello, World!"
    }
}