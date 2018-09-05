package sda.finalproject.MSS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import sda.finalproject.MSS.exception.BindingResultException;
import sda.finalproject.MSS.exception.NotFoundException;
import sda.finalproject.MSS.model.AverageConsumption;
import sda.finalproject.MSS.model.CounterState;
import sda.finalproject.MSS.model.Fuel;
import sda.finalproject.MSS.model.Machine;
import sda.finalproject.MSS.repository.FuelRepository;
import sda.finalproject.MSS.repository.MachineRepository;

import javax.mail.MessagingException;
import javax.validation.constraints.Null;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.*;

@Service
public class FuelService {

    @Autowired
    FuelRepository fuelRepository;

    @Autowired
    MachineRepository machineRepository;

    @Autowired
    MailService mailService = new MailService();

    public Fuel getById(Long id) {
        Optional<Fuel> filling = fuelRepository.findById(id);
        if (!filling.isPresent()) {
            throw new NotFoundException("No filling with " + id + " found.");
        }

        return filling.get();
    }


    public Fuel create(Fuel fuel, BindingResult bindingResult) {
        if (fuel.getMachine() == null || fuel.getMachine().getMachineNumber() == null) {
            bindingResult.addError(new FieldError("fuel", "machineNumber", "Machine number must not be empty"));
        } else {
            if (!machineRepository.existsByMachineNumber(fuel.getMachine().getMachineNumber())) {
                bindingResult.addError(new FieldError("fuel", "machineNumber", "Machine with this number does not exist"));
            } else {
                fuel.getMachine().setId(machineRepository.findByMachineNumber(fuel.getMachine().getMachineNumber()).getId());
            }
        }

        if (bindingResult.hasErrors()) {
            throw new BindingResultException(bindingResult);
        }

        validateFuel(fuel.getMachine().getId(), fuel.getDate(), bindingResult);
        return fuelRepository.save(fuel);
    }


    public Page<Fuel> search(String machineNumber, ZonedDateTime startDate, ZonedDateTime endDate, Pageable pageable) {
        if (startDate == null) {
            startDate = ZonedDateTime.of(LocalDate.of(1900, 1, 1), LocalTime.MIN, ZoneId.systemDefault());
        }

        if (endDate == null) {
            endDate = ZonedDateTime.of(LocalDate.of(2100, 12, 31), LocalTime.MAX, ZoneId.systemDefault());
        }

        return fuelRepository.findByDateBetweenAndMachine_MachineNumberContaining (startDate, endDate, machineNumber, pageable);

        //return fuelRepository.findByMachineAndDate(machineNumber, startDate, endDate, pageable);
    }

    private void validateFuel(Long machineId, ZonedDateTime fillingDateTime, BindingResult bindingResult) {
        if (fuelRepository.existsByMachine_IdAndDate(machineId, fillingDateTime)){
            bindingResult.addError(new FieldError(
                    "Filling",
                    "date", //field name "date" equal to "date" in vm.errors.date in fuel-create.html
                    String.format("Machine can not have multiple fillings at the same time")
            ));


            if (bindingResult.hasErrors()) {
                throw new BindingResultException(bindingResult);
            }
        }
    }


    public void delete(Long id) {
        if (!fuelRepository.existsById(id)) {
            throw new NotFoundException(String.format("Filling with id %s does not exist", id));
        }
        fuelRepository.deleteById(id);
    }

    public Fuel update(Long id, Fuel fuel, BindingResult bindingResult) {
        Optional<Fuel> dbFuel = fuelRepository.findById(id);
        if (!dbFuel.isPresent()) {
            throw new NotFoundException(String.format("No filling with id %s", id));
        }


        fuel.setId(id);
        return fuelRepository.save(fuel);
    }


    public List<CounterState> getMaxCounterStates() {
      return fuelRepository.findMaxCounterStates();

    }

    public List<AverageConsumption> countAverageConsumption() {
        return fuelRepository.countAverageConsumption();
    }

    public void sendMail() {
            String to = "wwojtacha@o2.pl";
            String subject = "test mail";
            String content = "test content";
            try {
                mailService.sendNotification(to, subject, content);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
    }

}
