package org.example.possystembackendspring.controller;

import org.example.possystembackendspring.dto.impl.OrdersDTO;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping(value = "/nextId")
    public String nextId(){
        return ordersService.generateOrderId();
    }
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OrdersDTO> getAllOrders(){
        return ordersService.getAllOrders();
    }
    @GetMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OrdersDTO> getOrders(@PathVariable("id") String id){
        return ordersService.searchByOrderId(id);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrdersDTO orderDTO){
        try {
            ordersService.saveOrder(orderDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (DataPersistsException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
