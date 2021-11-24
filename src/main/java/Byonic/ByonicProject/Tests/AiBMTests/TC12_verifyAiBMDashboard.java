package Byonic.ByonicProject.Tests.AiBMTests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.AiBMPage;
import Byonic.ByonicProject.Pages.LoginPage;
public class TC12_verifyAiBMDashboard extends BaseTest{
	
	List<String> AiBMTilesExpected = new ArrayList<String>(); 
	List<String> AiBMTilesActual = new ArrayList<String>();
	
	File firstFile,secondFile; 
	
	@Test(groups = {"Sanity", "AiBM"})
	public void TC12_toVerifyAiBMDashboard() throws InterruptedException, IOException {
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		page.getInstance(AiBMPage.class).navigateToAiBMTab("//a[contains(text(),'AiBM')]");
		
		String aibmValidationPoint = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@class='total-company-count']/div");
		Assert.assertEquals("Total number of Companies", aibmValidationPoint,"Redirection to AiBM Dashboard failed...!!!");
		
		AiBMTilesExpected.add("By Location");
		AiBMTilesExpected.add("Top 10 Industries");
		AiBMTilesExpected.add("By Company Size");
		AiBMTilesExpected.add("By Job Levels");
		AiBMTilesExpected.add("By Revenue");
		
		page.getInstance(BasePage.class).focuseOnElement("//div[@class='graph-content']/div/div[contains(text(),'By Company Size')]");
		AiBMTilesActual=page.getInstance(AiBMPage.class).getGraphsOnAiBMDashboard();
		Thread.sleep(3000);
		Assert.assertEquals(AiBMTilesExpected, AiBMTilesActual,"AiBM dashboard cards error");
		
		page.getInstance(BasePage.class).ScrollToElements("//div[@class='graph-content']/div/div[contains(text(),'By Location')]");   //.focuseOnElement("//div[@class='graph-content']/div/div[contains(text(),'By Location')]");
		//Compare two tiles
		firstFile = page.getInstance(BasePage.class).getElementScreenshot("//div[@class='graph-country']/canvas", "firstFileOfGraph");		
		
		page.getInstance(BasePage.class).MoveToElementByOffsetAndClick("//div[@class='graph-country']/canvas");//.getXYPointsOfElement("//div[@class='graph-country']/canvas")
		
		secondFile = page.getInstance(BasePage.class).getElementScreenshot("//div[@class='graph-country']/canvas", "secondFileOfGraph");		
		boolean isSame = page.getInstance(BasePage.class).compareScreenshots(firstFile,secondFile);
		
		System.out.println(isSame);
		
	}
}
