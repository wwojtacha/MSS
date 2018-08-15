package sda.finalproject.MSS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import sda.finalproject.MSS.exception.BindingResultException;
import sda.finalproject.MSS.exception.NotFoundException;
import sda.finalproject.MSS.model.Machine;
import sda.finalproject.MSS.model.MachineStatus;
import sda.finalproject.MSS.model.MachineType;
import sda.finalproject.MSS.repository.MachineRepository;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public Machine create(Machine machine, BindingResult bindingResult) {
        validateMachine(machine.getMachineNumber(), null, bindingResult);
        return machineRepository.save(machine);
    }

    public Machine getById(Long id) {
        Optional<Machine> machine = machineRepository.findById(id);
        if (!machine.isPresent()) {
            throw new NotFoundException("Machine with id " + id + "does not exist");
        }
        return machine.get();
    }




    public Page<Machine> search(String name, String producer, List<MachineType> type, String machineNumber, Integer productionYear, List<MachineStatus> machineStatus, Pageable pageable) {
        if (isEmpty(type)) {
            type = new ArrayList<>(EnumSet.allOf(MachineType.class));
        }
        if (isEmpty(machineStatus)) {
            machineStatus = new ArrayList<>(EnumSet.allOf(MachineStatus.class));
        }
        if (productionYear == null) {
            return machineRepository.findByNameContainingAndProducerContainingAndTypeInAndMachineNumberContainingAndMachineStatusIn(name, producer, type, machineNumber, machineStatus, pageable);
        } else {

        }
        return machineRepository
                .findByNameContainingAndProducerContainingAndTypeInAndMachineNumberContainingAndProductionYearAndMachineStatusIn(name, producer, type, machineNumber, productionYear, machineStatus, pageable);
        }

    public Machine update(Long id, Machine machine, BindingResult bindingResult) {
        Optional<Machine> dbMachine = machineRepository.findById(id);
        if (!dbMachine.isPresent()) {
            throw new NotFoundException(String.format("Machine with id %s does not exist", id));
        }

        validateMachine(machine.getMachineNumber(),dbMachine.get().getMachineNumber(), bindingResult);

        machine.setId(id);
        return machineRepository.save(machine);
    }

    private void validateMachine(String machineNumber, String currentMachineNumber, BindingResult bindingResult) {
        if (machineRepository.existsByMachineNumber(machineNumber) && !machineNumber.equals(currentMachineNumber)) {
            bindingResult.addError(new FieldError(
                                "Machine",
                            "name",
                               String.format("Machine number %s already exists", machineNumber)));
        }

        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }
    }
}
