package Byonic.ByonicProject.Tests.LinkTests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.CampaignsListPage;
import Byonic.ByonicProject.Pages.LoginPage;



public class TC_LinksCheckup extends BaseTest{

	ArrayList<WebElement> ListOfLinksOnPage;
	int pass = 1;

	//This testcase will check and verify all the links and their respective appropreate redirections.
	@Test
	public void TC_toVerifyLinksCheckup() throws InterruptedException
	{
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		Thread.sleep(2000);		
		navigateToTabs("//a[contains(text(),'Demand Funnel') or contains(text(),'AiBM')]");
		navigateToTabs("//a[contains(text(),'Smart Camp')]");
	}

	public void navigateToTabs(String path) throws InterruptedException {		
		ListOfLinksOnPage = null;
		ListOfLinksOnPage = (ArrayList<WebElement>) page.getInstance(BasePage.class).getListOfElements(path);		
		for (WebElement we : ListOfLinksOnPage) 
		{
			System.out.println("Element number : "+pass+" ");
			
			try {
				if (we.isDisplayed()) {
					System.out.println(we.getText()+" : is displayed "+pass);
					switch(we.getText()) {
					case "AiBM" : 
						we.click();//page.getInstance(BasePage.class).clickClickUsingXPath("//a[contains(text(),'AiBM')]");
						String aibmValidationPoint = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@class='total-company-count']/div");
						Assert.assertEquals("Total number of Companies", aibmValidationPoint,"Redirection to AiBM failed...!!!");

						page.getInstance(BasePage.class).ClickUsingXPath("//a[@href = '/AiBM/search']");
						String aibmSearcValidationPoint = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@class='filter-display']/div");
						Assert.assertEquals("Metrics", aibmSearcValidationPoint,"Redirection to AiBM Search failed...!!!");
						break;

					case "Demand Funnel":
						//Demand Funnel page				
						we.click();//page.getInstance(BasePage.class).ClickUsingXPath("//a[contains(text(),'Demand Funnel')]");
						String demandFunnelValidationPoint = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@class='total-company-count']/div");
						Assert.assertEquals("Unique Prospects", demandFunnelValidationPoint,"Redirection to Demand Funnel failed...!!!");

						//Demand funnel search page
						page.getInstance(BasePage.class).ClickUsingXPath("//a[@href = '/Demand-funnel/search']");
						String demandSearcValidationPoint = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@class='filter-display']/div");
						Assert.assertEquals("Metrics", demandSearcValidationPoint,"Redirection to Demand Funnel Search failed...!!!");
						Thread.sleep(2000);
						break;

					case "Smart Camp(ai)2gn":
						//Smart campaign
						we.click();//page.getInstance(BasePage.class).ClickUsingXPath("//a[contains(text(),'Demand Funnel')]");
						String smartCampValidationPoint = page.getInstance(BasePage.class).GetTextUsingXPath("//div[contains(text(),'Top 5 Campaigns')]");
						Assert.assertEquals("Top 5 Campaigns", smartCampValidationPoint,"Redirection to Smart Campaign failed...!!!");

						//Campaign List
						page.getInstance(BasePage.class).ClickUsingXPath("//ul/li[contains(text(),'Campaigns')]");
						String campaignsValidationPoint = page.getInstance(BasePage.class).GetTextUsingXPath("//div[contains(text(),'Campaign Name')]");
						Assert.assertEquals("Campaign Name", campaignsValidationPoint,"Redirection to Campaigns List failed...!!!");						

						page.getInstance(CampaignsListPage.class).clickOnCampaign("//div/table/tbody/tr[1]/td[1]");		
						String campTypeAct = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@id='status']");		
						Assert.assertEquals("Live",campTypeAct,"Listed campaign is not live");
						break;

					
					}

				}
			}catch(StaleElementReferenceException e) {
				pass++;
				System.out.println("Exception");
				continue;
			}
			pass++;
		}

	}

}
