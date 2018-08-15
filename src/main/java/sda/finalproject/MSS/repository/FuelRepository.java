package sda.finalproject.MSS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sda.finalproject.MSS.model.Fuel;
import sda.finalproject.MSS.model.Machine;

import java.time.LocalDateTime;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {


    boolean existsByMachineIdAndDate(Long machineId, LocalDateTime ldt);


}
