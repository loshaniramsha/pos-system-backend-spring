package org.example.possystembackendspring.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.possystembackendspring.customeStatescode.SelectedItemStatus;
import org.example.possystembackendspring.dao.ItemDAO;
import org.example.possystembackendspring.dto.ItemStates;
import org.example.possystembackendspring.dto.impl.ItemDTO;
import org.example.possystembackendspring.entity.impl.ItemEntity;
import org.example.possystembackendspring.exception.DataPersistsException;
import org.example.possystembackendspring.exception.ItemNotFoundException;
import org.example.possystembackendspring.service.ItemService;
import org.example.possystembackendspring.util.Mapping;
import org.example.possystembackendspring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDAO itemDao;
    @Autowired
    private Mapping mapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setId(generateItemId());
        ItemEntity savedItem = itemDao.save(mapper.toItemEntity(itemDTO));
        if (savedItem == null) {
            throw new DataPersistsException("Item could not be saved");
        }
    }


    @Override
    public void updateItem(String id, ItemDTO itemDTO) {
        ItemEntity itemEntity = itemDao.getReferenceById(id);
        if (itemEntity==null){
            throw new ItemNotFoundException("Item not found");
        }
        itemEntity.setDescription(itemDTO.getDescription());
        itemEntity.setUnitPrice(itemDTO.getUnitPrice());
        itemEntity.setQtyOnHand(itemDTO.getQty());
        itemDao.save(itemEntity);
    }

    @Override
    public void deleteItem(String id) {
        // Check if the item exists
        if (itemDao.existsById(id)) {
            itemDao.deleteById(id);
            System.out.println("Item deleted with ID: " + id);
        } else {
            // Log the exception for debugging
            System.out.println("Item not found in the database: " + id);
            throw new ItemNotFoundException("Item not found with ID: " + id);
        }
    }


    @Override
    public ItemStates getSelectedItem(String id) {
        ItemEntity item=itemDao.getReferenceById(id);
        if (item==null){
            return new SelectedItemStatus(1,"Item not found");
        }
        return mapper.toItemDTO(item);
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return mapper.toItemDTOList(itemDao.findAll());
    }

    @Override
    public String generateItemId() {
        String jpql = "SELECT i.id FROM ItemEntity i ORDER BY i.id DESC";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        List<String> ids = query.getResultList();

        // Check if the list is empty, return default ID if no items exist
        if (ids.isEmpty()) {
            return "ITEM001";  // Default to ITEM001 if no items are present
        }

        // Check for the last valid ID
        for (String id : ids) {
            if (id != null && id.matches("ITEM\\d+")) {
                int lastIdNumber = Integer.parseInt(id.substring(4));
                return String.format("ITEM%03d", lastIdNumber + 1);
            }
        }
        return "ITEM001";
    }

    @Override
    public List<ItemDTO> searchByItemCode(String newItemCode) {
        String jpql = "SELECT i FROM ItemEntity i WHERE i.id LIKE :itemCode";
        TypedQuery<ItemEntity> query = entityManager.createQuery(jpql, ItemEntity.class);

        // Use the correct parameter name and include the wildcard for partial matching
        query.setParameter("itemCode", newItemCode + "%");

        List<ItemEntity> itemEntities = query.getResultList();
        List<ItemDTO> itemDTOS = new ArrayList<>();

        // Convert the entities to DTOs
        itemEntities.forEach(itemEntity -> itemDTOS.add(mapper.toItemDTO(itemEntity)));

        return itemDTOS;
    }

}
