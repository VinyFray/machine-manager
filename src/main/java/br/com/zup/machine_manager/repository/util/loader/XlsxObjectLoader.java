package br.com.zup.machine_manager.repository.util.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class XlsxObjectLoader {

    @Autowired
    private Map<String, XLSXLoader> xlsxLoaderMap;

    public <T> List<T> objectsLoad(Class<T> className) {
        XLSXLoader xlsxLoader = xlsxLoaderMap.get(className.getSimpleName());
        List<?> rawList = xlsxLoader.loadObjects(); // Carrega a lista genérica

        if (!rawList.isEmpty() && className.isInstance(rawList.get(0))) {
            return (List<T>) rawList;
        } else {
            throw new ClassCastException("Os objetos carregados não são do tipo esperado: " + className.getName());
        }
    }
}
