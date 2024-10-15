package org.example.possystembackendspring.service;

import org.example.possystembackendspring.dto.OrdersStates;
import org.example.possystembackendspring.dto.impl.OrdersDTO;

import java.util.List;

public interface OrdersService {
    void saveOrder(OrdersDTO orderDTO);
    List<OrdersDTO> getAllOrders();
    OrdersStates getSelectedOrder(String orderId);
    String generateOrderId();
    List<OrdersDTO> searchByOrderId(String id);
}
