package Byonic.ByonicProject.Tests.SmartCampaignsTest;



import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.LoginPage;
import Byonic.ByonicProject.Pages.SmartCampDashBoardPage;

public class TC02_verifyStatuses extends BaseTest {
	
	List<String> campaignStatusExpected = new ArrayList<String>(); 
	List<String> LeadsStatusExpected  = new ArrayList<String>();
	List<String> campaignStatusActual = new ArrayList<String>();
	List<String> LeadsStatusActual  = new ArrayList<String>();
	@Test(groups = {"Byonic","Sanity" })
	public void TC02_toVerifyCampaignsAndLeadsByStatuses() throws InterruptedException {
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		
		campaignStatusExpected.add("Live Campaigns");
		campaignStatusExpected.add("Completed Campaigns");
		campaignStatusExpected.add("Created Campaigns");
		campaignStatusExpected.add("Paused Campaigns");		
		LeadsStatusExpected.add("Delivered");
		LeadsStatusExpected.add("Accepted");
		LeadsStatusExpected.add("Rejected");
		LeadsStatusExpected.add("Deficit");
		LeadsStatusExpected.add("Completion");
		page.getInstance(BasePage.class).GetTextUsingXPath("//a[contains(text(),'Dashboard')]");
		page.getInstance(SmartCampDashBoardPage.class).clickOnSmartCampaignTab();
		campaignStatusActual=page.getInstance(SmartCampDashBoardPage.class).getCampignsStatuses();		
		Assert.assertEquals(campaignStatusExpected, campaignStatusActual,"Campaign Statuses Error");
		
		LeadsStatusActual=page.getInstance(SmartCampDashBoardPage.class).getLeadsStatuses();		
		Assert.assertEquals(LeadsStatusExpected, LeadsStatusActual,"Leads Statuses Error");
		
	}
}
