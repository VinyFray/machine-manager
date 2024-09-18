package br.com.zup.machine_manager.services.zuper;

import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperCreateDTO;
import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperUpdateDTO;
import br.com.zup.machine_manager.repository.ZuperRepository;
import br.com.zup.machine_manager.repository.models.Zuper;
import br.com.zup.machine_manager.services.zuper.mappers.ZuperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZuperServiceImpl implements ZuperService {
    @Autowired
    private ZuperRepository zuperRepository;

    public Zuper save(ZuperCreateDTO zuperCreateDTO){
        Zuper zuper = ZuperMapper.toZuper(zuperCreateDTO);
        return zuperRepository.save(zuper);
    }

    @Override
    public Zuper update(ZuperUpdateDTO zuperUpdateDTO) {
        Zuper zuper = ZuperMapper.toZuper(zuperUpdateDTO);
        return zuperRepository.save(zuper);
    }

    @Override
    public List<Zuper> getAllZupers() {
        return zuperRepository.getAllZupers();
    }

    @Override
    public Zuper getZuperById(Integer id) {
        return zuperRepository.getZupByID(id).orElseThrow(() -> new RuntimeException("not found"));
    }

}
