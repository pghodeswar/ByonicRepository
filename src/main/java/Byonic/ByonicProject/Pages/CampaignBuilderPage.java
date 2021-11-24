package Byonic.ByonicProject.Pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;

public class CampaignBuilderPage extends BasePage{

	public CampaignBuilderPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	public CampaignBuilderPage setCampName(String name) {
		SetTextWithFieldXPath("//input[@formcontrolname='campName']", name);
		return this;
	}
	
	public CampaignBuilderPage setCostPerLead(String CPL) {
		SetTextWithFieldXPath("//input[@formcontrolname='campTargetCpl']", CPL);
		return this;
	}
	
	public CampaignBuilderPage setStartDate() {
		SetTextWithFieldXPath("//input[@formcontrolname='campStartDate']", getTodaysDate());
		return this;
	}
	
	public CampaignBuilderPage setEndDate() {
		SetTextWithFieldXPath("//input[@formcontrolname='campEndDate']", getFutureDateByDays(10));
		return this;
	}
	
	public CampaignBuilderPage setTargetLead(String targetLead) {
		SetTextWithFieldXPath("//input[@formcontrolname='campTargetLead']", targetLead);
		return this;
	}
	
	public CampaignBuilderPage selectMarketingMethod(String method) throws InterruptedException
	{
		Thread.sleep(1000);
		ClickOnInputArea("//mat-select[@id='mat-select-2']");
		SetTextWithFieldXPath("//input[@id='search-input_market']", method);
		ClickUsingXPath("//mat-option/span[contains(text(),'"+method+"')]");
		ClickOfTheField();
		return this;
	}
	
	
	public CampaignBuilderPage selectGeography(String region, String country, String state) throws InterruptedException {
		Thread.sleep(1000);
		ClickOnInputArea("//mat-select[@id='mat-select-4']");
		SetTextWithFieldXPath("//input[@id='search-input_zone']", region);
		ClickUsingXPath("//mat-option/span[contains(text(),'"+region+"')]");
		ClickOfTheField();
		
		Thread.sleep(1000);
		ClickOnInputArea("//mat-select[@id='mat-select-6']");
		SetTextWithFieldXPath("//input[@id='search-input_country']", country);
		ClickUsingXPath("//mat-option/span[contains(text(),'"+country+"')]");
		ClickOfTheField();
		
		Thread.sleep(1000);
		ClickOnInputArea("//mat-select[@id='mat-select-8']");
		SetTextWithFieldXPath("//input[@id='search-input_state']", state);
		ClickUsingXPath("//mat-option/span[contains(text(),'"+state+"')]");
		ClickOfTheField();
		return this;
	}
	
