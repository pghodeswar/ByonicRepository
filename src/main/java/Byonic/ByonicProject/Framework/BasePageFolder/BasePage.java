package Byonic.ByonicProject.Framework.BasePageFolder;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Byonic.ByonicProject.Framework.RandomDataGenerator.RandomDataGenerator;


public class BasePage extends Page {
	public Robot robot;
	public int randomNumber;

	public BasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	//Launch the URL 
	public void LaunchURL(String URL)
	{
		driver.get(URL);
	}
	// *************************************************************************************************
	// TEXT FUNCTIONS
	// *************************************************************************************************
	// Function To set value in the TextField
	public BasePage SetTextWithFieldID(String elementID, String value) {
		ClearTextAreaUsingID(elementID);
		driver.findElement(By.id(elementID)).sendKeys(value);
		return this;
	}

	// Set Number using ID
	public BasePage SetNumberUsingID(String elementID, int value) {

		driver.findElement(By.id(elementID)).sendKeys(String.valueOf(value));
		return this;
	}

	// Function to set text using XPath
	public BasePage SetTextWithFieldXPath(String elementXPath, String value) 
	{        
		driver.findElement(By.xpath(elementXPath)).sendKeys(value);
		return this;
	}

	// Function to get the value of the TextField using ID
	public String GetText(String id) {
		return driver.findElement(By.id(id)).getText();
	}

