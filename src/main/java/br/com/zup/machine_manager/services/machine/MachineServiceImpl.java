package br.com.zup.machine_manager.services.machine;


import br.com.zup.machine_manager.controllers.machine.dtos.MachineCreateDTO;
import br.com.zup.machine_manager.controllers.machine.dtos.MachineUpdateDTO;
import br.com.zup.machine_manager.repository.MachineRepository;
import br.com.zup.machine_manager.repository.models.Machine;
import br.com.zup.machine_manager.services.machine.mappers.MachineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineRepository machineRepository;

    @Override
    public Machine save(MachineCreateDTO machineCreateDTO) {
        Machine machine = MachineMapper.toMachine(machineCreateDTO);
        return machineRepository.save(machine);
    }

    @Override
    public Machine update(MachineUpdateDTO machineUpdateDTO) {
        Machine machine = MachineMapper.toMachine(machineUpdateDTO);
        return machineRepository.save(machine);
    }

    @Override
    public List<Machine> getAllMachines() {
        return machineRepository.getAllMachines();
    }

    @Override
    public Machine getMachineById(String registerId) {
        return machineRepository.getMachineById(registerId).orElseThrow(() -> new RuntimeException("not found"));
    }
}
