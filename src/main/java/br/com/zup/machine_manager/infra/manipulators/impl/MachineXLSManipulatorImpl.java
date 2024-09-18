package br.com.zup.machine_manager.infra.manipulators.impl;

import br.com.zup.machine_manager.infra.manipulators.MachineXLSXManipulator;
import br.com.zup.machine_manager.infra.util.HashGenerator;
import br.com.zup.machine_manager.repository.models.Machine;
import br.com.zup.machine_manager.repository.models.Zuper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Component
public class MachineXLSManipulatorImpl implements MachineXLSXManipulator {
    private static final Logger logger = LoggerFactory.getLogger(MachineXLSManipulatorImpl.class);

    @Value("${file.machine.path}")
    private String filePath;

    @Override
    public Machine saveObject(Machine object) {
        if (object.getRegisterId() == null) {
            object.setRegisterId(HashGenerator.generateRandomHash(5));
            return writeObject(object);
        }
        return update(object);
    }

    private Machine writeObject(Machine machine) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            Row newRow = sheet.createRow(lastRowNum + 1);

            populateRowWithZuperData(newRow, machine);

            workbook.write(fileOutputStream);
        } catch (IOException e) {
            logger.error("Error writing object to XLSX", e);
        }
        return machine;
    }

    private Machine update(Machine machine) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.getSheetAt(0);
            Optional<Row> rowToUpdate = findRowById(sheet, machine.getRegisterId());

            if (rowToUpdate.isPresent()) {
                populateRowWithZuperData(rowToUpdate.get(), machine);
                workbook.write(fileOutputStream);
                logger.info("Linha atualizada com sucesso!");
            } else {
                logger.info("ID não encontrado.");
            }

        } catch (IOException e) {
            logger.error("Error updating object in XLSX", e);
        }
        return machine;
    }

    private Optional<Row> findRowById(Sheet sheet, String resgisterMachine) {
        for (Row row : sheet) {
            Cell idCell = row.getCell(0);
            if (idCell != null && idCell.getCellType() == CellType.STRING && idCell.getStringCellValue().equals(resgisterMachine)) {
                return Optional.of(row);
            }
        }
        return Optional.empty();
    }

    private void populateRowWithZuperData(Row row, Machine machine) {
        row.createCell(0).setCellValue(machine.getRegisterId());
        row.createCell(1).setCellValue(machine.getBrand());
        row.createCell(2).setCellValue(machine.getInUse());
        row.createCell(3).setCellValue(machine.getZuper().getZuperId());
    }
}
