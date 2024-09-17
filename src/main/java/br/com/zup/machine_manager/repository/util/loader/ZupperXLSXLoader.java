package br.com.zup.machine_manager.repository.util.loader;

import br.com.zup.machine_manager.repository.models.Zuper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("Zuper")
public class ZupperXLSXLoader implements XLSXLoader{
    @Value("${file.zuper.path}")
    private String filePath;

    private static final Logger log = LoggerFactory.getLogger(ZupperXLSXLoader.class);

    @Override
    public List<Zuper> loadObjects() {
        List<Zuper> zupers = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

            for (int index = 1; index <= sheet.getLastRowNum(); index++) {
                Row row = sheet.getRow(index);

                if (row != null) {
                    int id = (int) row.getCell(0).getNumericCellValue();
                    String name = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "";
                    String address = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "";
                    String postalCode = row.getCell(3) != null ? dataFormatter.formatCellValue(row.getCell(3)) : "";

                    zupers.add(new Zuper(id, name, address, postalCode));

                    log.info("Load data: {} {} {} {}", name, id, address, postalCode);
                }
            }

        } catch (IOException e) {
            log.error("Erro ao carregar o arquivo: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Erro inesperado: {}", e.getMessage(), e);
        }

        return zupers;
    }
}
