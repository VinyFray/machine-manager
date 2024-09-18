package br.com.zup.machine_manager.services.zuper;

import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperCreateDTO;
import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperUpdateDTO;
import br.com.zup.machine_manager.repository.models.Zuper;

import java.util.List;
import java.util.Optional;

public interface ZuperService {
    public Zuper save(ZuperCreateDTO zuperCreateDTO);

    public Zuper update(ZuperUpdateDTO zuperUpdateDTO);

    public List<Zuper> getAllZupers();

    public Zuper getZuperById(Integer id);
}
