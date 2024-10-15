package org.example.possystembackendspring.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.entity.SuperEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class ItemEntity implements SuperEntity {
    @Id
    private String id;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
    @OneToMany(mappedBy = "item")
    private List<OrderDetailsEntity> orderDetails;
}
