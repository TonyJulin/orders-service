package com.github.ajulin.orderservice.controller

import com.github.ajulin.orderservice.dto.OrderDto
import com.github.ajulin.orderservice.request.OrderRequest
import com.github.ajulin.orderservice.service.OrderService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders")
class OrderController {

    @Autowired
    private lateinit var orderService: OrderService

    @PostMapping("/create")
    fun createOrder(@Valid @RequestBody orderRequest: OrderRequest): ResponseEntity<OrderDto> {
        return ResponseEntity.ok(orderService.createOrder(orderRequest))
    }

    @GetMapping
    fun getAllOrders(): ResponseEntity<List<OrderDto>> {
        return ResponseEntity.ok(orderService.getAllOrders())
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Long): ResponseEntity<OrderDto> {
        return ResponseEntity.ok(orderService.getOrderById(id))
    }
}