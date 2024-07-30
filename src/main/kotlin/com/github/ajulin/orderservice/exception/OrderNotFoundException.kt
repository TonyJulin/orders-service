package com.github.ajulin.orderservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
data class OrderNotFoundException (override val message: String) : RuntimeException()