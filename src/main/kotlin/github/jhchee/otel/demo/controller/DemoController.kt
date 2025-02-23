package github.jhchee.otel.demo.controller

import github.jhchee.otel.demo.model.Item
import github.jhchee.otel.demo.repository.InventoryRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class DemoController(private val inventoryRepository: InventoryRepository) {

    @GetMapping("/hello")
    fun hello(): String {
        inventoryRepository.save(Item().apply {
            name = "Item #" + Random.nextInt()
        })
        return "Hello, World!"
    }
}