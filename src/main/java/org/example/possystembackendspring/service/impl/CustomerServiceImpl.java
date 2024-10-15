package org.example.possystembackendspring.service.impl;

import jakarta.transaction.Transactional;
import org.example.possystembackendspring.dto.CustomerStates;
import org.example.possystembackendspring.dto.impl.CustomerDTO;
import org.example.possystembackendspring.service.CustomerService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {

    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {

    }

    @Override
    public void deleteCustomer(String id) {

    }

    @Override
    public CustomerStates getSelectedCustomer(String id) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return List.of();
    }

    @Override
    public String generateCustomerId() {
        return "";
    }

    @Override
    public List<CustomerDTO> searchByContact(String value) {
        return List.of();
    }
}
