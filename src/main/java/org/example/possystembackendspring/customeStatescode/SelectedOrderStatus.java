package org.example.possystembackendspring.customeStatescode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.dto.OrdersStates;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedOrderStatus implements OrdersStates {
    private int statuscode;
    private String message;
}
