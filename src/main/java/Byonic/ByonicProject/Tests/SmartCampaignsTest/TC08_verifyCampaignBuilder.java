package Byonic.ByonicProject.Tests.SmartCampaignsTest;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.CampaignBuilderPage;
import Byonic.ByonicProject.Pages.CampaignsListPage;
import Byonic.ByonicProject.Pages.SmartCampDashBoardPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC08_verifyCampaignBuilder extends BaseTest{

	String marketingMethod = "Telemarketing", deliveryMethod = "Email",frequency = "Weekly";
	String region = "APAC",country = "India ",state = "Maharashtra ", methodOfDelivery = "Email";
	String assetFile = "ABC.pdf";
	String[] title = {"IT, IT Ops, Information Technology","Finance","sales","IT"};
	String[] jobFunct = {"Information Technology","Finance","Sales","Human Resources"};
	String[] jobLevel = {"Owner","Founder","Director","Manager"};
	String[] industry = {"Accounting","Animation","Automotive","Banking"};
	String[] empSize = {"1 to 10","11 to 50","51 to 200","201 to 500"};
	String[] revenue = {"$0 to $1M","$1M to $5M","$5M to $10M","$10M to $25M"};
	String[] installBases = {"Test"};
	String[] intentTopics = {"Test","Testing and Analysis"};
	String[] days = {"Monday","Thursday"};
	
	
	@Test(groups = {"Sanity","Byonic"})
	public void TC08_verifyCampaignBuilderFunctionality() throws InterruptedException, AWTException {
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		page.getInstance(BasePage.class).GetTextUsingXPath("//a[contains(text(),'Dashboard')]");
		page.getInstance(SmartCampDashBoardPage.class).clickOnSmartCampaignTab();
		page.getInstance(CampaignsListPage.class).clickOnCampaignsTab().clickOnCreateCampaigns();
		
		page.getInstance(CampaignBuilderPage.class).setCampName("AutomationCampaign").setCostPerLead("10").setStartDate().setEndDate()
		.setTargetLead("100").selectMarketingMethod(marketingMethod).selectGeography(region,country,state);
		page.getInstance(CampaignBuilderPage.class).selectMultiJobTitle(title).selectMultiJobFunction(jobFunct).selectMultiJobLevel(jobLevel);
		page.getInstance(CampaignBuilderPage.class).selectMultiIndustry(industry).selectMultiEmpSize(empSize).selectMultiRevenue(revenue)
		.setAdditioManalInfo().saveAndNext().selectInstallBase(installBases).selectIntentTopic(intentTopics).setLeadsPerDelivery();
		page.getInstance(CampaignBuilderPage.class).selectDeliveryMethod(deliveryMethod).selectFrequency(frequency).selectDOD(days);
		page.getInstance(CampaignBuilderPage.class).assetLinkRadio().addLinks();//.removeLinks();
		page.getInstance(CampaignBuilderPage.class).assetRadio();
		page.getInstance(BasePage.class).UploadFile("//input[@id='file']", assetFile);
		String assetFileActual = page.getInstance(BasePage.class).GetTextUsingXPath("//table/tbody/tr/td[text()='1']/following-sibling::td");
		Assert.assertEquals(assetFile, assetFileActual);
		
		//page.getInstance(CampaignBuilderPage.class).deleteAssetFile();
		page.getInstance(CampaignBuilderPage.class).addCustomQuestion();
		
		System.out.println("From 2 String");
		
	}
}
