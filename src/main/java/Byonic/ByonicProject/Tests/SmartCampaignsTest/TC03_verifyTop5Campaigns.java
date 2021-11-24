package Byonic.ByonicProject.Tests.SmartCampaignsTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.SmartCampDashBoardPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC03_verifyTop5Campaigns extends BaseTest{
	
	List<Integer> goalsScoreExpected  = new ArrayList<Integer>();
	List<Integer> goalsScoreActual = new ArrayList<Integer>();

	List<Integer> needAttentionExpected  = new ArrayList<Integer>();
	List<Integer> needAttentionActual = new ArrayList<Integer>();
	
	@Test(groups = {"Byonic", "Sanity"})
	public void TC03_verifyTop5CampaignsDataAndOrdering() throws InterruptedException {
		
        page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);		
		page.getInstance(BasePage.class).GetTextUsingXPath("//a[contains(text(),'Dashboard')]");
		page.getInstance(SmartCampDashBoardPage.class).clickOnSmartCampaignTab();
		//Get the top 5 rows from table
		goalsScoreActual = page.getInstance(SmartCampDashBoardPage.class).getTop5RowsGoals();		
		for(int s: goalsScoreActual) {
		  goalsScoreExpected.add(s);
		  }
		Collections.sort(goalsScoreExpected,Collections.reverseOrder());		
		Assert.assertEquals(goalsScoreExpected, goalsScoreActual,"Data Ordering By Goal Error");
		
		
		needAttentionActual = page.getInstance(SmartCampDashBoardPage.class).getCriticalRows();			
		  for(int s: needAttentionActual) {
			  needAttentionExpected.add(s);
		  }		 
		Collections.sort(needAttentionExpected);		
		Assert.assertEquals(needAttentionExpected, needAttentionActual,"Data Ordering by Completion Error");
	}
}
