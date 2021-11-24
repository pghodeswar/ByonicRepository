package Byonic.ByonicProject.Framework.BasePageFolder;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	public WebDriver driver;
	public WebDriverWait wait;
	public static String dummyString = "feshfieuhfe";
	public static ArrayList<String> listOfCampaigns = new ArrayList<String>();

	public Page(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	// Create a method with java generic and return a new page and it will return a
	// new page
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class, WebDriverWait.class).newInstance(this.driver,this.wait);
		} catch (Exception e) {
			// Just return the stack trace if exception occurs
			e.printStackTrace();
			return null;
		}
	}
}
