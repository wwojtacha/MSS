package sda.finalproject.MSS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import sda.finalproject.MSS.exception.BindingResultException;
import sda.finalproject.MSS.exception.NotFoundException;
import sda.finalproject.MSS.model.Fuel;
import sda.finalproject.MSS.repository.FuelRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FuelService {

    @Autowired
    FuelRepository fuelRepository;


    public Fuel getById(Long id) {
        Optional<Fuel> filling = fuelRepository.findById(id);
        if (!filling.isPresent()) {
            throw new NotFoundException("No filling with" + id + " found.");
        }
        return filling.get();
    }


    public Fuel create(Fuel fuel, BindingResult bindingResult) {
        //validateFuel(fuel.getMachine().getId(), fuel.getDate(), bindingResult);
        return fuelRepository.save(fuel);
    }

    private void validateFuel(Long machineId, LocalDateTime fillingDateTime, BindingResult bindingResult) {
        if (fuelRepository.existsByMachineIdAndDate(machineId, fillingDateTime)){
            bindingResult.addError(new FieldError(
                    "Filling",
                    "name",
                    String.format("Machine can not have multiple fillings at the same time")
            ));

            if (bindingResult.hasErrors()) {
                throw new BindingResultException(bindingResult);
            }
        }
    }

}
