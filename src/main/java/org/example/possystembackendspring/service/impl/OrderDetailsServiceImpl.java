package org.example.possystembackendspring.service.impl;

import jakarta.transaction.Transactional;
import org.example.possystembackendspring.dto.impl.OrderDetailsDTO;
import org.example.possystembackendspring.service.OrderDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Override
    public void saveOrderDetail(OrderDetailsDTO orderDetailDTO) {

    }

    @Override
    public List<OrderDetailsDTO> getOrderDetails(String orderId) {
        return List.of();
    }
}
