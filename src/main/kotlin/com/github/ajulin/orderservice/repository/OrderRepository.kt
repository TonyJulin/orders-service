package com.github.ajulin.orderservice.repository

import com.github.ajulin.orderservice.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Long> {
    fun findOrderById(id: Long): Order
}