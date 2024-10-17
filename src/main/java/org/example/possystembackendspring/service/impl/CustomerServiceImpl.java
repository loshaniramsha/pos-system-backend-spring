package org.example.possystembackendspring.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.possystembackendspring.dao.CustomerDAO;
import org.example.possystembackendspring.dto.CustomerStates;
import org.example.possystembackendspring.dto.impl.CustomerDTO;
import org.example.possystembackendspring.entity.impl.CustomerEntity;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.service.CustomerService;
import org.example.possystembackendspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private Mapping mapping;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setId(generateCustomerId());
        CustomerEntity savedCustomer=customerDAO.save(mapping.toCustomerEntity(customerDTO));
        if (savedCustomer==null){
            throw new DataPersistsException("Customer could not be saved");
        }

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
        // JPQL query to get the latest customer ID
        String jpql = "select c.id from CustomerEntity c order by c.id desc";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setMaxResults(1);  // Limit to the last customer ID

        String maxCustomerId = null;
        try {
            maxCustomerId = query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            // If no customer ID is found, return the first ID
            return "C001";
        }

        // Check if maxCustomerId is valid and properly formatted
        if (maxCustomerId == null || maxCustomerId.isEmpty() || !maxCustomerId.startsWith("C")) {
            return "C001"; // Fallback to "C001" if the format is invalid
        }

        try {
            // Extract the numeric part and increment it
            int newCustomerId = Integer.parseInt(maxCustomerId.replace("C", "")) + 1;
            // Format the new customer ID and return it
            return String.format("C%03d", newCustomerId);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing customer ID: " + maxCustomerId, e);
        }
    }


    @Override
    public List<CustomerDTO> searchByContact(String value) {
        return List.of();
    }
}
