package Byonic.ByonicProject.Tests.POCs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.AiBMPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class ScrollByVisibleElement extends BaseTest {

    WebDriver driver;
    @Test
    public void ByVisibleElement() throws InterruptedException {
         
//        System.setProperty("webdriver.chrome.driver",
//				System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\chromedriver.exe");
//		//driver = new ChromeDriver();
        

        //Launch the application		
        page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		page.getInstance(AiBMPage.class).clickOnSearchOfAiBM();
		page.getInstance(BasePage.class).ScrollByCoordinates();
		
		page.getInstance(BasePage.class).ScrollToElements("//a[@id='toggle']");
		page.getInstance(AiBMPage.class).clickMinOrMax();
    }
}