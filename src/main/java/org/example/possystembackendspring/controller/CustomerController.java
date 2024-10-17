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

import org.example.possystembackendspring.dto.impl.CustomerDTO;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.service.CustomerService;
import org.example.possystembackendspring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}