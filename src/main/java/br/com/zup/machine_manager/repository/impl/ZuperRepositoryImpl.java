package br.com.zup.machine_manager.repository.impl;

import br.com.zup.machine_manager.infra.loaders.XLSXLoader;
import br.com.zup.machine_manager.repository.ZuperRepository;
import br.com.zup.machine_manager.repository.models.Zuper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ZuperRepositoryImpl implements ZuperRepository {
    private static final Logger log = LoggerFactory.getLogger(ZuperRepositoryImpl.class);
    private List<Zuper> zupers;

    @Autowired
    private XLSXLoader xlsxLoader;

    @Override
    public Optional<Zuper> getZupByID(int id) {
        return zupers.stream().filter(zuper -> zuper.getZuperId()==id).findFirst();
    }

    @Override
    public List<Zuper> getAllZupers() {
        return zupers;
    }

    @PostConstruct
    private void loadZupers(){
        try{
          zupers = xlsxLoader.objectsLoad(Zuper.class);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
