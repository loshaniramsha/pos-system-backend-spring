package org.example.possystembackendspring.service;

import org.example.possystembackendspring.dto.impl.OrderDetailsDTO;

import java.util.List;

public interface OrderDetailsService {
    void saveOrderDetail(OrderDetailsDTO orderDetailDTO);
    List<OrderDetailsDTO> getOrderDetails(String orderId);
}
