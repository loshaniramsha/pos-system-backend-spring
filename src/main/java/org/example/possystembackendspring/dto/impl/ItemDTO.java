package org.example.possystembackendspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.dto.ItemStates;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements ItemStates {
    private String id;
    private String description;
    private double unitPrice;
    private int qty;
}