	// Functions to get the value of the TextField using XPath
	public String GetTextUsingXPath(String XPath) throws InterruptedException {
		Thread.sleep(3000);
		//return driver.findElement(By.xpath(XPath)).getText();
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath))).getText();
	}	
	
	// Functions to get the value of the TextField using XPath
		public String GetInnerTextUsingXPath(String XPath) throws InterruptedException {
			Thread.sleep(3000);
			//return driver.findElement(By.xpath(XPath)).getText();
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath))).getAttribute("innerHTML");
		}
		
		public String GetInnerTextUsingWebElement(WebElement element) throws InterruptedException {
			Thread.sleep(3000);			
			return element.getAttribute("innerHTML");
		}

	// Clicks on text field
	public BasePage ClickOnInputArea(String XPath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath))).click();
		return this;
	}

	// Clears the field data
	public BasePage ClearTextAreaUsingXpath(String XPath) {
		driver.findElement(By.xpath(XPath)).clear();
		return this;
	}

	// Clears field using ID
	public BasePage ClearTextAreaUsingID(String ID) {
		driver.findElement(By.id(ID)).clear();
		return this;
	}

	// *******************************************************************************************
	// LINK ACTIONS
	// *******************************************************************************************
	// Clicks on Link
	public BasePage ClickOnLink(String LinkName) {
		driver.findElement(By.linkText(LinkName)).click();
		return this;
	}

	// Gets the text of link
	public String GetLinkTextByID(String ID) {
		return driver.findElement(By.id(ID)).getText();

	}

	// Checks if the Link is enable?
	public Boolean IsLinkEnable(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	

	// *************************************************************************************************
	// DROPDOWN ACTIONS
	// *************************************************************************************************
	// Expands the dropdown
	public WebElement ExpandDropdown(String DropdownID) {
		WebElement dropdownElement = driver.findElement(By.id(DropdownID));
		dropdownElement.click();
		return dropdownElement;
	}

	// Select from dropDown using visibleText
	public BasePage SelectWithVisibleText(String dropdownID, String VisibleText) {
		WebElement testDropDown = driver.findElement(By.id(dropdownID));
		Select sel = new Select(testDropDown);
		sel.selectByVisibleText(VisibleText);
		return this;
	}

	// Select from dropDown using value
	public BasePage SelectWithValue(String dropdownID, String Value) {
		WebElement testDropDown = driver.findElement(By.id(dropdownID));
		Select sel = new Select(testDropDown);
		sel.selectByValue(Value);
		return this;
	}

	// Selects with visible text using XPath
	public BasePage SelectWithVisibleTextUsingXPath(String XPath, String VisibleText) {
		WebElement testDropDown = driver.findElement(By.xpath(XPath));
		Select sel = new Select(testDropDown);
		sel.selectByVisibleText(VisibleText);
		return this;
	}
	
	// Selects with visible text using XPath
		public BasePage SelectWithValueUsingXPath(String XPath, String value) {
			WebElement testDropDown = driver.findElement(By.xpath(XPath));
			Select sel = new Select(testDropDown);
			sel.selectByValue(value);
			return this;
		}

	// Select from dropDown using index
	public BasePage SelectWithIndex(String dropdownID, int index) {
		WebElement testDropDown = driver.findElement(By.id(dropdownID));
		Select sel = new Select(testDropDown);
		sel.selectByIndex(index);
		return this;
	}

	// DeSelect from dropdown using visibleText
	public BasePage DeSelectWithVisibleText(String dropdownID, String VisibleText) {
		WebElement testDropDown = driver.findElement(By.id(dropdownID));
		Select sel = new Select(testDropDown);
		sel.deselectByVisibleText(VisibleText);
		return this;
	}

	// DeSelect from dropDown using index
	public BasePage DeSelectWithIndex(String dropdownID, int index) {
		WebElement testDropDown = driver.findElement(By.id(dropdownID));
		Select sel = new Select(testDropDown);
		sel.deselectByIndex(index);
		return this;
	}

	// *************************************************************************************************
	// DATE ACTIONS
	// *************************************************************************************************
	// Click on the date field
	public BasePage ClickOnDateField(String dateFieldID) {
		driver.findElement(By.xpath("//input[@id='" + dateFieldID + "']")).click();
		return this;
	}

	// Selects the date from the calendar
	public BasePage SelectDateFromCalendar(int DateValue) {
		driver.findElement(
				By.xpath("//div[@class='datepicker dropdown-menu']" + "//tr/td[contains(text(),'" + DateValue + "')]"))
				.click();
		return this;
	}

	// Selects the complete date from calendar
	public BasePage SetDate(String dateFieldID, int value) {
		ClickOnDateField(dateFieldID);
		SelectDateFromCalendar(value);
		return this;
	}

	// Select tommorow's date
	public BasePage SelectFirstDayOfNextMonth(String dateFieldID) {
		ClickOnDateField(dateFieldID);
		driver.findElement(By.xpath("(//td[@class='new day'])[1]")).click();
		return this;
	}

	public String getTodaysDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("M_dd_yyyy");		
		return formatter.format(date);
	}
	
	public String getNextDateByDays(int numberOfDays) {
		 Calendar c = Calendar.getInstance(); // starts with today's date and time
		 c.add(Calendar.DAY_OF_YEAR, numberOfDays);  // advances day by 2
		 Date date = c.getTime(); // gets modified time	  
		 SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy");	    
		 return formatter.format(date);
	}
	
	public String getFutureDateByDays(int numberOfDays) {
		 Calendar c = Calendar.getInstance(); // starts with today's date and time
		 c.add(Calendar.DAY_OF_YEAR, numberOfDays);  // advances day by 2
		 Date date = c.getTime(); // gets modified time	  
		 SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy");	    
		 return formatter.format(date);
	}
	// *************************************************************************************************
	// BUTTON ACTIONS
	// *************************************************************************************************
	// Clicks on button
	public BasePage ClickUsingID(String buttonID) {
		driver.findElement(By.id(buttonID)).click();
		return this;
	}

	// Clicks on button using Xpath
	public BasePage ClickUsingXPath(String XPath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath)));
		driver.findElement(By.xpath(XPath)).click();
		return this;
	}

	// Fetches name of the button
	public BasePage GetButtonName(String buttonName) {
		driver.findElement(By.name(buttonName)).click();
		return this;
	}

	// Fetches the current status of button{Enable/Disable}
	public boolean IsEnable(String buttonID) {
		return driver.findElement(By.id(buttonID)).isEnabled();
	}

	// Checks if the element is enable
	public boolean IsEnableUsingXPath(String XPath) {
		return driver.findElement(By.xpath(XPath)).isEnabled();
	}

	// Verifies if the button/radio button/checkbox is selected
	public boolean IsSelected(String buttonID) {
		return driver.findElement(By.id(buttonID)).isSelected();
	}

	// Verifies if the button is displayed on the screen
	public boolean IsDisplayedUsingID(String buttonID) {
		return driver.findElement(By.id(buttonID)).isDisplayed();
	}

	// Verifies if the button is displayed on the screen using xpath
	public boolean IsDisplayedUsingXPath(String XPath) {
		return driver.findElement(By.xpath(XPath)).isDisplayed();
	}

	// // Verifies if the button is exists on the screen using xpath
	public boolean IsExistsUsingXPath(String XPath) {
		boolean exists = driver.findElements(By.xpath(XPath)).size() != 0;
		return exists;
	}

	// *************************************************************************************************
	// ALERT ACTIONS
	// *************************************************************************************************
	// Accept the alert
	public BasePage AcceptAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		return this;
	}

	// Dismiss the alert
	public BasePage DismissAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
		return this;
	}

	// Fetches the alert Message
	public String GetMessageOfAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
		String Message = driver.switchTo().alert().getText();
		return Message;
	}

	// Allow the alert
	public BasePage ClickOnAllowAlert() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_TAB);
		return this;
	}

	// Get popup text
	public String getPopupTextUsingID(String ID) throws InterruptedException {
		Thread.sleep(2000);
		return GetText(ID);
	}

	// ************************************************************************************************
	// KEYBOARD ACTIONS
	// *************************************************************************************************
	// Press the Tab key and release
	public BasePage PressTabKeyAndRelease() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		return this;
	}

	// Press the Enter key and release
	public BasePage PressEnterKey() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		return this;
	}

	// Press the ESC key and release
	public BasePage PressESCKey() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		return this;
	}

	// Press the Down key and release
	public BasePage PressDownKey() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		return this;
	}

	// Press the Down and Enter and release
	public BasePage PressDownAndEnter(String Xpath) {
		WebElement element = driver.findElement(By.xpath(Xpath));
		element.sendKeys(Keys.DOWN, Keys.RETURN);
		return this;
	}
	
	// Zoom out
		public BasePage zoomOut(int times) throws AWTException {
			Robot robot = new Robot();
			for (int i = 0; i < times; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
			return this;
		}

	// ************************************************************************************************
	// Frame Functions
	// ************************************************************************************************
	// Switch to frame using XPath
	public BasePage SwitchToFrame(String Xpath) {
		driver.switchTo().frame(driver.findElement(By.xpath(Xpath)));
		return this;
	}

	// Switch to frame Using ID
	public BasePage SwitchToFrameUsingID(String ID) {
		WebElement Frame = driver.findElement(By.id(ID));
		driver.switchTo().frame(Frame);
		return this;
	}

	// Close the opened frame using cross sign
	public BasePage CloseFrameWindow() throws InterruptedException {
		SwitchToDefaultWindow();
		ClickUsingXPath("//button[@title='Close']");
		return this;
	}
	
	public BasePage SwitchToDefaultFrame() {
		driver.switchTo().defaultContent();
		return this;
	}

	// *************************************************************************************************
	// Parent child window actions
	// ********************************************************************************************
	// Switches to default window
	public BasePage SwitchToDefaultWindow() {
		driver.switchTo().defaultContent();
		return this;
	}

	public BasePage CloseTheCurrentWindow() {
		driver.close();
		return null;
	}

	// **************************************************************
	// Wait Actions
	// *********************************************************
	// wait till element is not visible
	public BasePage WaitTillElementIsNotVisible(String XPath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath)));
		return this;
	}

	// Wait till Element is not clickable
	public BasePage WaitTillElementIsNotClickable(String XPath) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPath)));
		return this;
	}
	
	//Fluent wait {Polling wait}
	public void FluentWait()
	{
		// Waiting 30 seconds for an element to be present on the page, checking
//		// for its presence once every 5 seconds.
//		   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//		       .withTimeout(30, TimeUnit.SECONDS)
//		       .pollingEvery(5, TimeUnit.SECONDS)
//		       .ignoring(NoSuchElementException.class);
//
//		   WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//		     public WebElement apply(WebDriver driver) {
//		       return driver.findElement(By.id("foo"));
//		     }
		   //});
		 
	}
	
	

	// *************************************************************************************************
	// Customized Base functions
	// *************************************************************************************************
	// Customised function for send keys using javascriptexecutor
	public BasePage CustomSendKeysWithId(String Id, String Text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + Id + "').value='" + Text + "';");
		return this;

	}
	
	public BasePage zoomInOut(String percentage) {
		
		//percentage 0.5 = 50%, 1 = 100%, 1.5 = 150%,etc.
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.body.style.zoom = '"+percentage+"'");
        return this;
	}

	// *******************************************************************
	// Page Specific Functions
	// *****************************************************************
	/// Detail:Clicks on Submit tab of the page
	// Return:Nothing
	public BasePage Submit() throws InterruptedException {
		ClickUsingXPath("//button[text()='Submit']");	
		return this;
	}
	// *************************************************************************************************
	// Action class functions 
	// *************************************************************************************************

	public BasePage MoveToElementAndClick(String Xpath)
	{
		WebElement Target =driver.findElement(By.xpath(Xpath));
		Actions ac = new Actions(driver);
		ac.moveToElement(Target).click(Target).build().perform();
		return this;
	}
	
	public BasePage MoveToElementByOffsetAndClick(String Xpath)
	{
		WebElement Target =driver.findElement(By.xpath(Xpath));
		Actions ac = new Actions(driver);
	//  ac.moveByOffset(Target.getLocation().getX(), Target.getLocation().getY()).click().build().perform();
		//ac.moveByOffset().click().build().perform();
		ac.moveToElement(Target,256,128).click().build().perform();
		return this;
	}
	public BasePage focuseOnElement(String Xpath)
	{
		//WebElement Target = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
		WebElement Target =driver.findElement(By.xpath(Xpath));
		new Actions(driver).moveToElement(Target).perform();
		return this;
		
	}
	// *************************************************************************************************
	// Extra functions
	// *************************************************************************************************
	
	//Scrolling functions 
	public BasePage ScrollByCoordinates() throws InterruptedException
	{
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1500)");
		return this;
	}
	
	public BasePage ScrollByUpCoordinates() throws InterruptedException
	{
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-1500)");
		return this;
	}
	
	public BasePage ScrollToElements(String xpath) throws InterruptedException
	{
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement ele =  driver.findElement(By.xpath(xpath));
		jse.executeScript("arguments[0].scrollIntoView();",ele);
		return this;
	}
	
	//Specifically wait for page load 
	 public BasePage WaitForPageLoad()
	 {			 
		 wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
		 return this;
	 }
	 
	 
	 
	// Gets the attribute value for the element
	public String GetAttributeValue(String XPath, String AttributeName) {
		return driver.findElement(By.xpath(XPath)).getAttribute(AttributeName);
	}

	// Refresh the page
	public BasePage RefreshPage() {
		driver.navigate().refresh();
		return this;
	}

	// Open a new Tab
	public BasePage OpenNewTab() throws AWTException, InterruptedException {
		Robot robot = new Robot();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
		Thread.sleep(2000);
		return this;
	}

	// Switch to required tab
	public BasePage SwitchToTab(int TabID) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(TabID));
		return this;
	}

	// Open a URL
	public BasePage OpenURL(String URL) {
		driver.navigate().to(URL);
		return this;
	}

	// Click off the field
	public BasePage ClickOfTheField() {
		driver.findElement(By.xpath("//html")).click();
		return this;
	}

	// Upload file from machine
	public BasePage UploadFile(String xpathOfUploadbutton, String fileName) throws AWTException, InterruptedException {
		WebElement uploadPhotoBtn = driver.findElement(By.xpath(xpathOfUploadbutton));
		//uploadPhotoBtn.click();
		Actions act = new Actions(driver);
		act.moveToElement(uploadPhotoBtn).click().perform();
		StringSelection attachment_Path;
		String Absolute_Path;
		ClipboardOwner owner = null;
		Absolute_Path = System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\SamplePDF\\"
				+ fileName;
		attachment_Path = new StringSelection(Absolute_Path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(attachment_Path, owner);
		Robot robot = new Robot();
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_K);
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		return this;
	}

	// Checks if elements with provided xpath is exists or not
	public boolean CheckIfwebElementStillExists(String XPath) {
		List<WebElement> Elements = driver.findElements(By.xpath(XPath));
		return Elements.isEmpty();
	}
	
	//get title of the page 
	public BasePage GetTitleOfPage()
	{
		String Title =driver.getTitle();
		System.out.println(Title);
		return this;
	}
	
	//getListOfElemts
	public List<WebElement> getListOfElements(String xpath)
	{
		return  driver.findElements(By.xpath(xpath));
	}
	
