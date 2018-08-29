package sda.finalproject.MSS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "fillings")

@NamedNativeQueries({
        @NamedNativeQuery(name = "Fuel.findCounterStates",
                query = "SELECT machines.machine_number AS machineNumber, MAX(fillings.counter_state) AS counterState FROM fillings INNER JOIN machines ON fillings.machine_id = machines.id GROUP BY machines.machine_number",
                resultSetMapping = "Fuel.CounterState"),
        @NamedNativeQuery(name = "Fuel.countAverageConsumption",
                query = "SELECT machines.machine_number AS machineNumber, SUM(fillings.fuel_quantity) AS fuelQuantity, MAX(fillings.counter_state) - MIN(fillings.counter_state) AS  motohours, ROUND(((SUM(fillings.fuel_quantity)) / (MAX(fillings.counter_state) - MIN(fillings.counter_state))), 2) AS averageConsumption FROM fillings INNER JOIN machines ON fillings.machine_id = machines.id GROUP BY machines.machine_number",
                resultSetMapping = "Fuel.AverageConsumption")
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "Fuel.CounterState",
                classes = {@ConstructorResult(
                                targetClass = CounterState.class,
                                columns = {
                                        @ColumnResult(name = "machineNumber", type = String.class),
                                        @ColumnResult(name = "counterState", type = Integer.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = "Fuel.AverageConsumption",
                classes = {@ConstructorResult(
                        targetClass = AverageConsumption.class,
                        columns = {
                                @ColumnResult(name = "machineNumber", type = String.class),
                                @ColumnResult(name = "fuelQuantity", type = Double.class),
                                @ColumnResult(name = "motohours", type = Integer.class),
                                @ColumnResult(name = "averageConsumption", type = Double.class)
                        }
                )
                }
        )
})

public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Past
    @Column(nullable = false)
    private ZonedDateTime date;

    @NotNull
    private Double fuelQuantity;

    @NotNull
    private BigDecimal fuelPrice;

    @NotNull
    private Integer counterState;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "machineId")
    private Machine machine;

}
