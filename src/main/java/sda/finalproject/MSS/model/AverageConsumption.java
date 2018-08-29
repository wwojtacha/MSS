package sda.finalproject.MSS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageConsumption {

    private String machineNumber;
    private Double fuelQuantity;
    private Integer motohours;
    private Double averageConsumption;
}
