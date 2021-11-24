package Byonic.ByonicProject.Tests.SmartCampaignsTest;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.CampaignsListPage;
import Byonic.ByonicProject.Pages.SmartCampDashBoardPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC05_verifyCampaignPage extends BaseTest 
{

	
	List<String> campaignHeadersExpected = new ArrayList<String>(); 
	List<String> campaignHeadersActual = new ArrayList<String>();
	@Test(groups = {"Byonic","Sanity" })
	public void TC02_toVerifyCampaignsAndLeadsByStatuses() throws InterruptedException {
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		
		campaignHeadersExpected.add("Campaign Name");
		campaignHeadersExpected.add("Target");
		campaignHeadersExpected.add("Leads Generated");
		campaignHeadersExpected.add("Leads Delivered");
		campaignHeadersExpected.add("Leads Accepted");
		campaignHeadersExpected.add("Leads Under QA Check");
		campaignHeadersExpected.add("Leads Returned");
		
		page.getInstance(BasePage.class).GetTextUsingXPath("//a[contains(text(),'Dashboard')]");
		page.getInstance(SmartCampDashBoardPage.class).clickOnSmartCampaignTab();
		campaignHeadersActual=page.getInstance(CampaignsListPage.class).clickOnCampaignsTab().getCampignsStatuses();		
		Assert.assertEquals(campaignHeadersExpected, campaignHeadersActual,"Campaign Headers Error");
		
		
		
	}
}
