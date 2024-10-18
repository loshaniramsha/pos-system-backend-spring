
package org.example.possystembackendspring.service.impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.possystembackendspring.dao.ItemDAO;
import org.example.possystembackendspring.dao.OrderDetailsDAO;
import org.example.possystembackendspring.dao.OrdersDAO;
import org.example.possystembackendspring.dto.impl.OrderDetailsDTO;
import org.example.possystembackendspring.entity.impl.ItemEntity;
import org.example.possystembackendspring.entity.impl.OrderDetailsEntity;
import org.example.possystembackendspring.entity.impl.OrdersEntity;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.service.OrderDetailsService;
import org.example.possystembackendspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private OrderDetailsDAO orderDetailsDAO;

    @Autowired
    private Mapping mapping;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void saveOrderDetail(OrderDetailsDTO orderDetailDTO) {
        OrderDetailsEntity orderDetails = mapping.toOrderDetailEntity(orderDetailDTO);

        // Fetch order and item safely using findById
        Optional<OrdersEntity> optionalOrder = ordersDAO.findById(orderDetailDTO.getOrder_id());
        Optional<ItemEntity> optionalItem = itemDAO.findById(orderDetailDTO.getItem_id());

        // If either order or item is not found, throw an exception
        if (optionalOrder.isEmpty()) {
            throw new DataPersistsException("Order not found for ID: " + orderDetailDTO.getOrder_id());
        }
        if (optionalItem.isEmpty()) {
            throw new DataPersistsException("Item not found for ID: " + orderDetailDTO.getItem_id());
        }

        // Set the found order and item to the orderDetails entity
        orderDetails.setOrder(optionalOrder.get());
        orderDetails.setItem(optionalItem.get());

        // Save orderDetails entity
        OrderDetailsEntity savedOrderDetail = orderDetailsDAO.save(orderDetails);

        // Update the item's stock or perform other item-related updates if necessary
        ItemEntity savedItem = itemDAO.save(optionalItem.get());

        // Ensure both entities were saved properly
        if (savedOrderDetail == null || savedItem == null) {
            throw new DataPersistsException("Order detail or item could not be saved");
        }
    }

    @Override
    public List<OrderDetailsDTO> getOrderDetails(String orderId) {
        String jpql = "select od from OrderDetailsEntity od where od.order.id=:orderId";
        TypedQuery<OrderDetailsEntity> query = entityManager.createQuery(jpql, OrderDetailsEntity.class);
        query.setParameter("orderId", orderId);

        List<OrderDetailsEntity> orderDetailsEntities = query.getResultList();
        List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();

        // Map the order details entities to DTOs
        for (OrderDetailsEntity od : orderDetailsEntities) {
            orderDetailsDTOS.add(new OrderDetailsDTO(
                    od.getOrder().getId(),
                    od.getItem().getId(),
                    od.getQty(),
                    od.getUnit_price(),
                    od.getTotal()
            ));
        }

        return orderDetailsDTOS;
    }
}