	public CampaignBuilderPage selectMultiJobTitle(String[] title) throws InterruptedException {
		Thread.sleep(500);
		ClickOnInputArea("//mat-select[@id='mat-select-10']");
		for(String jobTitle:title) {
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input']", jobTitle);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+jobTitle+"')]");
			ClearTextAreaUsingXpath("//input[@id='search-input']");
		}
		ClickOfTheField();
		return this;
	}

	public CampaignBuilderPage selectMultiJobFunction(String[] functions) throws InterruptedException {
		ClickOnInputArea("//mat-select[@id='mat-select-12']");
		for(String jobfunctions:functions) {
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input_function']", jobfunctions);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+jobfunctions+"')]");
			ClearTextAreaUsingXpath("//input[@id='search-input_function']");
		}
		ClickOfTheField();
		return this;
	}

	public CampaignBuilderPage selectMultiJobLevel(String[] jobLevel1) throws InterruptedException {
		ClickOnInputArea("//mat-select[@id='mat-select-14']");
		for(String jobLevel:jobLevel1) {
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input_level']", jobLevel);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+jobLevel+"')]");
			ClearTextAreaUsingXpath("//input[@id='search-input_level']");
		}
		ClickOfTheField();
		return this;
	}
	
    public CampaignBuilderPage selectMultiIndustry(String[] industry) throws InterruptedException {
    	ClickOnInputArea("//mat-select[@id='mat-select-16']");
		for(String industryone:industry) {
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input_industry']", industryone);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+industryone+"')]");
			ClearTextAreaUsingXpath("//input[@id='search-input_industry']");
		}
		ClickOfTheField();
		return this;
	 }

    public CampaignBuilderPage selectMultiEmpSize(String[] EmpSize) throws InterruptedException {
    	ClickOnInputArea("//mat-select[@id='mat-select-18']");
		for(String empsizeOne:EmpSize) {
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input_employee']", empsizeOne);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+empsizeOne+"')]");
			ClearTextAreaUsingXpath("//input[@id='search-input_employee']");
		}
		ClickOfTheField();
		return this;
    }

    public CampaignBuilderPage selectMultiRevenue(String[] revenue) throws InterruptedException {
    	ClickOnInputArea("//mat-select[@id='mat-select-20']");
		for(String revenueOne:revenue) {
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input_revenue']", revenueOne);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+revenueOne+"')]");
			ClearTextAreaUsingXpath("//input[@id='search-input_revenue']");
		}
		ClickOfTheField();
		return this;
    }

	public CampaignBuilderPage setAdditioManalInfo() {
		
		SetTextWithFieldXPath("//textarea[@placeholder='Enter the Additional Information']",
				"This is typed by Automation. Please ignore these comments!!!");
		return this;
	}
	
    public CampaignBuilderPage saveAndNext() {		
		ClickUsingXPath("//button[@type='submit']/span");
		return this;
	}
	
	//******************3. Advanced Criteria**************************
	
	public CampaignBuilderPage selectInstallBase(String[] installBases) throws InterruptedException {
    	ClickOnInputArea("//mat-select[@id='mat-select-22']");
		for(String installBase:installBases) {
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input_install']", installBase);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+installBase+"')]");
			ClearTextAreaUsingXpath("//input[@id='search-input_install']");
		}
		ClickOfTheField();
		return this;
	 }
	
	public CampaignBuilderPage selectIntentTopic(String[] intentTopics) throws InterruptedException {
    	ClickOnInputArea("//mat-select[@id='mat-select-24']");
		for(String intentTopic:intentTopics) {
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input_topic']", intentTopic);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+intentTopic+"')]");
			ClearTextAreaUsingXpath("//input[@id='search-input_topic']");
		}
		ClickOfTheField();
		return this;
	 }
	
	public CampaignBuilderPage setLeadsPerDelivery() {
		
		SetTextWithFieldXPath("//input[@formcontrolname='leadsPerDay']","10");
		return this;
	}

	public CampaignBuilderPage selectDeliveryMethod(String methodOfDelivery) throws InterruptedException {
		    ClickOnInputArea("//mat-select[@id='mat-select-26']");		
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input_method']", methodOfDelivery);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+methodOfDelivery+"')]");				
		    ClickOfTheField();
		   return this;		
	}
	
	public CampaignBuilderPage selectFrequency(String frequency) throws InterruptedException {
	    ClickOnInputArea("//mat-select[@id='mat-select-28']");		
		Thread.sleep(500);		
		ClickUsingXPath("//mat-option/span[contains(text(),'"+frequency+"')]");
	    ClickOfTheField();
	return this;		
}
	public CampaignBuilderPage selectDOD(String[] days) throws InterruptedException {
		ClickOnInputArea("//mat-select[@id='mat-select-30']");
		for(String day:days) {
			Thread.sleep(500);
			SetTextWithFieldXPath("//input[@id='search-input_day']", day);
			ClickUsingXPath("//mat-option/span[contains(text(),'"+day+"')]");
			ClearTextAreaUsingXpath("//input[@id='search-input_day']");
		}
		ClickOfTheField();
		return this;		
}

	//******************Advanced Criteria**************************	
	public CampaignBuilderPage assetLinkRadio() {
		ClickUsingXPath("//mat-radio-button[@id='mat-radio-5']");
		return this;
	}
	
	public CampaignBuilderPage assetRadio() {
		ClickUsingXPath("//mat-radio-button[@id='mat-radio-6']");
		return this;
	}
	
	public CampaignBuilderPage addLinks() throws InterruptedException {
		int links = 3;
		while(links>0) {
			SetTextWithFieldXPath("//input[@placeholder='Enter Your Link']", "Automation Link number "+links);
			ClickUsingXPath("//button[contains(text(),'Add link')]");
			links--;
			Thread.sleep(500);
		}
		return this;
	}
	
	public CampaignBuilderPage removeLinks() throws InterruptedException {
		int links = 3;
		while(links>0) {			
			ClickUsingXPath("//div[@class='cc_link_head show']/div/div/div/button");
			links--;
			Thread.sleep(500);
		}
		return this;
	}

	public CampaignBuilderPage deleteAssetFile() {
		ClickUsingXPath("//table/tbody/tr/td[text()='1']/following-sibling::td[3]/button");
		return this;
	}

	public CampaignBuilderPage addCustomQuestion() {
		ClickUsingXPath("//div[@id='cdk-step-content-0-1']/form/div/div/button/img");
		SetTextWithFieldXPath("//div[@class='value']/input", "40");
		ClickUsingXPath("//div[@class='submit']/button");
		int num = 1;
		ArrayList<WebElement> listOfCustomQuestions = (ArrayList<WebElement>) getListOfElements("//input[@placeholder='Add Your Custom Questions']");
		for(WebElement custQuest : listOfCustomQuestions) {
			custQuest.sendKeys("Automation Custom question number: "+num);
			num++;
		}			
		return this;		
	}
	
}
