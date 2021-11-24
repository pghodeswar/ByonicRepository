package Byonic.ByonicProject.Tests.SmartCampaignsTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.SmartCampDashBoardPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC04_verifyFilters extends BaseTest{

private String clientCode = "1082";
@Test(groups= {"sanity","Byonic"})
public void TC04_toVerifyFiltersAndReset() throws InterruptedException {
	page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);		
	page.getInstance(BasePage.class).GetTextUsingXPath("//a[contains(text(),'Dashboard')]");
	page.getInstance(SmartCampDashBoardPage.class).clickOnSmartCampaignTab();			
	List<Integer> beforeFilter = getDataToCompare();
	
	page.getInstance(SmartCampDashBoardPage.class).enterClientCode(clientCode).selectQuarter("Current Month").clickApply();
	List<Integer> withFilter = getDataToCompare();
	Assert.assertNotEquals(beforeFilter, withFilter,"Data matches before and after filter.");
	
	page.getInstance(SmartCampDashBoardPage.class).clickReset();
	List<Integer> afterResetFilter = getDataToCompare();	
	Assert.assertEquals(beforeFilter, afterResetFilter,"Data Ordering wrong before and after filter.");
}

 public List<Integer> getDataToCompare() {
	 List<Integer> goalsScoreExpected  = new ArrayList<Integer>();
	 List<Integer> goalsScoreActual = new ArrayList<Integer>();
	 List<Integer> needAttentionExpected  = new ArrayList<Integer>();
	 List<Integer> needAttentionActual = new ArrayList<Integer>();
	try { 
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
		return needAttentionExpected;
	}catch(NoSuchElementException e) {
		System.out.println(e.getMessage());
		return null;
	}
 }
}
