package com.Vcidex.StoryboardSystems.Common.Helpers;

import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class DataProviderHelper {

    @DataProvider(name = "ExcelDataProvider")
    public Iterator<Object[]> getDataFromExcel() {
        String filePath = "src/test/resources/testData.xlsx";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            ExcelReader excelReader = new ExcelReader(fis);
            return excelReader.getExcelData("Sheet1");
        } catch (IOException e) {
            throw new RuntimeException("Error opening Excel file: " + filePath, e);
        }
    }
}