package br.com.zup.machine_manager.repository.impl;

import br.com.zup.machine_manager.infra.loaders.XLSXLoader;
import br.com.zup.machine_manager.repository.MachineRepository;
import br.com.zup.machine_manager.repository.models.Machine;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MachineRepositoryImpl implements MachineRepository {
    private List<Machine> machines;
    @Autowired
    private XLSXLoader xlsxLoader;

    @Override
    public Optional<Machine> getMachineById(String id) {
        return machines.stream().filter(machine -> machine.getRegisterId().equalsIgnoreCase(id)).findFirst();
    }

    @Override
    public List<Machine> getAllMachines() {
        return machines;
    }

    @PostConstruct
    private void loadMachines(){
        machines = xlsxLoader.objectsLoad(Machine.class);
    }
}