//	*****************File modification*************************
	public void openFileToEdit(String FilePath) throws IOException {
		
		randomNumber = RandomDataGenerator.RandomInt();		
		String CampaignNameRandom = "AutoCamp" + randomNumber;
		String client = "1111";	
		String Campaign = "8776";
		String EmpSizeLink ="https://www.linkedin.com/company/15897338";
		String jobTitleLink = "https://www.linkedin.com/in/hhhtt-Boe-";
		String Salutation="Mr",
				First_Name="F_"+RandomDataGenerator.RandomString(),
				Last_Name="L_"+RandomDataGenerator.RandomString();
		String Job_Title="Information Systems",Job_level="Manager",Job_function="Information Technology",
			Email_Addresss=RandomDataGenerator.RandomString()+"@"+RandomDataGenerator.RandomString()+".com",
			Domain=RandomDataGenerator.RandomString()+".com",
			Company_Name=RandomDataGenerator.RandomString()+"pvt ltd",
			Address="100th road, xyz street",City ="California city",State="California",Zip_Code="789654",Country="United States",Industry="Animation",Employee_size="51 to 200",Revenue="$1M to $5M",leadEmailStatus="Subscribed";
		int Phone_Number=RandomDataGenerator.RandomPhone();
        String sheetName = "", BDE = "Test-00005";
        
        
        

	try   
	{  		
	//declare file name to be create   
	//File file = new File("C:\\Users\\pghodeswar\\OneDrive - Inventive Infotech Inc\\Desktop\\SampleFormatData.xls");	
	File file = new File(FilePath);
	if(file.getName().equals("uploadBulkLeadsSampleFile.xls")) {
		sheetName = "Sheet1";
	}
	else {
		sheetName = "Sample Format";
	}
	
	Thread.sleep(1000);
	FileInputStream inputStream = new FileInputStream(file);
	//creating an instance of HSSFWorkbook class  
	HSSFWorkbook workbook = new HSSFWorkbook(inputStream);  
	//getting Sheet() method and passing the name of the sheet to be created   
	HSSFSheet sheet = workbook.getSheet(sheetName);  
	//int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	Row headRow = sheet.getRow(0);
	for (int k = 1;k<=150;k++) {
		Row row = sheet.createRow(k);
		for (int i = 0; i < headRow.getLastCellNum(); i++) {
			
			    switch(headRow.getCell(i).toString()) {
			    case "Client Code":			    	
			    	row.createCell(i).setCellValue(client);
			    	break;
			    case "Campaign ":
			    	row.createCell(i).setCellValue(Campaign);
			    	break;
			    case "Employee Size Link":
			    	row.createCell(i).setCellValue(EmpSizeLink);
			    	break;
			    case "Job Title Link":
			    	row.createCell(i).setCellValue(jobTitleLink+RandomDataGenerator.RandomInt()+"/");
			    	break;
			    case "Salutation":
			    	row.createCell(i).setCellValue(Salutation);
			    	break;
			    case "First Name":
			    	row.createCell(i).setCellValue("F_"+RandomDataGenerator.RandomString());
			    	break;	
			    case "Last Name":
			    	row.createCell(i).setCellValue("L_"+RandomDataGenerator.RandomString());
			    	break;
			    case "Job Title":
			    	row.createCell(i).setCellValue(Job_Title);
			    	break;
			    case "Job_level":
			    	row.createCell(i).setCellValue(Job_level);
			    	break;
			    case "Job_function":
			    	row.createCell(i).setCellValue(Job_function);
			    	break;
			    case "Email Addresss":
			    	row.createCell(i).setCellValue(RandomDataGenerator.RandomString()+"@"+RandomDataGenerator.RandomString()+".com");
			    	break;
			    case "Email":
			    	row.createCell(i).setCellValue(RandomDataGenerator.RandomString()+"@"+RandomDataGenerator.RandomString()+".com");
			    	break;
			    case "Domain":
			    	row.createCell(i).setCellValue(RandomDataGenerator.RandomString()+".com");
			    	break;
			    case "Phone Number":
			    	row.createCell(i).setCellValue("91"+RandomDataGenerator.RandomPhone());
			    	break;
			    case "Phone":
			    	row.createCell(i).setCellValue("91"+RandomDataGenerator.RandomPhone());
			    	break;
			    case "Company Name":
			    	row.createCell(i).setCellValue(RandomDataGenerator.RandomString()+" pvt ltd");
			    	break;
			    case "Company Type":
			    	row.createCell(i).setCellValue("Private");
			    	break;
			    case "Address":
			    	row.createCell(i).setCellValue(Address);
			    	break;
			    case "Address1":
			    case "Address2":
			    	row.createCell(i).setCellValue(Address);
			    	break;
			    case "City":
			    case "City ":
			    	row.createCell(i).setCellValue(City);
			    	break;
			    case "State":
			    	row.createCell(i).setCellValue(State);
			    	break;
			    case "Zip Code":
			    	row.createCell(i).setCellValue(Zip_Code);
			    	break;
			    case "Country":
			    	row.createCell(i).setCellValue(Country);
			    	break;
			    case "Industry":			    	
			    	row.createCell(i).setCellValue(Industry);
			    	break;
			    case "Employee size":
			    	row.createCell(i).setCellValue(Employee_size);
			    	break;
			    case "Revenue":
			    	row.createCell(i).setCellValue(Revenue);
			    	break;
			    case "Lead Email Status":
			    	row.createCell(i).setCellValue(leadEmailStatus);
			    	break;
			    case "Date":
			    	row.createCell(i).setCellValue(getTodaysDate());
			    	break;
			    case "RA Name(BDE)":
			    	row.createCell(i).setCellValue(BDE);
			    	break;
			    case "QA Comment":
			    	row.createCell(i).setCellValue("Lead QA comment ");
			    	break;	
			    case "QA Agent Name":
			    	row.createCell(i).setCellValue("TEST-00005");
			    	break;
			    case "QA Status":
			    	row.createCell(i).setCellValue("Active");
			    	break;
			    case "White Paper":
			    	row.createCell(i).setCellValue("Google");
			    	break;
			    case "Voice Log Path":
			    	row.createCell(i).setCellValue("https://uat-ilead.bython.co.in/ilead/");
			    	break;
			    case "Disposition":
			    	row.createCell(i).setCellValue("Web Scored");
			    	break;
			    	
			    default :	
			    }                        
         }
		//System.out.println();
	}
	
	FileOutputStream fileOut = new FileOutputStream(file);
	workbook.write(fileOut);  
	//closing the Stream  
	fileOut.close();  
	//closing the workbook  
	workbook.close();  
	//prints the message on the console  
	System.out.println("Excel file has been generated successfully.");  
	}   
	catch (Exception e)   
	{  
	e.printStackTrace();  
	}  
		
	}
