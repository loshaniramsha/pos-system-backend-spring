package org.example.possystembackendspring.dao;

import org.example.possystembackendspring.entity.impl.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDAO extends JpaRepository<OrderDetailsEntity, String> {
}
