package sda.finalproject.MSS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sda.finalproject.MSS.model.Machine;
import sda.finalproject.MSS.model.MachineStatus;
import sda.finalproject.MSS.model.MachineType;
import sda.finalproject.MSS.service.MachineService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Machine create(
            @RequestBody @Valid Machine machine,
            BindingResult bindingResult
    ) {
        return machineService.create(machine, bindingResult);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Machine getById(@PathVariable Long id) {
        return machineService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Machine> search(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "producer", required = false, defaultValue = "") String producer,
            @RequestParam(value = "type", required = false) List<MachineType> type,
            @RequestParam(value = "machineNumber", required = false, defaultValue = "") String machineNumber,
            @RequestParam(value = "productionYear", required = false) Integer productionYear,
            @RequestParam(value = "machineStatus", required = false) List<MachineStatus> machineStatus,
            Pageable pageable
            ) {
        return machineService.search(name, producer, type, machineNumber, productionYear, machineStatus, pageable);

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Machine update(
            @PathVariable Long id,
            @RequestBody @Valid Machine machine,
            BindingResult bindingResult
    ) {
        return machineService.update(id, machine, bindingResult);
    }

    //@GetMapping("/types")


}
