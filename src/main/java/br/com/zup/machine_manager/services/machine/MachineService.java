package br.com.zup.machine_manager.services.machine;

import br.com.zup.machine_manager.controllers.machine.dtos.MachineCreateDTO;
import br.com.zup.machine_manager.controllers.machine.dtos.MachineUpdateDTO;
import br.com.zup.machine_manager.repository.models.Machine;

import java.util.List;

public interface MachineService {
    public Machine save(MachineCreateDTO machineCreateDTO);

    public Machine update(MachineUpdateDTO machineUpdateDTO);

    public List<Machine> getAllMachines();

    public Machine getMachineById(String registerId);
}
