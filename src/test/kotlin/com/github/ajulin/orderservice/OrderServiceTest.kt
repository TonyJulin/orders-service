package com.github.ajulin.orderservice

import com.github.ajulin.orderservice.dto.OrderDto
import com.github.ajulin.orderservice.model.Order
import com.github.ajulin.orderservice.repository.OrderRepository
import com.github.ajulin.orderservice.service.OrderService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderServiceTest {
    private final val orderRepository: OrderRepository = mockk()
    val orderService: OrderService = OrderService(orderRepository)

    @Test
    fun getOrderById() {
        val dbOrder: Order = createDbOrderTestData()
        every { orderRepository.findOrderById(1) } returns dbOrder
        val result: OrderDto = orderService.getOrderById(1)
        verify(exactly = 1) { orderRepository.findOrderById(1) }
        assertThat(result.appleOrderCost).isEqualTo(dbOrder.appleOrderCost)
        assertThat(result.orangeOrderCost).isEqualTo(dbOrder.orangeOrderCost)
        assertThat(result.orderTotalCost).isEqualTo(dbOrder.appleOrderCost + dbOrder.orangeOrderCost)
    }

    @Test
    fun getOrderByIdDoesNotExist() {

    }

    @Test
    fun getAllOrders() {

    }

    @Test
    fun createNewOrder() {

    }

    private fun createDbOrderTestData(): Order {
        val order = Order()
        order.numApples = 2
        order.numOranges = 3
        order.appleOrderCost = (0.60).toFloat()
        order.orangeOrderCost = (0.50).toFloat()
        return order
    }
}