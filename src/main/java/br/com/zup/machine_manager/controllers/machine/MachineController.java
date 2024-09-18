package br.com.zup.machine_manager.controllers.machine;

import br.com.zup.machine_manager.controllers.machine.dtos.MachineCreateDTO;
import br.com.zup.machine_manager.controllers.machine.dtos.MachineResponseDTO;
import br.com.zup.machine_manager.controllers.machine.dtos.MachineUpdateDTO;
import br.com.zup.machine_manager.repository.models.Machine;
import br.com.zup.machine_manager.services.machine.MachineService;
import br.com.zup.machine_manager.services.machine.mappers.MachineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machines")
public class MachineController {
    @Autowired
    private MachineService machineService;

    @GetMapping
    public List<MachineResponseDTO> getAll(){
        return MachineMapper.toMachineResponseDTO(machineService.getAllMachines());
    }

    @GetMapping("/{machineID}")
    public ResponseEntity<?> getMachine(@PathVariable String machineID){
        try {
            return ResponseEntity.ok(MachineMapper.toMachineResponseDTO(machineService.getMachineById(machineID)));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public MachineResponseDTO create(@RequestBody MachineCreateDTO machineCreateDTO){
        Machine machine = machineService.save(machineCreateDTO);
        return MachineMapper.toMachineResponseDTO(machine);
    }

    @PutMapping
    public MachineResponseDTO update(@RequestBody MachineUpdateDTO machineUpdateDTO){
        Machine machine = machineService.update(machineUpdateDTO);
        return MachineMapper.toMachineResponseDTO(machine);
    }
}
