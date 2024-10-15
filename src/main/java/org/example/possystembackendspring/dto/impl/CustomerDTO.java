package org.example.possystembackendspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.dto.CustomerStates;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements CustomerStates {
    private String id;
    private String name;
    private String address;
    private String contact;
}
