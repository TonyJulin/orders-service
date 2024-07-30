package com.github.ajulin.orderservice.response

class OrderResponse {
    var numOranges: Int = 0
    var numApples: Int = 0
    var appleCost: Float = 0.0f
    var orangeCost: Float = 0.0f

    fun getTotalCost(): Float {
        return appleCost + orangeCost
    }
}