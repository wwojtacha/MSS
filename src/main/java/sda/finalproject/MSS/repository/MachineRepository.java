package sda.finalproject.MSS.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.finalproject.MSS.model.Machine;
import sda.finalproject.MSS.model.MachineStatus;
import sda.finalproject.MSS.model.MachineType;

import java.util.List;


@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    Page<Machine>
    findByNameContainingAndProducerContainingAndTypeInAndMachineNumberContainingAndProductionYearAndMachineStatusIn(
            String name,
            String producer,
            List<MachineType> type,
            String machineNumber,
            Integer productionYear,
            List<MachineStatus> machineStatus,
            Pageable pageable
    );

    Page<Machine>
    findByNameContainingAndProducerContainingAndTypeInAndMachineNumberContainingAndMachineStatusIn(
            String name,
            String producer,
            List<MachineType> type,
            String machineNumber,
            List<MachineStatus> machineStatus,
            Pageable pageable
    );

    boolean existsByMachineNumber(String machineNumber);
}