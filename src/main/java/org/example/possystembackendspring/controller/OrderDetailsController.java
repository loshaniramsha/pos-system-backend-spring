package org.example.possystembackendspring.controller;

import org.example.possystembackendspring.dto.impl.OrderDetailsDTO;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/orderDetails")

public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addOrderDetail(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        try {
            orderDetailsService.saveOrderDetail(orderDetailsDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails(@PathVariable("id") String id) {
        try {
            List<OrderDetailsDTO> orderDetails = orderDetailsService.getOrderDetails(id);

            // Check if the list is empty and return appropriate response
            if (orderDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content if no order details found
            }

            return new ResponseEntity<>(orderDetails, HttpStatus.OK); // 200 OK with the list of order details
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error for any exception
        }
    }

}
