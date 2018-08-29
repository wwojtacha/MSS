package sda.finalproject.MSS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounterState {
    private String machineNumber;
    private Integer counterState;
}
