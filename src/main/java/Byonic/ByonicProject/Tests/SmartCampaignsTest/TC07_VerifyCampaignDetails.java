package Byonic.ByonicProject.Tests.SmartCampaignsTest;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.CampaignDetailsPage;
import Byonic.ByonicProject.Pages.CampaignsListPage;
import Byonic.ByonicProject.Pages.SmartCampDashBoardPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC07_VerifyCampaignDetails extends BaseTest{

	List<String> campaignDetailsExpected = new ArrayList<String>(); 
	List<String> campaignDetailsActual = new ArrayList<String>();
	
	List<String> notesDetailsExpected = new ArrayList<String>(); 
	List<String> notesDetailsActual = new ArrayList<String>();
	
	String campNameToFilter = "", campNameToAfterFilter = "", campTypeExp= "Live",campTypeAct = "";
	WebElement LeadDeliveryFiles; 
	boolean isPresent;
	@Test(groups = {"Byonic","sanity"})
	public void TC07_toVerifyAllCampaignDetailPage() throws InterruptedException{
		
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		page.getInstance(BasePage.class).GetTextUsingXPath("//a[contains(text(),'Dashboard')]");
		page.getInstance(SmartCampDashBoardPage.class).clickOnSmartCampaignTab();
		page.getInstance(CampaignsListPage.class).clickOnCampaignsTab();		
		campNameToFilter = page.getInstance(BasePage.class).GetTextUsingXPath("//div/table/tbody/tr[1]/td");
		page.getInstance(CampaignsListPage.class).searchByCampaignName(campNameToFilter).applyFilter();			
		page.getInstance(CampaignsListPage.class).clickOnCampaign("//div/table/tbody/tr[1]/td[1]");		
		
		campaignDetailsExpected.add("Campaign Overview");
		campaignDetailsExpected.add("Campaign Requirements");
		campaignDetailsExpected.add("Advanced Requirements");
		campaignDetailsExpected.add("Campaign Documents");
		campaignDetailsExpected.add("Campaign Statistics");
		campaignDetailsExpected.add("Lead Delivery Files");
		campaignDetailsExpected.add("Lead Rejected Files");
		campaignDetailsExpected.add("Campaign Custom Questions");
		campaignDetailsExpected.add("Notes");
				
		campaignDetailsActual = page.getInstance(CampaignDetailsPage.class).getCampignsDetails();		
		
		
		//To check if user can click on Edit and edit the details of delivery file(Bython)/reject file(agency/client).
		try {
			
			Assert.assertEquals(campaignDetailsExpected, campaignDetailsActual,"Campaign Details Error");			
			//To check if user can click on campaign documents present and view links/download documents.
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='asset_list']/table/tr[2]/td[4]")).isDisplayed());
			LeadDeliveryFiles = driver.findElement(By.xpath("//div[@class='campaign_detail']/mat-card/mat-card-content/div[@class='lead_table_wrapper']//table/tbody/tr[1]"));
			LeadDeliveryFiles.findElement(By.xpath("//td[7]/button")).click();
			
			page.getInstance(BasePage.class).SetTextWithFieldXPath("//div[@id='input']/input[@id='filename']", " By Automation");
			page.getInstance(BasePage.class).SetTextWithFieldXPath("//div[@id='input']/textarea[@id='filedescription']", " By Automation");
			//page.getInstance(BasePage.class).ClickUsingXPath("//button[@type='submit' and contains(text(),'Update')]");
			page.getInstance(BasePage.class).ClickUsingXPath("//img[@type='button']");
			
			LeadDeliveryFiles.findElement(By.xpath("//td[7]/button[2]")).click();
			page.getInstance(BasePage.class).ClickUsingXPath("//button[contains(text(),'No')]");			
			
			page.getInstance(CampaignDetailsPage.class).setNotes("Automation Suite Note");			
			
			notesDetailsExpected.add("#");
			notesDetailsExpected.add("Description");
			notesDetailsExpected.add("Submitted by");
			notesDetailsExpected.add("Submitted on");						
					
			notesDetailsActual = page.getInstance(CampaignDetailsPage.class).getNotesDetails();		
			Assert.assertEquals(notesDetailsExpected, notesDetailsActual,"Campaign Notes Error");
			
			String strExpected = page.getInstance(CampaignDetailsPage.class).getLatestNoteFromTable();
			String strActual = page.getInstance(CampaignDetailsPage.class).getLatestNoteName();
			Assert.assertEquals(strExpected,strActual );
			
		}catch (NoSuchElementException e) {
	        System.out.println("Error : "+e.getStackTrace());
	    }
		
	}
}
