package sda.finalproject.MSS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "fillings")
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Past
    private LocalDateTime date;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private Integer day;

    @NotNull
    private Double fuelQuantity;

    @NotNull
    private BigDecimal fuelPrice;

    @NotNull
    private Integer counterState;


    @ManyToOne
    @JoinColumn(name = "machineId")
    private Machine machine;
}
