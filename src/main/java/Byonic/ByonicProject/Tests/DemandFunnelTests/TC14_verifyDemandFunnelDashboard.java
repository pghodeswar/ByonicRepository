package Byonic.ByonicProject.Tests.DemandFunnelTests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.DemandFunnelPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC14_verifyDemandFunnelDashboard extends BaseTest{
	List<String> DemFunTilesExpected = new ArrayList<String>(); 
	List<String> DemFunTilesActual = new ArrayList<String>();
	
	@Test(groups = {"sanity", "DemandFunnel"})
	public void TC14_toVerifyDemandFunnelDashboard() throws InterruptedException
	{
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		page.getInstance(DemandFunnelPage.class).navigateToDemFunTab("//a[contains(text(),'Demand Funnel')]");
		
		String DemFunValidationPoint = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@class='total-company-count']/div");
		Assert.assertEquals("Unique Prospects", DemFunValidationPoint,"Redirection to Demand Funnnel Dashboard failed...!!!");
		
		DemFunTilesExpected.add("By Location");		
		DemFunTilesExpected.add("By Job Level");
		DemFunTilesExpected.add("By Industry");
		DemFunTilesExpected.add("By Job Function");
		
		page.getInstance(BasePage.class).focuseOnElement("//div[@class='graph-content']/div/div[contains(text(),'By Industry')]");
		DemFunTilesActual=page.getInstance(DemandFunnelPage.class).getGraphsOnDemFunDashboard();
		Thread.sleep(3000);
		Assert.assertEquals(DemFunTilesExpected, DemFunTilesActual,"Demand Funnel dashboard cards error");
		
		// check data on graphs
	}
}
