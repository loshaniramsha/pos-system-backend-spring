package org.example.possystembackendspring.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.possystembackendspring.dao.CustomerDAO;
import org.example.possystembackendspring.dto.CustomerStates;
import org.example.possystembackendspring.dto.impl.CustomerDTO;
import org.example.possystembackendspring.entity.impl.CustomerEntity;
import org.example.possystembackendspring.exception.CustomerNotFoundException;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.service.CustomerService;
import org.example.possystembackendspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<CustomerEntity> optionalCustomerEntity = customerDAO.findById(id);
        if (!optionalCustomerEntity.isPresent()) {
            throw new CustomerNotFoundException("Customer not found");
        }

        CustomerEntity customerEntity = optionalCustomerEntity.get();
        customerEntity.setName(customerDTO.getName());
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setContact(customerDTO.getContact());
        customerDAO.save(customerEntity);
    }


    @Override
    public void deleteCustomer(String id) {

    }

    @Override
    public CustomerStates getSelectedCustomer(String id) {
        Optional<CustomerEntity> customerEntity=customerDAO.findById(id);
        if (customerEntity.isPresent()){
            return mapping.toCustomerDTO(customerDAO.getReferenceById(id));
        }else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.toCustomerDTOList(customerDAO.findAll());
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
        String jpql = "select c from CustomerEntity c where c.contact like :value";
        TypedQuery<CustomerEntity> query = entityManager.createQuery(jpql, CustomerEntity.class);
        query.setParameter("value", "%" + value + "%");

        List<CustomerEntity> customerEntities = query.getResultList();
        List<CustomerDTO> customerDTOS=new ArrayList<>();

        customerEntities.forEach(customerEntity -> customerDTOS.add(mapping.toCustomerDTO(customerEntity)));

        return customerDTOS;
    }
}
