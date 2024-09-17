package br.com.zup.machine_manager.infra.loaders;

import java.util.List;

public interface XLSXLoader {

    public <T> List<T> objectsLoad (Class<T> className);
}
