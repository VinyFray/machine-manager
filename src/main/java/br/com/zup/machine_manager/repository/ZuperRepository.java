package br.com.zup.machine_manager.repository;

import br.com.zup.machine_manager.repository.models.Zuper;

import java.util.List;
import java.util.Optional;

public interface ZuperRepository {

    public Optional<Zuper> getZupByID(int id);

    public List<Zuper> getAllZupers();

}
