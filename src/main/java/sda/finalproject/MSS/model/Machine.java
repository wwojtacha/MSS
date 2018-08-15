package sda.finalproject.MSS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "machines")
@Data
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank
    @Length(min = 1, max = 50)
    @Column(nullable = false, unique = false)
    private String name;

    @NotBlank
    @Length(min = 1, max = 20)
    @Column(nullable = false, unique = false)
    private String producer;

    @NotBlank
    @Length(min = 1, max = 20)
    @Column(nullable = false, unique = false)
    private String model;


    @Enumerated(EnumType.STRING)
    private MachineType type;

    @NotBlank
    @Length(min = 1, max = 10)
    @Column(nullable = false, unique = true)
    private String machineNumber;

    @NotNull
    @Min(1900)
    @Max(2100)
    @Column(nullable = false, unique = false)
    private Integer productionYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MachineStatus machineStatus;

    //@OneToMany(mappedBy = "machine")
    //private List<Fuel> fillings;

//    public void updateFrom(Machine machine) {
//
//    }

}
