package com.Vcidex.StoryboardSystems.Common.Helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    private final FileInputStream fis;

    public ExcelReader(FileInputStream fis) {
        this.fis = fis;
    }

    public Iterator<Object[]> getExcelData(String sheetName) {
        List<Object[]> data = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> rowData = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    rowData.add(cell.toString().trim());
                }
                data.add(rowData.toArray());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel sheet: " + sheetName, e);
        }
        return data.iterator();
    }
}