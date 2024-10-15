package org.example.possystembackendspring.util;
import org.example.possystembackendspring.dto.impl.CustomerDTO;
import org.example.possystembackendspring.dto.impl.ItemDTO;
import org.example.possystembackendspring.dto.impl.OrderDetailsDTO;
import org.example.possystembackendspring.dto.impl.OrdersDTO;
import org.example.possystembackendspring.entity.impl.CustomerEntity;
import org.example.possystembackendspring.entity.impl.ItemEntity;
import org.example.possystembackendspring.entity.impl.OrderDetailsEntity;
import org.example.possystembackendspring.entity.impl.OrdersEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //for customer mapping
    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }

    public CustomerDTO toCustomerDTO(CustomerEntity customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public List<CustomerDTO> toCustomerDTOList(List<CustomerEntity> customers) {
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType());

    }
    //for item mapping
    public ItemEntity toItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, ItemEntity.class);
    }

    public ItemDTO toItemDTO(ItemEntity item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    public List<ItemDTO> toItemDTOList(List<ItemEntity> items) {
        return modelMapper.map(items, new TypeToken<List<ItemDTO>>() {}.getType());
    }

    //for order mapping
    public OrdersEntity toOrderEntity(OrdersDTO orderDTO) {
        return modelMapper.map(orderDTO, OrdersEntity.class);
    }

    public OrdersDTO toOrderDTO(OrdersEntity order) {
        return modelMapper.map(order, OrdersDTO.class);
    }

    public List<OrdersDTO> toOrderDTOList(List<OrdersEntity> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrdersDTO>>(){}.getType());
    }

    //for order detail mapping
    public OrderDetailsEntity toOrderDetailEntity(OrderDetailsDTO orderDetailDTO) {
        return modelMapper.map(orderDetailDTO, OrderDetailsEntity.class);
    }

    public OrderDetailsDTO toOrderDetailDTO(OrderDetailsEntity orderDetail) {
        return modelMapper.map(orderDetail, OrderDetailsDTO.class);
    }

    public List<OrderDetailsDTO> toOrderDetailDTOList(List<OrderDetailsEntity> orderDetails) {
        return modelMapper.map(orderDetails, new TypeToken<List<OrderDetailsDTO>>(){}.getType());

    }


}
