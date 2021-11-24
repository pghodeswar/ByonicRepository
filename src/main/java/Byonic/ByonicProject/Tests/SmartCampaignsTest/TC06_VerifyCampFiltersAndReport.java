package Byonic.ByonicProject.Tests.SmartCampaignsTest;

import static org.testng.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.CampaignsListPage;
import Byonic.ByonicProject.Pages.SmartCampDashBoardPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC06_VerifyCampFiltersAndReport extends BaseTest{

	String campNameToFilter = "", campNameToAfterFilter = "", campTypeExp= "Live",campTypeAct = "";
	
	@Test(groups= {"Byonic","sanity"})
	public void TC06_toVerifyCampaignFiltersAndRepotrsDownload() throws InterruptedException {
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		page.getInstance(BasePage.class).GetTextUsingXPath("//a[contains(text(),'Dashboard')]");
		page.getInstance(SmartCampDashBoardPage.class).clickOnSmartCampaignTab();
		page.getInstance(CampaignsListPage.class).clickOnCampaignsTab();		
		campNameToFilter = page.getInstance(BasePage.class).GetTextUsingXPath("//div/table/tbody/tr[5]/td");
		page.getInstance(CampaignsListPage.class).searchByCampaignName(campNameToFilter).applyFilter();
		campNameToAfterFilter = page.getInstance(BasePage.class).GetTextUsingXPath("//div/table/tbody/tr[1]/td");		
		Assert.assertEquals(campNameToFilter,campNameToAfterFilter,"Filters are not applying correctly");
		
		page.getInstance(CampaignsListPage.class).clickOnCampaign("//div/table/tbody/tr[1]/td[1]");		
		campTypeAct = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@id='status']");		
		Assert.assertEquals(campTypeExp,campTypeAct,"Listed campaign is not live");
		
						
		
		page.getInstance(CampaignsListPage.class).clickOnCampaignsTab().downLoadCampFile();
		Thread.sleep(2000);
		Path path = Paths.get(System.getProperty("user.dir")+"\\src\\main\\java\\Byonic\\ByonicProject\\Framework\\Latest_Driver\\Files\\"+getTodaysDate().replace("/", "_")+"\\Campaign_Report.csv");
		assertTrue(Files.exists(path));
		
	}
}
