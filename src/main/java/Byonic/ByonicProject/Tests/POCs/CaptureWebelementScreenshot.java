package Byonic.ByonicProject.Tests.POCs;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
 
public class CaptureWebelementScreenshot {
 
     
     
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", "E:\\Selenium-Softwares\\geckodriver.exe");
        driver=new ChromeDriver();
        driver.get("http://demo.automationtesting.in/Register.html");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.id("imagetrgt"));
         
      // File source = ((TakesScreenshot)element).getScreenshotAs(OutputType.FILE);
      // FileHandler.copy(source, new File(System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\"+"element.jpg"));
       File getFile = getElementScreenshot(element,System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\"+"element.jpg");
        Thread.sleep(3000);
        driver.quit();
 
    }
 
    public static File getElementScreenshot(WebElement element, String pathToSave) throws IOException {
		File source = ((TakesScreenshot)element).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File(pathToSave));//System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\"+"element.jpg")
		return new File(pathToSave);
        
	}
}