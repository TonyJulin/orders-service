package com.github.ajulin.orderservice.service

import com.github.ajulin.orderservice.dto.OrderDto
import com.github.ajulin.orderservice.exception.OrderNotFoundException
import com.github.ajulin.orderservice.model.Order
import com.github.ajulin.orderservice.repository.OrderRepository
import com.github.ajulin.orderservice.request.OrderRequest
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class OrderService(private val repository: OrderRepository) {

    private fun convertDBEntityToDto(orderEntity: Order): OrderDto {
        return OrderDto(
            id = orderEntity.id,
            numApples = orderEntity.numApples,
            numOranges = orderEntity.numOranges,
            appleOrderCost = orderEntity.appleOrderCost,
            orangeOrderCost = orderEntity.orangeOrderCost,
            orderTotalCost = orderEntity.appleOrderCost + orderEntity.orangeOrderCost
        )
    }

    private fun assignRequestValuesToDbEntity(orderEntity: Order, orderRequest: OrderRequest) {
        // Calculate apple BOGO offer
        orderEntity.numApples = orderRequest.numApples * 2
        val appleOrderCostPromo = (orderRequest.numApples * 0.60).toFloat()
        orderEntity.appleOrderCost = appleOrderCostPromo

        // Calculate orange offer (3 for the price of 2)
        orderEntity.numOranges = orderRequest.numOranges
        val orangeOrderCostPromo = ((orderRequest.numOranges - orderRequest.numOranges / 3) * 0.25).toFloat()
        orderEntity.orangeOrderCost = orangeOrderCostPromo
    }

    private fun checkOrderIdExists(id: Long) {
        if (!repository.existsById(id)) {
            throw OrderNotFoundException("Order with ID: $id does not exist.")
        }
    }

    fun getOrderById(id: Long): OrderDto {
        checkOrderIdExists(id)
        val dbOrder: Order = repository.findOrderById(id)
        return convertDBEntityToDto(dbOrder)
    }

    fun getAllOrders(): List<OrderDto> {
        return repository.findAll().stream().map(this::convertDBEntityToDto).collect(Collectors.toList())
    }

    fun createOrder(orderRequest: OrderRequest): OrderDto {
        val order = Order()
        assignRequestValuesToDbEntity(order, orderRequest)
        val dbSavedOrder = repository.save(order)
        return convertDBEntityToDto(dbSavedOrder)
    }
}