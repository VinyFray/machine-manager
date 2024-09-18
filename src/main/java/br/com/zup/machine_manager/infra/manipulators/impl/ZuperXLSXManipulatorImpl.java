package br.com.zup.machine_manager.infra.manipulators.impl;

import br.com.zup.machine_manager.infra.manipulators.ZuperXLSXManipulator;
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
public class ZuperXLSXManipulatorImpl implements ZuperXLSXManipulator {
    private static final Logger logger = LoggerFactory.getLogger(ZuperXLSXManipulatorImpl.class);

    @Value("${file.zuper.path}")
    private String filePath;

    @Override
    public Zuper saveObject(Zuper object) {
        if (object.getZuperId() == 0) {
            object.setZuperId((int) (Math.random() * 1000));
            return writeObject(object);
        }
        return update(object);
    }

    private Zuper writeObject(Zuper zuper) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            Row newRow = sheet.createRow(lastRowNum + 1);

            populateRowWithZuperData(newRow, zuper);

            workbook.write(fileOutputStream);
        } catch (IOException e) {
            logger.error("Error writing object to XLSX", e);
        }
        return zuper;
    }

    private Zuper update(Zuper zuper) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

            Sheet sheet = workbook.getSheetAt(0);
            Optional<Row> rowToUpdate = findRowById(sheet, zuper.getZuperId());

            if (rowToUpdate.isPresent()) {
                populateRowWithZuperData(rowToUpdate.get(), zuper);
                workbook.write(fileOutputStream);
                logger.info("Linha atualizada com sucesso!");
            } else {
                logger.info("ID não encontrado.");
            }

        } catch (IOException e) {
            logger.error("Error updating object in XLSX", e);
        }
        return zuper;
    }

    private Optional<Row> findRowById(Sheet sheet, int zuperId) {
        for (Row row : sheet) {
            Cell idCell = row.getCell(0);
            if (idCell != null && idCell.getCellType() == CellType.NUMERIC && idCell.getNumericCellValue() == zuperId) {
                return Optional.of(row);
            }
        }
        return Optional.empty();
    }

    private void populateRowWithZuperData(Row row, Zuper zuper) {
        row.createCell(0).setCellValue(zuper.getZuperId());
        row.createCell(1).setCellValue(zuper.getName());
        row.createCell(2).setCellValue(zuper.getAddress());
        row.createCell(3).setCellValue(zuper.getPostalCode());
    }
}