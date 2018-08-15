package sda.finalproject.MSS.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "machine_service")
public class WorkshopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Past
    private Date date;

    @Enumerated(EnumType.STRING)
    private ServiceType type;

    @NotBlank
    @Length(min = 1, max = 100)
    private String description;

    @NotNull
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "machineId")
    private Machine machine;

}
