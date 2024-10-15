package org.example.possystembackendspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.dto.OrdersStates;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdersDTO implements OrdersStates {
    private String id;
    private String date;
    private double discount_value;
    private double sub_total;
    private String customer_id;
}
