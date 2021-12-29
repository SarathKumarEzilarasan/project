package test.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;

    public static Map<String, Map<String, String>> readExcel() throws IOException {
        //<testName,<ColHeader,ColValue>> ---> <testName,List<String>>
        Map<String, Map<String, String>> fileData = new HashMap<>();
        File file = new File("/Users/cb-sarathkumar/Downloads/demo.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        XSSFRow headers = sheet.getRow(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            Map<String, String> colMap = new HashMap<>();
            for (int j = 1; j < row.getLastCellNum(); j++) {
                colMap.put(headers.getCell(j).toString(), row.getCell(j).toString());
            }
                fileData.put(row.getCell(0).toString(), colMap);
        }
        return fileData;
    }

    public void setExcelFile(String excelFilePath, String sheetName) throws IOException {
        //Create an object of File class to open xls file
        File file = new File(excelFilePath);
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        //creating workbook instance that refers to .xls file
        workbook = new XSSFWorkbook(inputStream);
        //creating a Sheet object
        sheet = workbook.getSheet(sheetName);
    }

    public String getCellData(int rowNumber, int cellNumber) {
        //getting the cell value from rowNumber and cell Number
        cell = sheet.getRow(rowNumber).getCell(cellNumber);

        //returning the cell value as string
        return cell.getStringCellValue();
    }

    public int getRowCountInSheet() {
        int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        return rowcount;
    }

    public void setCellValue(int rowNum, int cellNum, String cellValue, String excelFilePath) throws IOException {
        //creating a new cell in row and setting value to it
        sheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }



}