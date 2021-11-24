package Byonic.ByonicProject.Tests.DemandFunnelTests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;
import Byonic.ByonicProject.Framework.Credentials.ByonicCredentials;
import Byonic.ByonicProject.Pages.DemandFunnelPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC15_verifySearchPageOfDemandFunnel extends BaseTest {

	List<String> DemandFunnelTilesExpected = new ArrayList<String>(); 
	List<String> DemandFunnelTilesActual = new ArrayList<String>();
	
	List<String> DemandFunnelSearchTableExpected = new ArrayList<String>(); 
	List<String> DemandFunnelSearchTableActual = new ArrayList<String>();

	List<String> DemandFunnelSearchFiltersExpected = new ArrayList<String>(); 
	List<String> DemandFunnelSearchFiltersActual = new ArrayList<String>();
	
	List<String> ProfileInfoExpected = new ArrayList<String>(); 
	List<String> ProfileInfoActual = new ArrayList<String>();
	
	File firstFile,secondFile; 
	
	String LocationFilter="India";

	@Test(groups = {"Sanity", "Demand Funnel"})
	public void TC13_toVerifySearchPageOfDemandFunnel() throws InterruptedException, IOException, AWTException {
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		page.getInstance(DemandFunnelPage.class).navigateToDemFunTab("//a[contains(text(),'Demand Funnel')]");
		page.getInstance(DemandFunnelPage.class).clickOnSearchOfDemandFunnel();
		
		//Varification of presence cards
		DemandFunnelTilesExpected.add("Contacts By Location");
		DemandFunnelTilesExpected.add("Contacts By Job Level");
		DemandFunnelTilesExpected.add("Contacts By Industry");
		DemandFunnelTilesExpected.add("Contacts By Job Function");
		DemandFunnelTilesExpected.add("By Contact Info");
		DemandFunnelTilesExpected.add("Contacts By Intent Score");		
		
		page.getInstance(BasePage.class).ScrollByCoordinates();
		page.getInstance(BasePage.class).focuseOnElement("//div[@class='metrics-content']/div/div[contains(text(),'Contacts By Intent Score')]");
		DemandFunnelTilesActual=page.getInstance(DemandFunnelPage.class).getCardsOnDemandFunnelSearchPage();
		Assert.assertEquals(DemandFunnelTilesExpected, DemandFunnelTilesActual,"Demand Funnel search page cards error");
		
		//verify the table headers
		DemandFunnelSearchTableExpected.add("FIRST NAME");
		DemandFunnelSearchTableExpected.add("LAST NAME");
		DemandFunnelSearchTableExpected.add("COMPANY");
		DemandFunnelSearchTableExpected.add("LOCATION");
		DemandFunnelSearchTableExpected.add("JOB TITLE");
		DemandFunnelSearchTableExpected.add("JOB LEVEL");
		DemandFunnelSearchTableExpected.add("JOB FUNCTION");
		DemandFunnelSearchTableExpected.add("1P SCORE");
		DemandFunnelSearchTableExpected.add("ACCURACY SCORE");
		DemandFunnelSearchTableExpected.add("INTENT SCORE");
		
		
		DemandFunnelSearchTableActual=page.getInstance(DemandFunnelPage.class).getTableHearderOnDemandFunnelSearchPage();
		Assert.assertEquals(DemandFunnelSearchTableExpected, DemandFunnelSearchTableActual,"Demand Funnel search page Table Headers error");
		
		//verify table data without filters
		Assert.assertTrue(page.getInstance(BasePage.class).IsExistsUsingXPath("//table/tbody/tr"));
		
		page.getInstance(BasePage.class).zoomOut(1);
		
		//verify All available flters		
		
		DemandFunnelSearchFiltersExpected.add("Job Level");
		DemandFunnelSearchFiltersExpected.add("Job Function");		
		DemandFunnelSearchFiltersExpected.add("Location");
		DemandFunnelSearchFiltersExpected.add("Intent Topic");		
		DemandFunnelSearchFiltersExpected.add("Intent Score");
		DemandFunnelSearchFiltersExpected.add("Industry");		
		DemandFunnelSearchFiltersExpected.add("Inclusion");
		DemandFunnelSearchFiltersExpected.add("Exclusion");		
				
		DemandFunnelSearchFiltersActual=page.getInstance(DemandFunnelPage.class).getFiltersOnDemandFunnelSearchPage();
		Assert.assertEquals(DemandFunnelSearchFiltersExpected, DemandFunnelSearchFiltersActual,"Demand Funnel search page Filters error");
		
		//Apply filters
		page.getInstance(DemandFunnelPage.class).clickOnLocFilter().setLocationFilter(LocationFilter).applyFilter();		
		
		//verify number of rows on screen
		String pagination = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@class='mat-paginator-range-label']");
		Assert.assertTrue(pagination.contains("500"));
		
		//verify minimization and maximization
		page.getInstance(BasePage.class).ScrollToElements("//a[@id='toggle']");
		page.getInstance(DemandFunnelPage.class).clickMinOrMax();
	    //verify Individual Profile
		page.getInstance(BasePage.class).ScrollToElements("//div[@class='table-container']/table");
		page.getInstance(DemandFunnelPage.class).clickOnRecord();
		Thread.sleep(3000);
		
		//Profile information 		
		ProfileInfoExpected.add("Full Name");
		ProfileInfoExpected.add("Email");
		ProfileInfoExpected.add("TelePhone");
		ProfileInfoExpected.add("Location");
		ProfileInfoExpected.add("job Level");
		ProfileInfoExpected.add("Job Title");
		ProfileInfoExpected.add("Job Function");
		ProfileInfoExpected.add("State");
		ProfileInfoExpected.add("Country");
		ProfileInfoExpected.add("Linkedin");
		ProfileInfoExpected.add("Company Name");
		ProfileInfoExpected.add("Domain Name");
		ProfileInfoExpected.add("Industry Name");
		ProfileInfoExpected.add("Company Type");
		ProfileInfoExpected.add("Company Website");
		ProfileInfoExpected.add("Company LinkedIn");
		
		ProfileInfoActual=page.getInstance(DemandFunnelPage.class).getProfileInformation();
		Assert.assertEquals(ProfileInfoExpected, ProfileInfoActual,"Individual Profile details error");
		
		String IndempSize = page.getInstance(DemandFunnelPage.class).getNameCompanyOfProfile();
		
		
		//verify Similar companies
		page.getInstance(DemandFunnelPage.class).clickOnSimilarProfile();
		
		Thread.sleep(2000);
		String empSize = IndempSize.split(",")[0];
		String industry= IndempSize.split(",")[1];
		boolean similarCompCritetia = page.getInstance(DemandFunnelPage.class).verifySimilarCompanies(empSize,industry);
		System.out.print("");
	}
}
