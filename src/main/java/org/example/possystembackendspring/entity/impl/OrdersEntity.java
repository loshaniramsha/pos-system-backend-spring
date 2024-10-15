package org.example.possystembackendspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.entity.SuperEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class OrdersEntity implements SuperEntity {
    @Id
    private String id;
    private String date;
    private double discount_value;
    private double sub_total;
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private CustomerEntity customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<OrderDetailsEntity> orderDetails;
}
