/*
 * package Framework.InputUtilityFolder;
 * 
 * import java.io.File; import java.io.FileInputStream; import
 * java.io.IOException; import java.util.Date;
 * 
 * import org.apache.poi.hssf.usermodel.HSSFCell; import
 * org.apache.poi.hssf.usermodel.HSSFRow; import
 * org.apache.poi.hssf.usermodel.HSSFSheet; import
 * org.apache.poi.hssf.usermodel.HSSFWorkbook; import
 * org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.support.ui.WebDriverWait;
 * 
 * import Framework.BasePageFolder.BasePage;
 * 
 * public class ExcelInput extends BasePage {
 * 
 * HSSFWorkbook wb; HSSFSheet sheet; HSSFRow row1; HSSFCell cell;
 * 
 * public ExcelInput(WebDriver driver, WebDriverWait wait) { super(driver,
 * wait); } public HSSFWorkbook readExcelWB (String path) throws IOException {
 * File file = new File(path); FileInputStream inputStream = new
 * FileInputStream(file); wb = new HSSFWorkbook(inputStream); return wb; }
 * 
 * public HSSFSheet getTheSheetByName(String name) { sheet=wb.getSheet(name);
 * return sheet; }
 * 
 * //Create a row object to retrieve row at index 1 public HSSFSheet
 * getTheSheetByIndex(int index) { sheet=wb.getSheetAt(index); return sheet; }
 * 
 * //Create a row object to retrieve row at index 1 public HSSFRow getRow(int
 * index) { row1=sheet.getRow(index); return row1; }
 * 
 * //Create a row object to retrieve row at index 1 public HSSFCell getCell(int
 * rowNum, int cellNum) { cell= sheet.getRow(rowNum).getCell(cellNum); return
 * cell; }
 * 
 * public String getCellString() { return cell.getStringCellValue(); }
 * 
 * public double getCellNumericData() { return cell.getNumericCellValue(); }
 * 
 * public Date getdateFromCell() { return cell.getDateCellValue(); }
 * 
 * public int getRowCount() { return
 * sheet.getLastRowNum()-sheet.getFirstRowNum(); } }
 */