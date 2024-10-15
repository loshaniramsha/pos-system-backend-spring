package org.example.possystembackendspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.dto.OrderDetailsStates;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO implements OrderDetailsStates {
    private String order_id;
    private String item_id;
    private int qty;
    private double unit_price;
    private double total;
}
