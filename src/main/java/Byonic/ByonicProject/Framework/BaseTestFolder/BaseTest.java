package Byonic.ByonicProject.Framework.BaseTestFolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import Byonic.ByonicProject.Framework.BasePageFolder.Page;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;

public class BaseTest {

	public static WebDriver driver;
	public WebDriverWait wait;
	public Page page;
	public int randomNumber;

	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException {
		
		String downloadFilepath = System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\Files\\"+getTodaysDate().replace("/", "_");
		                                                            		
		Map<String, Object> preferences = new Hashtable<String, Object>();
		preferences.put("profile.default_content_settings.popups", 0);
		preferences.put("download.prompt_for_download", "false");
		preferences.put("download.default_directory", downloadFilepath);
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", preferences);
		options.addArguments("allow-file-access-from-files");
		options.addArguments("use-fake-device-for-media-stream");
		options.addArguments("use-fake-ui-for-media-stream");
		options.addArguments("--enable-notifications");
		options.addArguments("--window-size=1000,500");
		//options.setHeadless(true);
        
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\chromedriver.exe");
		driver = new ChromeDriver(options);
		
		wait = new WebDriverWait(driver, 30);
		driver.get(ByonicCredentials.URL);
		//UnsecureSiteConnection();
		Thread.sleep(2000);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		page = new Page(driver, wait);
	}

	@AfterMethod(alwaysRun = true)
	public void TearDown() {
		driver.quit();
	}

	public void UnsecureSiteConnection() {
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
	}
	
	public String getTodaysDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy");		
		return formatter.format(date);
	}
}
