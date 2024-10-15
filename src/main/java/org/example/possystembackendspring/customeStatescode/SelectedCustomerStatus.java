package org.example.possystembackendspring.customeStatescode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.dto.CustomerStates;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedCustomerStatus implements CustomerStates {
    private int statuscode;
    private String message;
}
