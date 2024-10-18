package org.example.possystembackendspring.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.possystembackendspring.customeStatescode.SelectedOrderStatus;
import org.example.possystembackendspring.dao.CustomerDAO;
import org.example.possystembackendspring.dao.OrdersDAO;
import org.example.possystembackendspring.dto.OrdersStates;
import org.example.possystembackendspring.dto.impl.OrdersDTO;
import org.example.possystembackendspring.entity.impl.CustomerEntity;
import org.example.possystembackendspring.entity.impl.OrdersEntity;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.service.OrdersService;
import org.example.possystembackendspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private Mapping mapp;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveOrder(OrdersDTO orderDTO) {
        Optional<CustomerEntity> customerEntity=customerDAO.findById(orderDTO.getCustomer_id());
        OrdersEntity orders=mapp.toOrderEntity(orderDTO);
        orders.setId(generateOrderId());
        orders.setCustomer(customerEntity.get());
        OrdersEntity savedOrder=ordersDAO.save(orders);
        if (savedOrder==null){
            throw new DataPersistsException("Order could not be saved");
        }
    }

    @Override
    public List<OrdersDTO> getAllOrders() {
        List<OrdersEntity> ordersEntities = ordersDAO.findAll();
        List<OrdersDTO> ordersDTOs = new ArrayList<>();
        ordersEntities.forEach(orderEntity -> {
            ordersDTOs.add(mapp.toOrderDTO(orderEntity));
        });
        return ordersDTOs;
    }

    @Override
    public OrdersStates getSelectedOrder(String orderId) {
       OrdersEntity ordersEntity=ordersDAO.getReferenceById(orderId);
       if (ordersEntity==null){
           return new SelectedOrderStatus(1,"Order not found");

       }
       return mapp.toOrderDTO(ordersEntity);
    }

    @Override
    public String generateOrderId() {
       String jpql="SELECT o.id FROM OrdersEntity o ORDER BY o.id DESC LIMIT 1";
        TypedQuery<String> query=entityManager.createQuery(jpql,String.class);
        query.setMaxResults(1);
        String maxId=null;
        try {
            maxId=query.getSingleResult();
        }
        catch (jakarta.persistence.NoResultException e){
            return "000-001";
        }
        int newOrderId=Integer.parseInt(maxId.split("-")[1])+1;
        return String.format("000-%03d",newOrderId);
    }

    @Override
    public List<OrdersDTO> searchByOrderId(String id) {
        String jpql="SELECT o FROM OrdersEntity o WHERE o.id=:id";
        TypedQuery<OrdersEntity> query=entityManager.createQuery(jpql,OrdersEntity.class);
        query.setParameter("id",id+"%");
        List<OrdersEntity> ordersEntities=query.getResultList();
        List<OrdersDTO> ordersDTOs=new ArrayList<>();
        ordersEntities.forEach(orderEntity -> {
            ordersDTOs.add(mapp.toOrderDTO(orderEntity));
        });
        return ordersDTOs;
    }
}
