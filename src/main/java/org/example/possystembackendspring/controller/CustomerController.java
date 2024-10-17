/*
package org.example.possystembackendspring.controller;

import org.example.possystembackendspring.dto.impl.CustomerDTO;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.service.CustomerService;
import org.example.possystembackendspring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping(value = "/nextId")
    public String nextCustomerId(){
        return customerService.generateCustomerId();
    }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            boolean isContactValid=customerDTO.getContact().matches(RegexProcess.CONTACT_REGEX);
            if (!isContactValid){
                customerService.saveCustomer(customerDTO);
                return new ResponseEntity<>("Customer saved successfully", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Invalid contact number", HttpStatus.BAD_REQUEST);
            }

    }catch (DataPersistsException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
*/
package org.example.possystembackendspring.controller;

import org.example.possystembackendspring.dto.CustomerStates;
import org.example.possystembackendspring.dto.impl.CustomerDTO;
import org.example.possystembackendspring.exception.CustomerNotFoundException;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.service.CustomerService;
import org.example.possystembackendspring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Get the next available customer ID
    @GetMapping(value = "/nextId")
    public String nextCustomerId(){
        return customerService.generateCustomerId();
    }

    // Save a new customer
    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            boolean isContactValid = customerDTO.getContact().matches(RegexProcess.CONTACT_REGEX);

            // Save customer if contact is valid
            if (isContactValid) {
                customerService.saveCustomer(customerDTO);
                return new ResponseEntity<>("Customer saved successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid contact number", HttpStatus.BAD_REQUEST);
            }

        } catch (DataPersistsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Conflict error: possible duplicate or constraint violation", HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(consumes = "application/json")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public List<CustomerDTO> getCustomer(@PathVariable("id") String id){
        return customerService.searchByContact(id);
    }

    @GetMapping(value = "/search/{id}", produces = "application/json")
    public CustomerStates getSelectedCustomer(@PathVariable("id") String id){
        return customerService.getSelectedCustomer(id);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") String id, @RequestBody CustomerDTO customerDTO){
        boolean isCustomerIdValid=id.matches(RegexProcess.CUSTOMER_ID_REGEX);
        try {
            if (isCustomerIdValid){
                customerService.updateCustomer(id, customerDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (CustomerNotFoundException | DataPersistsException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}