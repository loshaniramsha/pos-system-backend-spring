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
        OrderDetailsEntity orderDetails=mapping.toOrderDetailEntity(orderDetailDTO);
        OrdersEntity selectOrder=ordersDAO.getReferenceById(orderDetailDTO.getOrder_id());
        ItemEntity selectItem=itemDAO.getReferenceById(orderDetailDTO.getItem_id());
        orderDetails.setItem(selectItem);

        OrderDetailsEntity saveOrderDetail=orderDetailsDAO.save(orderDetails);

        String itemId=orderDetailDTO.getItem_id();
        int qty=orderDetailDTO.getQty();

        ItemEntity savedItem=itemDAO.save(selectItem);
        if (saveOrderDetail==null || savedItem==null){
            throw new DataPersistsException("Order detail could not be saved");
        }

    }

    @Override
    public List<OrderDetailsDTO> getOrderDetails(String orderId) {
        String jpql="select od from OrderDetailsEntity od where od.order.id=:orderId";
        TypedQuery<OrderDetailsEntity> query=entityManager.createQuery(jpql,OrderDetailsEntity.class);
        query.setParameter("orderId",orderId);

        List<OrderDetailsEntity> orderDetailsEntities=query.getResultList();

        List<OrderDetailsDTO> orderDetailsDTOS=new ArrayList<>();
        for (OrderDetailsEntity od:orderDetailsEntities){
            orderDetailsDTOS.add(new OrderDetailsDTO(od.getOrder().getId(),od.getItem().getId(),od.getQty(),od.getUnit_price(),od.getTotal()));

        }
        return orderDetailsDTOS;
    }
}
