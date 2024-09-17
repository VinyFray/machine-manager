package br.com.zup.machine_manager.infra.loaders.impl;

import br.com.zup.machine_manager.infra.loaders.XLSXObjectsLoader;
import br.com.zup.machine_manager.repository.ZuperRepository;
import br.com.zup.machine_manager.repository.models.Machine;
import br.com.zup.machine_manager.repository.models.Zuper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("Machine")
public class MachineXLSXObjectsLoader implements XLSXObjectsLoader {
    private static final Logger log = LoggerFactory.getLogger(MachineXLSXObjectsLoader.class);
    @Autowired
    private ZuperRepository zuperRepository;

    @Value("${file.machine.path}")
    private String filePath;

    @Override
    public List<Machine> loadObjects() {
        List<Machine> machines = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter(); // Para formatar valores de células

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int index = 1; index <= sheet.getLastRowNum(); index++) {
                Row row = sheet.getRow(index);

                if (row != null) {
                    String registerID = dataFormatter.formatCellValue(row.getCell(0));
                    String brand = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "";
                    Boolean isInUse = row.getCell(2) != null && row.getCell(2).getBooleanCellValue();
                    Integer zuperID = row.getCell(3) != null ? (int) row.getCell(3).getNumericCellValue() : null;

                    addMachine(machines, registerID, brand, isInUse, zuperID);

                    log.info("Load data: {} {} {} {}", registerID, brand, isInUse, zuperID);
                }
            }

        } catch (IOException e) {
            log.error("Erro ao carregar o arquivo: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Erro inesperado: {}", e.getMessage(), e);
        }

        return machines;
    }

    private void addMachine(List<Machine> machines, String registerID, String brand, Boolean isInUse, Integer zuperID) {
        if (zuperID != null) {
            Optional<Zuper> zuper = zuperRepository.getZupByID(zuperID);
            zuper.ifPresentOrElse(
                    value -> machines.add(new Machine(registerID, brand, isInUse, value)),
                    () -> machines.add(new Machine(registerID, brand, isInUse))
            );
        } else {
            machines.add(new Machine(registerID, brand, isInUse));
        }
    }
}
