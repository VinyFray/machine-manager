package br.com.zup.machine_manager.repository.impl;

import br.com.zup.machine_manager.infra.loaders.XLSXLoader;
import br.com.zup.machine_manager.infra.manipulators.MachineXLSXManipulator;
import br.com.zup.machine_manager.repository.MachineRepository;
import br.com.zup.machine_manager.repository.models.Machine;
import br.com.zup.machine_manager.repository.models.Zuper;
import br.com.zup.machine_manager.services.zuper.ZuperService;
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
    @Autowired
    private ZuperService zuperService;
    @Autowired
    private MachineXLSXManipulator machineXLSXManipulator;

    @Override
    public Optional<Machine> getMachineById(String id) {
        return machines.stream().filter(machine -> machine.getRegisterId().equalsIgnoreCase(id)).findFirst();
    }

    @Override
    public List<Machine> getAllMachines() {
        return machines;
    }

    public Machine save(Machine machine){
        if(machine.getZuper() != null && machine.getZuper().getZuperId() != 0){
            Zuper zuper = zuperService.getZuperById(machine.getZuper().getZuperId());
            machine.setZuper(zuper);
        }
        Machine finalMachine = machineXLSXManipulator.saveObject(machine);;
        machines = machines.stream()
                .map(obj -> obj.getRegisterId().equals(finalMachine.getRegisterId()) ? finalMachine : obj).toList();
        return machine;
    }

    @PostConstruct
    private void loadMachines(){
        machines = xlsxLoader.objectsLoad(Machine.class);
    }
}
