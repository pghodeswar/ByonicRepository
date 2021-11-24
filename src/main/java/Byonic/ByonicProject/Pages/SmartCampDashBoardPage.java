package Byonic.ByonicProject.Pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;

public class SmartCampDashBoardPage extends BasePage{

	ArrayList<String> campStatus;
	ArrayList<String>  LeadsStatus;
	ArrayList<WebElement> campStatusWeb;
	ArrayList<WebElement> LeadsStatusWeb;

	ArrayList<Integer> TopGoals;
	ArrayList<WebElement> TopGoalsWeb;
	String str;
	Iterator<WebElement> iter;
	private ArrayList<Integer> criticalCamps;
	private ArrayList<WebElement> criticalCampsWeb;

	public SmartCampDashBoardPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);		
	}

	public SmartCampDashBoardPage clickOnSmartCampaignTab() {
		ClickUsingXPath("//header[2]//li/a[contains(text(),'Smart Camp')]");
		return this;
	}

	public ArrayList<String> getCampignsStatuses() {
		campStatus = new ArrayList<String>() ;		
		campStatusWeb = new ArrayList<WebElement>();
		campStatusWeb = (ArrayList<WebElement>) getListOfElements("//div[1]/div[@class='campaign_detail']/div/div/div/div[1]");
		iter = campStatusWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			campStatus.add(str);
		}	
		return campStatus;
	}

	public ArrayList<String> getLeadsStatuses() {

		LeadsStatus = new ArrayList<String>();
		LeadsStatusWeb = new ArrayList<WebElement>();
		LeadsStatusWeb = (ArrayList<WebElement>) getListOfElements("//div[2]/div[@class='campaign_detail']/div/div/div/div[1]");
		iter = LeadsStatusWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			LeadsStatus.add(str);
		}	
		return LeadsStatus;
	}

	public ArrayList<Integer> getTop5RowsGoals() {		
		TopGoals = new ArrayList<Integer>();
		TopGoalsWeb = new ArrayList<WebElement>();
		TopGoalsWeb = (ArrayList<WebElement>) getListOfElements("//div[contains(text(),'Top 5 Campaigns')]/following-sibling::div/table/tbody/tr/td[3]");
		iter = TopGoalsWeb.iterator();
		if(TopGoalsWeb.isEmpty()) {	

			throw new NoSuchElementException("Error : No Data Found. Top Goals List returned empty..!!!");
		}
		while(iter.hasNext()) {
			str = iter.next().getText();
			TopGoals.add(Integer.parseInt(str));
		}	
		return TopGoals;
	}

	public List<Integer> getCriticalRows() {	
		criticalCamps = new ArrayList<Integer>();
		criticalCampsWeb = new ArrayList<WebElement>();
		criticalCampsWeb = (ArrayList<WebElement>) getListOfElements("//div[contains(text(),'Campaign Requiring Attention')]/following-sibling::div/table/tbody/tr/td[5]");
		iter = criticalCampsWeb.iterator();
		if(criticalCampsWeb.isEmpty()) {
			throw new NoSuchElementException("Error : No Data Found. Critical Campaigns List returned empty..!!!");
		}
		while(iter.hasNext()) {
			str = iter.next().getText();
			criticalCamps.add(Integer.parseInt(str));
		}	
		return criticalCamps;
	}

	public SmartCampDashBoardPage enterClientCode(String clientCode) {

		SetTextWithFieldXPath("//input[@placeholder='Client Code']", clientCode);
		return this;	
	}

	public SmartCampDashBoardPage selectQuarter(String quarter) {
		SelectWithValueUsingXPath("//select[@name='filters']", quarter);
		return this;
	}

	public void clickApply() {
		ClickUsingXPath("//button[@id='submit_btn']");
	}

	public void clickReset() {
		ClickUsingXPath("//button[@id='reset_btn']");
	}


}

