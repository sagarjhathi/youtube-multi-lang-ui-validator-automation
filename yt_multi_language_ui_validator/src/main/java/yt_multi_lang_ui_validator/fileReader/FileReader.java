package main.java.yt_multi_lang_ui_validator.fileReader;

import java.io.File; 
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileReader {

	
	     private Workbook workbook;
	     private Sheet sheet;
	     /**
	      * Load Excel workbook by file name (relative path)
	      */
	     public void loadWorkbook(String fileName) {
	         try {
	             File file = find(fileName);
	             if (file == null || !file.exists()) {
	                 throw new RuntimeException("❌ Excel file not found: " + fileName);
	             }
	             workbook = WorkbookFactory.create(file);
	         } catch (IOException e) {
	             throw new RuntimeException("❌ Failed to load Excel file: " + fileName, e);
	         }
	     }

	     /**
	      * Select the sheet by name
	      */
	     public void loadSheet(String sheetName) {
	         if (workbook == null)
	             throw new IllegalStateException("⚠️ Workbook not loaded. Call loadWorkbook() first.");
	         sheet = workbook.getSheet(sheetName);
	         if (sheet == null)
	             throw new RuntimeException("❌ Sheet not found: " + sheetName);
	     }

	     /**
	      * Get a Row by index (0-based)
	      */
	     public Row getRow(int rowIndex) {
	         if (sheet == null)
	             throw new IllegalStateException("⚠️ Sheet not loaded. Call loadSheet() first.");
	         return sheet.getRow(rowIndex);
	     }

	     /**
	      * Get a Cell by row and column index (0-based)
	      */
	     public Cell getCell(int rowIndex, int colIndex) {
	         Row row = getRow(rowIndex);
	         return (row != null) ? row.getCell(colIndex) : null;
	     }

	     /**
	      * Get cell value as String (auto-handles numbers, booleans, etc.)
	      */
	     public String getCellValue(int rowIndex, int colIndex) {
	         Cell cell = getCell(rowIndex, colIndex);
	         if (cell == null) return "";
	         DataFormatter formatter = new DataFormatter();
	         return formatter.formatCellValue(cell).trim();
	     }

	     /**
	      * Get total row count in current sheet
	      */
	     public int getRowCount() {
	         if (sheet == null)
	             throw new IllegalStateException("⚠️ Sheet not loaded. Call loadSheet() first.");
	         return sheet.getPhysicalNumberOfRows();
	     }

	     /**
	      * Get total column count for a given row
	      */
	     public int getColumnCount(int rowIndex) {
	         Row row = getRow(rowIndex);
	         return (row != null) ? row.getPhysicalNumberOfCells() : 0;
	     }

	     /**
	      * Close the workbook safely
	      */
	     public void closeWorkbook() {
	         try {
	             if (workbook != null) workbook.close();
	         } catch (IOException e) {
	             System.err.println("⚠️ Failed to close workbook: " + e.getMessage());
	         }
	     }
	 
	     
	  
	     public static File find(String fileName) {
		        try {
		            // 1️⃣ Try direct path (handles absolute or relative)
		            File f = new File(fileName);
		            if (f.exists()) return f.getAbsoluteFile();

		            // 2️⃣ Try relative to project root (user.dir)
		            Path baseDir = Paths.get(System.getProperty("user.dir"));
		            f = baseDir.resolve(fileName).toFile();
		            if (f.exists()) return f.getAbsoluteFile();

		            // 3️⃣ Try classpath (resources in target/classes or JAR)
		            URL resource = Thread.currentThread()
		                                 .getContextClassLoader()
		                                 .getResource(fileName);
		            if (resource != null) {
		                return new File(Objects.requireNonNull(resource.getPath()));
		            }

		            // ❌ Not found anywhere
		            System.err.println("File not found: " + fileName);
		            return null;

		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
		 

}
