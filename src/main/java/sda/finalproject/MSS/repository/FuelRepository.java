package sda.finalproject.MSS.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sda.finalproject.MSS.model.AverageConsumption;
import sda.finalproject.MSS.model.CounterState;
import sda.finalproject.MSS.model.Fuel;
import sda.finalproject.MSS.model.Machine;
import sun.misc.ProxyGenerator;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {

    boolean existsByMachine_IdAndDate(Long machineId, ZonedDateTime ldt);

    @Query(value = "SELECT fillings.*, machines.machine_number FROM fillings INNER JOIN machines ON fillings.machine_id = machines.id WHERE machines.machine_number LIKE %?1% AND fillings.date >= ?2 AND fillings.date <= ?3",
    nativeQuery = true)
    Page<Fuel> findByMachineAndDate(String machineNumber, ZonedDateTime startDate, ZonedDateTime endDate, Pageable pageable);

//    @Query(value = "SELECT fillings.*, machines.machine_number FROM fillings INNER JOIN machines ON fillings.machine_id = machines.id WHERE machines.machine_number LIKE %?1% AND fillings.year = ?2 AND fillings.month = ?3 AND fillings.day =?4",
//            nativeQuery = true)
//    Page<Fuel> findByMachineAndYearAndMonthAndDay(String machineName, Integer year, Integer month, Integer day, Pageable pageable);

    Page<Fuel> findByDateBetweenAndMachine_MachineNumberContaining (ZonedDateTime startDate, ZonedDateTime endDate, String machineNumber, Pageable pageable);

    @Query(name = "Fuel.findCounterStates", nativeQuery = true)
    List<CounterState> findMaxCounterStates();

    @Query(name = "Fuel.countAverageConsumption", nativeQuery = true)
    List<AverageConsumption> countAverageConsumption();


}
