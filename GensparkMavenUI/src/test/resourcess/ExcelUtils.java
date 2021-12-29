package test.resourcess;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

import static test.resourcess.ExcelUtils.readTestData;


public class ExcelUtils {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;

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

    public static Map<String, Map<String, String>> readTestData() throws IOException {
        String path = "/Users/cb-sarathkumar/Downloads/demo.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sh = wb.getSheetAt(0);
        int lastRow = sh.getLastRowNum();
        Map<String, Map<String, String>> fileMap = new HashMap<>();
        Map<String, String> dataMap = new HashMap<>();
        for (int i = 0; i <= lastRow; i++) {
            Row headers = sh.getRow(0);
            Row row = sh.getRow(i);
            Cell keyCol = row.getCell(0);
            for (int j = 1; j < row.getLastCellNum(); j++) {
                dataMap.put(headers.getCell(j).toString(), row.getCell(j).toString());
            }
            fileMap.put(keyCol.toString(),dataMap);
        }
        return fileMap;
    }
}

class demo {
    public static void main(String[] args) throws IOException {
        ExcelUtils excelUtils = new ExcelUtils();
//        excelUtils.setExcelFile("/Users/cb-sarathkumar/Downloads/demo.xlsx","Sheet1");
//        System.out.println(excelUtils.getCellData(1,1));
        readTestData();
    }
}