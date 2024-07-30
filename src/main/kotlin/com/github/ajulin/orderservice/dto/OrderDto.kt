package com.github.ajulin.orderservice.dto

data class OrderDto (
    val id: Long,
    val numApples: Int,
    val numOranges: Int,
    val appleOrderCost: Float,
    val orangeOrderCost: Float,
    val orderTotalCost: Float
)