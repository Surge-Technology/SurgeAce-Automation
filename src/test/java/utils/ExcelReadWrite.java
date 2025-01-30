package utils;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
public class ExcelReadWrite {
	
	private String filePath;
    private int sheetIndex;
    
    private String path;
    private FileInputStream fis = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row;
    private XSSFCell cell;
    private final FileOutputStream fileOut = null;
    private String businessEffectiveDate;
    
    public ExcelReadWrite() {
    	
    }
    
    public ExcelReadWrite(String path) {
    	this.path = path;
    	try {
    		fis = new FileInputStream(new File(path));
    		System.out.println("workbook before");
    		workbook = new XSSFWorkbook(fis);
    		System.out.println("workbook after");
    		sheet = workbook.getSheetAt(0);
    		fis.close();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    }
    
    public ExcelReadWrite(String filePath, int sheetIndex) {
        this.filePath = filePath;
        this.sheetIndex = sheetIndex;
    }
    private HSSFSheet getSheet() throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        return sheet;
    }
    public Map<String, Map<String, String>> getExcelAsMap() throws IOException {
        HSSFSheet sheet = getSheet();
        Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
        List<String> columnHeader = new ArrayList<String>();
        Row row = sheet.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            columnHeader.add(cellIterator.next().getStringCellValue());
        }
        int rowCount = row.getLastCellNum();
        int columnCount = row.getLastCellNum();
        for (int i = 1; i <= rowCount; i++) {
            Map<String, String> singleRowData = new HashMap<String, String>();
            Row row1 = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row1.getCell(j);
                singleRowData.put(columnHeader.get(j), getCellValueAsString(cell));
            }
            completeSheetData.put(String.valueOf(i), singleRowData);
        }
        return completeSheetData;
    }
   public String getCellValueAsString(Cell cell) {
        String cellValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue= cell.getCellFormula();
            case BLANK:
                cellValue="BLANK";
            default:
                cellValue ="DEFAULT";
        }
        return cellValue;
    }

    public String getSheetName(int index) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        String sheet = workbook.getSheetName(index);
        return sheet;
    }
    public int getSheetCount() throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        int sheetCount = workbook.getNumberOfSheets();
        return sheetCount;
    }
    public int totolColumnCount() throws IOException {
        HSSFSheet sheet = getSheet();
        Row row = sheet.getRow(0);
        int lastColumnNum = row.getLastCellNum();
        return lastColumnNum;
    }
      
	public static Map<String,  Map<String, String>> setMapData() throws IOException {

		  String path = "data/TestDataSheet.xlsx";
		  FileInputStream fis = new FileInputStream(path);
		  Workbook workbook = new XSSFWorkbook(fis);
		  Sheet sheet = (Sheet) workbook.getSheetAt(0);
		  int lastRow = ((org.apache.poi.ss.usermodel.Sheet) sheet).getLastRowNum();
		  Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String,String>>();
		  Map<String, String> dataMap = new HashMap<String, String>();
		  //Looping over entire row
		  for(int i=0; i<=lastRow; i++){
			  Row row = ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(i);
			  //1st Cell as Value
			  Cell valueCell = row.getCell(1);
			 //0th Cell as Key
			  Cell keyCell = row.getCell(0);
			 
			  String value = valueCell.getStringCellValue().trim();
			  String key = keyCell.getStringCellValue().trim();
				  
			  //Putting key & value in dataMap
			  dataMap.put(key, value);
				  
			  //Putting dataMap to excelFileMap
			  excelFileMap.put("DataSheet", dataMap);
		  }
		  
		 //Returning excelFileMap
		return excelFileMap;

	}
	
	//Method to retrieve value
	public static String getMapData(String key) throws IOException{

	Map<String, String> m = setMapData().get("DataSheet");
		String value = m.get(key);
		
		return value;
		
	}
	
	public synchronized Map<String, String> getRowData(String sheetName, String columnName, String columnValue){
		String column, value;
		Map<String, String> map = new LinkedHashMap<String, String>();
		int rowNum = 0;
		try {
				if(!isSheetExists(sheetName))
					return null;
				int rowCount = getRowCount(sheetName);
				
				for(int j=0; j<rowCount; j++) {
					if(getCellData(sheetName, j+1, columnName).equalsIgnoreCase(columnValue)) {
						rowNum = j;
						break;
					}
				}
				XSSFRow dataRow = workbook.getSheet(sheetName).getRow(rowNum);
				if(dataRow == null)
					return null;
				XSSFRow firstRow = workbook.getSheet(sheetName).getRow(0);
				
				int colCount = workbook.getSheet(sheetName).getRow(0).getLastCellNum();
				for(int i = 0; i < colCount; i++) {
					column = firstRow.getCell(i).getStringCellValue().trim();
					value = dataRow.getCell(i).getStringCellValue().trim();
					if(column.equalsIgnoreCase("BuisnessEffectiveDate")) {
						if(value.equals("<BuisnessEffectiveDate>")) {
							map.put(column,  businessEffectiveDate);
						}
						else {
							map.put(column, value);
						}
					}
					else {
							map.put(column, value);
						}
				}
				return map;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean isSheetExists(String sheetName) {
		try {
			int sheetIndex = workbook.getSheetIndex(sheetName);
			return sheetIndex >= 0;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	
	public int getRowCount(String sheetName) {
		try {
			if(isSheetExists(sheetName)) {
				return workbook.getSheet(sheetName).getLastRowNum() + 1;
			}
			else {
				return 0;
			}
		}
		catch(Exception e) {
			System.out.println(e);
			return 0;

		}
	}
	
	
	public String getCellData(String sheetName, int rowNum, String colName) {
		try {
			if(!isSheetExists(sheetName)) 
				return "";
				
			int lastColNum = workbook.getSheet(sheetName).getRow(0).getLastCellNum();
			int colNum = -1;
			row = workbook.getSheet(sheetName).getRow(0);
			for(int i=0; i< lastColNum; i++) {
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
					colNum = i;
					break;
				}
			}
			if (colNum == -1)
					return "";
			return getCellData(sheetName, rowNum, colNum + 1);
			}
		
		catch(Exception e)
		{
			System.out.println(e);
			return "";
		}
		
	}
	
	
	public String getCellData(String sheetName, int rowNum, int colNum) {
		try {
			if(isSheetExists(sheetName)) {
				if(rowNum < 0)
					return "";
				
				row = workbook.getSheet(sheetName).getRow(rowNum - 1);
				if(row == null)
					return null;
				
				cell = row.getCell(colNum - 1);
				if(cell == null)
					return null;
				
		       	switch (cell.getCellTypeEnum()) {
	            case NUMERIC:
	                return String.valueOf(cell.getNumericCellValue()).trim();
	                
	            case STRING:
	               return cell.getStringCellValue().trim();
	            case BLANK:
	                return "";
	            default:
	            	 return "";
			}
			}
		    else
		    	return "";
		}
		catch(Exception e)
		{
			System.out.println(e);
			return "";
		}
		
		}
	
	public void writeToExcel(String filePath, String sheetName, Object[][] columnNames, Object[][] columnValues) {
		 // Creating file object of existing excel file
        File xlsxFile = new File(System.getProperty("user.dir")+filePath);
        
        try {
            //Creating input stream
            FileInputStream inputStream = new FileInputStream(xlsxFile);
             
            //Creating workbook from input stream
            Workbook workbook = WorkbookFactory.create(inputStream);
 
            //Reading sheet of excel file
            XSSFSheet sheet = (XSSFSheet) workbook.getSheet(sheetName);
          
            //**********************************************************************
            int rowCount = getRowCount(sheetName); // ex: 4rows
            for(int i=1; i<=rowCount;i++) {  
            	//if(getCellData(sheetName, 1 , columnNames[i-1].toString()).equalsIgnoreCase(columnNames[i-1].toString())) {
            		// Row row = sheet.createRow(i+1);
            		 Row row = sheet.getRow(i+1);
            		 //Case ID
            		 Cell cell1 = row.getCell(1);
            		 Cell cell2 = row.getCell(3);
            		 Cell cell3 = row.getCell(4);
            		 Cell cell4 = row.getCell(5);
            		 Cell cell5 = row.getCell(12);
            		 
                     if (columnValues.toString() instanceof String) {
                         cell1.setCellValue(columnValues[i-1].toString());
                         cell2.setCellValue(columnValues[i].toString());
                         cell3.setCellValue(columnValues[i+1].toString());
                         cell4.setCellValue(columnValues[i+2].toString());
                         cell5.setCellValue(columnValues[i+3].toString());
                        
                     } /*else if (Integer.parseInt(columnValues[i-1]) instanceof Integer) {
                         cell.setCellValue((Integer) Integer.parseInt(columnValues[i-1]));
                     }*/
            	//}
            }
            
            
            //**********************************************************************
          
            //Close input stream
            inputStream.close();
 
            //Crating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(xlsxFile);
            workbook.write(os);
             
            //Close the workbook and output stream
            workbook.close();
            os.close();
             
            System.out.println("Excel file has been updated successfully.");
             
        } 
        catch (IOException e) {
            System.err.println("Exception while updating an existing excel file.");
            e.printStackTrace();
        }
		
	}
	
	
	}
