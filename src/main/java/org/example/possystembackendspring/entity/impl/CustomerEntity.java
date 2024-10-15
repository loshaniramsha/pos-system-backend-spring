package org.example.possystembackendspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.entity.SuperEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class CustomerEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String address;
    private String contact;
    @OneToMany(mappedBy = "customer")
    private List<OrdersEntity> orders;
}
