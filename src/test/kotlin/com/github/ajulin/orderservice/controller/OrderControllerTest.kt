package com.github.ajulin.orderservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ajulin.orderservice.dto.OrderRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(OrderController::class)
class OrderControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    private var mapper: ObjectMapper = ObjectMapper()

    @Test
    fun orderShouldReturnSummary() {
        val numApples = 5
        val numOranges = 6
        // BOGO
        val expectedAppleCost = (3.00).toFloat()
        val expectedNumApplesResult = numApples * 2
        // Buy 3 for the price of 2
        val expectedOrangeCost = (1.00).toFloat()

        val orderInput = createOrderRequest(numApples, numOranges)
        mockMvc.post("/orders") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(orderInput)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.numApples") {
                value(expectedNumApplesResult)
            }
            jsonPath("$.numOranges") {
                value(numOranges)
            }
            jsonPath("$.appleCost") {
                value(expectedAppleCost)
            }
            jsonPath("$.orangeCost") {
                value(expectedOrangeCost)
            }
            jsonPath("$.totalCost") {
                value(expectedAppleCost + expectedOrangeCost)
            }
        }
    }

    @Test
    fun numberOfOrangesNotDivisibleByThreeSummaryReturn() {
        val numApples = 5
        val numOranges = 11
        // BOGO
        val expectedAppleCost = (3.00).toFloat()
        val expectedNumApplesResult = numApples * 2
        // Buy 3 for the price of 2
        val expectedOrangeCost = (2.00).toFloat()

        val orderInput = createOrderRequest(numApples, numOranges)
        mockMvc.post("/orders") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(orderInput)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.numApples") {
                value(expectedNumApplesResult)
            }
            jsonPath("$.numOranges") {
                value(numOranges)
            }
            jsonPath("$.appleCost") {
                value(expectedAppleCost)
            }
            jsonPath("$.orangeCost") {
                value(expectedOrangeCost)
            }
            jsonPath("$.totalCost") {
                value(expectedAppleCost + expectedOrangeCost)
            }
        }
    }

    @Test
    fun badRequestOrderingLessThanZero() {
        val numApples = 5
        val numOranges = -1
        val orderInput = createOrderRequest(numApples, numOranges)
        mockMvc.post("/orders") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(orderInput)
        }.andExpect {
            status { isBadRequest() }
        }
    }

    private fun createOrderRequest(numApples: Int, numOranges: Int): OrderRequest {
        val orderRequest = OrderRequest()
        orderRequest.numApples = numApples
        orderRequest.numOranges = numOranges
        return orderRequest
    }


}