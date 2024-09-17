package br.com.zup.machine_manager.repository;

import br.com.zup.machine_manager.repository.models.Machine;

import java.util.List;
import java.util.Optional;

public interface MachineRepository {

    public Optional<Machine> getMachineById(String id);

    public List<Machine> getAllMachines();
}
