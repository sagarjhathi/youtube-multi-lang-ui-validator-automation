package main.java.yt_multi_lang_ui_validator.fileWriter;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileWriter {

    private Workbook workbook;
    private Sheet sheet;

    /**
     * Create a new Excel workbook
     */
    public void createWorkbook() {
        workbook = new XSSFWorkbook();
    }

    /**
     * Create a new sheet
     */
    public void createSheet(String sheetName) {
        if (workbook == null)
            throw new IllegalStateException("⚠️ Workbook not created. Call createWorkbook() first.");

        sheet = workbook.createSheet(sheetName);
    }

    /**
     * Write a value to a cell (auto-creates row & cell)
     */
    public void writeCell(int rowIndex, int colIndex, String value) {
        if (sheet == null)
            throw new IllegalStateException("⚠️ Sheet not created. Call createSheet() first.");

        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        row.createCell(colIndex).setCellValue(value);
    }


    
    
    public void save(String filePath) {

        if (workbook == null) {
            throw new IllegalStateException("Workbook not created");
        }

        File file = new File(filePath);

        // ✅ Convert relative path to absolute path
        if (!file.isAbsolute()) {
            file = new File(System.getProperty("user.dir"), filePath);
        }

        File parent = file.getParentFile();

        try {
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }

            System.out.println("Excel written to: " + file.getAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("Failed to save Excel file", e);
        } finally {
            try {
                workbook.close();
            } catch (IOException ignored) {}
        }
    }

    
    
    
    
   



    /**
     * Close the workbook safely
     */
    public void close() {
        try {
            if (workbook != null) workbook.close();
        } catch (IOException e) {
            System.err.println("⚠️ Failed to close workbook: " + e.getMessage());
        }
    }
}

