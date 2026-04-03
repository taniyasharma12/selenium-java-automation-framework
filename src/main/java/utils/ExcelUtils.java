package utils;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rows = sheet.getPhysicalNumberOfRows();
        int columns = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows-1][columns];

        for (int i = 1; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                data[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();

            }
        }

        workbook.close();
        fis.close();
        return data;

    }
}

