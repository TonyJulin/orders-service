package com.github.ajulin.orderservice.model

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order {
    @Id
    @GeneratedValue(generator = "task_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    val id: Long = 0
    @Column(name = "num_apples")
    var numApples: Int = 0
    @Column(name = "num_oranges")
    var numOranges: Int = 0
    @Column(name = "apple_order_cost")
    var appleOrderCost: Float = 0.00f
    @Column(name = "orange_order_cost")
    var orangeOrderCost: Float = 0.00f
}