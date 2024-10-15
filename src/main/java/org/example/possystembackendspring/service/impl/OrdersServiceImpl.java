package org.example.possystembackendspring.service.impl;

import jakarta.transaction.Transactional;
import org.example.possystembackendspring.dto.OrdersStates;
import org.example.possystembackendspring.dto.impl.OrdersDTO;
import org.example.possystembackendspring.service.OrdersService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Override
    public void saveOrder(OrdersDTO orderDTO) {

    }

    @Override
    public List<OrdersDTO> getAllOrders() {
        return List.of();
    }

    @Override
    public OrdersStates getSelectedOrder(String orderId) {
        return null;
    }

    @Override
    public String generateOrderId() {
        return "";
    }

    @Override
    public List<OrdersDTO> searchByOrderId(String id) {
        return List.of();
    }
}
