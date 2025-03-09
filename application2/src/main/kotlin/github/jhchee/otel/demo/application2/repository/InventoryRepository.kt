package github.jhchee.otel.demo.application2.repository

import github.jhchee.otel.demo.application2.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository: JpaRepository<Item, Long> {
}