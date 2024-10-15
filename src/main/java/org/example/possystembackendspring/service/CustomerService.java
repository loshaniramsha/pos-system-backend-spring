package org.example.possystembackendspring.service;

import org.example.possystembackendspring.dto.CustomerStates;
import org.example.possystembackendspring.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String id, CustomerDTO customerDTO);
    void deleteCustomer(String id);
    CustomerStates getSelectedCustomer(String id);
    List<CustomerDTO> getAllCustomers();
    String generateCustomerId();
    List<CustomerDTO> searchByContact(String value);
}
