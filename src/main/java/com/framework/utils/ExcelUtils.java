package com.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

    public static String readCell(String filePath, int row, int col) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = sheet.getRow(row).getCell(col);
        return cell.getStringCellValue();
    }
    public static List<List<String>> readDataTable(String filePath) throws IOException {
        return readDataTable(filePath, 0); // Default to first sheet (sheet index = 0)
    }

    // Overloaded method to read a complete data table from a specified sheet index
    public static List<List<String>> readDataTable(String filePath, int sheetIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(sheetIndex);
            List<List<String>> dataTable = new ArrayList<>();

            // Iterate through rows and columns
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<String> rowData = new ArrayList<>();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    rowData.add(cell.getStringCellValue()); // Assuming all cells are strings
                }
                dataTable.add(rowData);
            }
            return dataTable;
        }
    }

    // Overloaded method to read a complete row (all columns of a given row)
    public static List<String> readRow(String filePath, int rowIndex) throws IOException {
        return readRow(filePath, rowIndex, 0); // Default to first sheet (sheet index = 0)
    }

    // Overloaded method to read a complete row from a specified sheet index
    public static List<String> readRow(String filePath, int rowIndex, int sheetIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = sheet.getRow(rowIndex);
            List<String> rowData = new ArrayList<>();

            // Iterate through cells in the row
            for (Cell cell : row) {
                rowData.add(cell.getStringCellValue()); // Assuming all cells are strings
            }
            return rowData;
        }
    }

    // Overloaded method to read a complete column (all rows of a given column)
    public static List<String> readColumn(String filePath, int colIndex) throws IOException {
        return readColumn(filePath, colIndex, 0); // Default to first sheet (sheet index = 0)
    }

    // Overloaded method to read a complete column from a specified sheet index
    public static List<String> readColumn(String filePath, int colIndex, int sheetIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(sheetIndex);
            List<String> columnData = new ArrayList<>();

            // Iterate through rows and get the value of the specified column
            for (Row row : sheet) {
                Cell cell = row.getCell(colIndex);
                columnData.add(cell.getStringCellValue()); // Assuming all cells are strings
            }
            return columnData;
        }
    }
}
