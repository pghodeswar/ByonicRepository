package Byonic.ByonicProject.Pages;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;

public class CampaignsListPage extends BasePage{

	public CampaignsListPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}
	private ArrayList<String> campHeaders;
	private Iterator<WebElement> iter;
	private String str;

	
	public CampaignsListPage clickOnCampaignsTab() {
		ClickUsingXPath("//li/img[@alt='Campaign']");
		return this;
	}
	
	public CampaignsListPage clickOnCreateCampaigns() {
		ClickUsingXPath("//div[@class='create_campaign_btn show_campaign']/button");
		return this;
	}
	
	public ArrayList<String> getCampignsStatuses() {
		campHeaders = new ArrayList<String>() ;		
		ArrayList<WebElement> campHeadersWeb = new ArrayList<WebElement>();
		campHeadersWeb = (ArrayList<WebElement>) getListOfElements("//div/table/thead/tr[1]/th");
		iter = campHeadersWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			campHeaders.add(str);
		}	
		return campHeaders;
	}
	
	
	
	public CampaignsListPage clickOnCampaign(String xpath) {
		ClickUsingXPath(xpath);
		return this;
	}
	
	public CampaignsListPage searchByCampaignName(String CampName) {
		SetTextWithFieldXPath("//input[@placeholder='Campaign Name']", CampName);
		return this;
	}
	
	public CampaignsListPage applyFilter() {
		ClickUsingXPath("//button[@type='submit']");
		return this;
	}
	public CampaignsListPage downLoadCampFile() {
		ClickUsingXPath("//button[@class='btn_download']");
		return this;
	}
}
