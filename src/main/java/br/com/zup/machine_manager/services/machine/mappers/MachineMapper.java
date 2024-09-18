package br.com.zup.machine_manager.services.machine.mappers;

import br.com.zup.machine_manager.controllers.machine.dtos.MachineCreateDTO;
import br.com.zup.machine_manager.controllers.machine.dtos.MachineResponseDTO;
import br.com.zup.machine_manager.controllers.machine.dtos.MachineUpdateDTO;
import br.com.zup.machine_manager.repository.models.Machine;
import br.com.zup.machine_manager.repository.models.Zuper;
import br.com.zup.machine_manager.services.zuper.mappers.ZuperMapper;

import java.util.List;

public class MachineMapper {

    public static Machine toMachine(MachineCreateDTO machineCreateDTO){
        Machine machine = new Machine();
        machine.setBrand(machineCreateDTO.getBrand());
        machine.setInUse(machineCreateDTO.getInUse());

        Zuper zuper = new Zuper();
        zuper.setZuperId(machineCreateDTO.getZuperId());
        machine.setZuper(zuper);
        return machine;
    }

    public static Machine toMachine(MachineUpdateDTO machineUpdateDTO){
        Machine machine = new Machine();
        machine.setRegisterId(machineUpdateDTO.getRegisterId());
        machine.setBrand(machineUpdateDTO.getBrand());
        machine.setInUse(machineUpdateDTO.getInUse());

        Zuper zuper = new Zuper();
        zuper.setZuperId(machineUpdateDTO.getZuperId());
        machine.setZuper(zuper);
        return machine;
    }

    public static MachineResponseDTO toMachineResponseDTO(Machine machine){
        return new MachineResponseDTO(
                machine.getRegisterId(),
                machine.getBrand(),
                machine.getInUse(),
                machine.getZuper() != null ? ZuperMapper.toZuperResponseDTO(machine.getZuper()) : null);
    }

    public static List<MachineResponseDTO> toMachineResponseDTO(List<Machine> machines){
        return machines.stream().map(MachineMapper::toMachineResponseDTO).toList();
    }
}
