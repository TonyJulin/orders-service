package com.github.ajulin.orderservice.request

import jakarta.validation.constraints.Min

data class OrderRequest(
    @Min(value = 0, message = "The number of apples must be no less than 0")
    var numApples: Int = 0,

    @Min(value = 0, message = "The number of oranges must be no less than 0")
    var numOranges: Int = 0
)