package Byonic.ByonicProject.Tests.AiBMTests;

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
import Byonic.ByonicProject.Pages.AiBMPage;
import Byonic.ByonicProject.Pages.LoginPage;

public class TC13_verifySearchPageOfAiBM extends BaseTest {

	List<String> AiBMTilesExpected = new ArrayList<String>(); 
	List<String> AiBMTilesActual = new ArrayList<String>();
	
	List<String> AiBMSearchTableExpected = new ArrayList<String>(); 
	List<String> AiBMSearchTableActual = new ArrayList<String>();

	List<String> AiBMSearchFiltersExpected = new ArrayList<String>(); 
	List<String> AiBMSearchFiltersActual = new ArrayList<String>();
	
	List<String> CompanyInfoExpected = new ArrayList<String>(); 
	List<String> CompanyInfoActual = new ArrayList<String>();
	
	File firstFile,secondFile; 
	
	String LocationFilter="India";

	@Test(groups = {"Sanity", "AiBM"})
	public void TC13_toVerifySearchPageOfAiBM() throws InterruptedException, IOException, AWTException {
		page.getInstance(LoginPage.class).login(ByonicCredentials.TestUserName, ByonicCredentials.TestUserPswd);
		page.getInstance(AiBMPage.class).clickOnSearchOfAiBM();
		//Varification of presence cards
		AiBMTilesExpected.add("Company By Location");
		AiBMTilesExpected.add("Industries");
		AiBMTilesExpected.add("Company By Size");
		AiBMTilesExpected.add("Company By Revenue");
		AiBMTilesExpected.add("By Intent Score");
		AiBMTilesExpected.add("Contacts By Job Level");		
		
		page.getInstance(BasePage.class).ScrollByCoordinates();
		page.getInstance(BasePage.class).focuseOnElement("//div[@class='metrics-content']/div/div[contains(text(),'By Intent Score')]");
		AiBMTilesActual=page.getInstance(AiBMPage.class).getCardsOnAiBMSearchPage();
		Assert.assertEquals(AiBMTilesExpected, AiBMTilesActual,"AiBM search page cards error");
		
		//verify the table headers
		AiBMSearchTableExpected.add("COMPANY");
		AiBMSearchTableExpected.add("TOPIC");
		AiBMSearchTableExpected.add("EMPLOYEE SIZE");
		AiBMSearchTableExpected.add("INDUSTRY");
		AiBMSearchTableExpected.add("PROSPECT COUNT");
		AiBMSearchTableExpected.add("1P SCORE");
		AiBMSearchTableExpected.add("ACCURACY SCORE");
		AiBMSearchTableExpected.add("INTENT SCORE");
		
		
		AiBMSearchTableActual=page.getInstance(AiBMPage.class).getTableHearderOnAiBMSearchPage();
		Assert.assertEquals(AiBMSearchTableExpected, AiBMSearchTableActual,"AiBM search page Table Headers error");
		
		//verify table data without filters
		Assert.assertTrue(page.getInstance(BasePage.class).IsExistsUsingXPath("//table/tbody/tr"));
		
		page.getInstance(BasePage.class).zoomOut(1);
		
		//verify All available flters		
		AiBMSearchFiltersExpected.add("Intent Topic");
		AiBMSearchFiltersExpected.add("Employee Size");
		AiBMSearchFiltersExpected.add("Location");
		AiBMSearchFiltersExpected.add("Industry");
		AiBMSearchFiltersExpected.add("Composite Score");
		AiBMSearchFiltersExpected.add("Revenue");
		AiBMSearchFiltersExpected.add("Install Base");
		AiBMSearchFiltersExpected.add("Inclusion List");
		AiBMSearchFiltersExpected.add("Exclusion List");		
				
		AiBMSearchFiltersActual=page.getInstance(AiBMPage.class).getFiltersOnAiBMSearchPage();
		Assert.assertEquals(AiBMSearchFiltersExpected, AiBMSearchFiltersActual,"AiBM search page Filters error");
		
		//Apply filters
		page.getInstance(AiBMPage.class).clickOnLocFilter().setLocationFilter(LocationFilter).applyFilter();		
		
		//verify number of rows on screen
		String pagination = page.getInstance(BasePage.class).GetTextUsingXPath("//div[@class='mat-paginator-range-label']");
		Assert.assertTrue(pagination.contains("500"));
		
		//verify minimization and maximization
		page.getInstance(BasePage.class).ScrollToElements("//a[@id='toggle']");
		page.getInstance(AiBMPage.class).clickMinOrMax();
	    //verify Individual company
		page.getInstance(BasePage.class).ScrollToElements("//div[@class='table__main']/table");
		page.getInstance(AiBMPage.class).clickOnCompany();
		Thread.sleep(3000);
		
		//Company information 		
		CompanyInfoExpected.add("WEBSITE");
		CompanyInfoExpected.add("EMPLOYEE SIZE");
		CompanyInfoExpected.add("REVENUE");
		CompanyInfoExpected.add("LINKEDIN");
		CompanyInfoExpected.add("PROSPECTS");
		CompanyInfoExpected.add("INDUSTRY");
		CompanyInfoExpected.add("COUNTRY");
		CompanyInfoActual=page.getInstance(AiBMPage.class).getCompanyInformation();
		Assert.assertEquals(CompanyInfoExpected, CompanyInfoActual,"Individual Compay details error");
		
		String IndempSize = page.getInstance(AiBMPage.class).getCompanyIndustryEmpSize();
		
		
		//verify Similar companies
		page.getInstance(AiBMPage.class).clickOnSimilarCompany();
		
		Thread.sleep(2000);
		String empSize = IndempSize.split(",")[0];
		String industry= IndempSize.split(",")[1];
		boolean similarCompCritetia = page.getInstance(AiBMPage.class).verifySimilarCompanies(empSize,industry);
		System.out.print("");
	}
}
