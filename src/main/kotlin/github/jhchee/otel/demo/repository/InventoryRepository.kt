package github.jhchee.otel.demo.repository

import github.jhchee.otel.demo.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository: JpaRepository<Item, Long> {
}