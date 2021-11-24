package Byonic.ByonicProject.Tests.LoginTests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC01_verifyLogin extends BaseTest{

	@Test(groups = {"Byonic","Sanity" })
	void TC01_verifyLoginFunction() throws InterruptedException{
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);		
		String expected = "Dashboard";
		String actual = page.getInstance(BasePage.class).GetTextUsingXPath("//a[contains(text(),'Dashboard')]");
		assertEquals(actual, expected);
	}
}
