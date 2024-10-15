package org.example.possystembackendspring.service.impl;

import jakarta.transaction.Transactional;
import org.example.possystembackendspring.dto.ItemStates;
import org.example.possystembackendspring.dto.impl.ItemDTO;
import org.example.possystembackendspring.service.ItemService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Override
    public void saveItem(ItemDTO itemDTO) {

    }

    @Override
    public void updateItem(String id, ItemDTO itemDTO) {

    }

    @Override
    public void deleteItem(String id) {

    }

    @Override
    public ItemStates getSelectedItem(String id) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return List.of();
    }

    @Override
    public String generateItemId() {
        return "";
    }

    @Override
    public List<ItemDTO> searchByItemCode(String newItemCode) {
        return List.of();
    }
}
