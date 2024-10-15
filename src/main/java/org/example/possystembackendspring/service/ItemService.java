package org.example.possystembackendspring.service;

import org.example.possystembackendspring.dto.ItemStates;
import org.example.possystembackendspring.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(String id, ItemDTO itemDTO);
    void deleteItem(String id);
    ItemStates getSelectedItem(String id);
    List<ItemDTO> getAllItem();
    String generateItemId();
    List<ItemDTO> searchByItemCode(String newItemCode);
}
