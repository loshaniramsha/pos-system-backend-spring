package org.example.possystembackendspring.controller;

import org.example.possystembackendspring.dto.impl.ItemDTO;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.exception.ItemNotFoundException;
import org.example.possystembackendspring.service.ItemService;
import org.example.possystembackendspring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/nextId")
    public ResponseEntity<String> generateItemId() {
        try {
            String newItemId = itemService.generateItemId();
            return new ResponseEntity<>(newItemId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error generating Item ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDTO itemDto) {
        if (itemDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle null DTO
        }

        try {
            itemService.saveItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle persistence issue
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // General error handling
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItem(){
        return itemService.getAllItem();
    }
    @GetMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getItem(@PathVariable("id") String id){
        return itemService.searchByItemCode(id);
    }
}


