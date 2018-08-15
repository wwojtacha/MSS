package sda.finalproject.MSS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sda.finalproject.MSS.model.Fuel;
import sda.finalproject.MSS.service.FuelService;

import javax.validation.Valid;

@RestController
@RequestMapping("/fuel")
public class FuelController {

    @Autowired
    FuelService fuelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fuel create(@RequestBody @Valid Fuel fuel, BindingResult bindingResult) {
        return fuelService.create(fuel, bindingResult);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Fuel getById(@PathVariable Long id) {
        return fuelService.getById(id);
    }
}
