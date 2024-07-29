package com.github.ajulin.orderservice.controller

import com.github.ajulin.orderservice.dto.OrderRequest
import com.github.ajulin.orderservice.dto.OrderResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController {
    @PostMapping("/orders")
    fun createOrder(@Valid @RequestBody orderRequest: OrderRequest): ResponseEntity<OrderResponse> {
        val response = OrderResponse()
        // Buy one get one free
        response.numApples = orderRequest.numApples * 2
        response.numOranges = orderRequest.numOranges
        response.appleCost = (orderRequest.numApples * 0.60).toFloat()
        // 3 oranges for the price of 2
        response.orangeCost = ((orderRequest.numOranges - orderRequest.numOranges/3) * 0.25).toFloat()
        return ResponseEntity.ok(response)
    }
}