//********************************ScreeenShots Section*****************************************************************
	
	public File getElementScreenshot(String xpath, String fileNme) throws IOException, InterruptedException {
		WebElement element = driver.findElement(By.xpath(xpath));
		//String pathToSave = System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\Files\\ScreenShots\\"+getTodaysDate()+"\\"+fileNme+".jpg";
		String pathToSave = System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\Files\\ScreenShots\\"+fileNme+".jpg";
		File source = ((TakesScreenshot)element).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File(pathToSave));
        Thread.sleep(1000);
		return new File(pathToSave);        
	}

	public boolean compareScreenshots(File fileA, File fileB) {
		try {
	        // take buffer data from botm image files //
	        BufferedImage biA = ImageIO.read(fileA);
	        DataBuffer dbA = biA.getData().getDataBuffer();
	        int sizeA = dbA.getSize();                      
	        BufferedImage biB = ImageIO.read(fileB);
	        DataBuffer dbB = biB.getData().getDataBuffer();
	        int sizeB = dbB.getSize();
	        // compare data-buffer objects //
	        if(sizeA == sizeB) {
	            for(int i=0; i<sizeA; i++) { 
	                if(dbA.getElem(i) != dbB.getElem(i)) {
	                    return false;
	                }
	            }
	            return true;
	        }
	        else {
	            return false;
	        }
	    } 
	    catch (Exception e) { 
	        System.out.println("Failed to compare image files ...");
	        return  false;
	    }
	}

	public BasePage getXYPointsOfElement(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		//Used points class to get x and y coordinates of element.
		Point point = element.getLocation();
		int xcord = point.getX();
		System.out.println("Position of the webelement from left side is "+xcord +" pixels");
		int ycord = point.getY();
		System.out.println("Position of the webelement from top side is "+ycord +" pixels");
		return this;
	}
}

// ************************************************************************************************