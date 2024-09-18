package br.com.zup.machine_manager.infra.loaders.impl;

import br.com.zup.machine_manager.infra.loaders.XLSXLoader;
import br.com.zup.machine_manager.infra.loaders.XLSXObjectsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class XLSXLoaderImpl implements XLSXLoader {

    private Map<String, XLSXObjectsLoader> xlsxLoaderMap;

    @Autowired
    public XLSXLoaderImpl(Map<String, XLSXObjectsLoader> xlsxLoaderMap) {
        this.xlsxLoaderMap = xlsxLoaderMap;
    }

    @Override
    public <T> List<T> objectsLoad(Class<T> className) {
        XLSXObjectsLoader xlsxObjectsLoader = xlsxLoaderMap.get(className.getSimpleName());
        List<?> rawList = xlsxObjectsLoader.loadObjects(); // Carrega a lista genérica

        if (!rawList.isEmpty() && className.isInstance(rawList.get(0))) {
            return (List<T>) rawList;
        } else {
            throw new ClassCastException("Os objetos carregados não são do tipo esperado: " + className.getName());
        }
    }

}
