package sda.finalproject.MSS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sda.finalproject.MSS.model.AverageConsumption;
import sda.finalproject.MSS.model.CounterState;
import sda.finalproject.MSS.model.Fuel;
import sda.finalproject.MSS.model.FuelPrice;
import sda.finalproject.MSS.service.FuelService;
import sda.finalproject.MSS.service.PriceService;

import javax.validation.Valid;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fuel")
public class FuelController {

    @Autowired
    FuelService fuelService;

    @Autowired
    PriceService priceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fuel create(@RequestBody @Valid Fuel fuel, BindingResult bindingResult) {
        return fuelService.create(fuel, bindingResult);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Fuel> search(
            @RequestParam(value = "machineNumber", required = false, defaultValue = "") String machineNumber,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(value = "startDate", required = false) ZonedDateTime startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(value = "endDate", required = false) ZonedDateTime endDate,
            Pageable pageable
    ) {
        return fuelService.search(machineNumber, startDate, endDate, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Fuel getById(@PathVariable Long id) {
        return fuelService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        fuelService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Fuel update(
                       @PathVariable Long id,
                       @RequestBody @Valid Fuel fuel,
                       BindingResult bindingResult) {

        return fuelService.update(id, fuel, bindingResult);
    }

    @GetMapping("/price")
    @ResponseStatus(HttpStatus.OK)
    public FuelPrice getFuelPrice() {
        try {
            return priceService.downloadPrice();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FuelPrice fuelPrice = new FuelPrice();
        fuelPrice.setFuelPrice(-1.00f);
        return fuelPrice;
    }

    @GetMapping("/counters")
    @ResponseStatus(HttpStatus.OK)
    public List<CounterState> showCounterStates(){
        return fuelService.getMaxCounterStates();
    }

    @GetMapping("/averageConsumption")
    @ResponseStatus(HttpStatus.OK)
    public List<AverageConsumption> countAverageConsumption() {
        return fuelService.countAverageConsumption();
    }
}
