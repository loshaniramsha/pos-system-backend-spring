package org.example.possystembackendspring.customeStatescode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.possystembackendspring.dto.ItemStates;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedItemStatus implements ItemStates {
    private int statuscode;
    private String message;
}
