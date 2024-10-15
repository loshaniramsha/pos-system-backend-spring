package org.example.possystembackendspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.entity.SuperEntity;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")
@IdClass(OrderIdDetails.class)
public class OrderDetailsEntity implements SuperEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private OrdersEntity order;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private ItemEntity item;
    private int qty;
    private double unit_price;
    private double total;
}
