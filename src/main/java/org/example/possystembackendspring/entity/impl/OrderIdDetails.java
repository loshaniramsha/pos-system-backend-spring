package org.example.possystembackendspring.entity.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.entity.SuperEntity;


import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderIdDetails implements SuperEntity {
    private String order;
    private String item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderIdDetails that = (OrderIdDetails) o;
        return Objects.equals(order, that.order) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, item);
    }
}
