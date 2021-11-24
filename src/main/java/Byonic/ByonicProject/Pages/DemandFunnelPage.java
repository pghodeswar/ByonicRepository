package Byonic.ByonicProject.Pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;

public class DemandFunnelPage extends BasePage{

	private ArrayList<WebElement> ListOfLinksOnPage;
	int pass = 1;
	private ArrayList<String> graphName;
	ArrayList<WebElement> graphNameWeb;
	private Iterator<WebElement> iter;
	private String str;
	public DemandFunnelPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);}

	public void navigateToDemFunTab(String path) {
		ListOfLinksOnPage = null;
		ListOfLinksOnPage = (ArrayList<WebElement>) getListOfElements(path);		
		for (WebElement we : ListOfLinksOnPage) {
            System.out.println("Element number : "+pass+" ");
			
			try {
				if (we.isDisplayed()) {
					System.out.println(we.getText()+" : is displayed "+pass );
					switch(we.getText()) {
					case "Demand Funnel" :
						we.click();
						break;
					}
				}
			}catch(StaleElementReferenceException e) {
				pass++;
				System.out.println("Exception");
				continue;
			}
			pass++;
		}
	}

	public ArrayList<String> getGraphsOnDemFunDashboard() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='graph-content']/div/div[contains(text(),'By Industry')]")));
		
		graphName = new ArrayList<String>() ;		
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//div[@class='graph-content']/div/div[@class='graph-title']");
		iter = graphNameWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			System.out.println(str);
			graphName.add(str);
		}	
		return graphName;
	}

	public DemandFunnelPage clickOnSearchOfDemandFunnel() {
		ClickUsingXPath("//a[@href = '/Demand-funnel/search']");
		return this;
	}

	public ArrayList<String> getCardsOnDemandFunnelSearchPage() {
		graphName = new ArrayList<String>() ;		
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//div[@class='metrics-content']/div/div[@class='graph-title']");
		iter = graphNameWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			System.out.println(str);
			graphName.add(str);
		}	
		return graphName;
	}

	public List<String> getTableHearderOnDemandFunnelSearchPage() {
		graphName = new ArrayList<String>() ;		
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//table/thead/tr/th/div[@role='button']/div[1]");
		iter = graphNameWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			System.out.println(str);
			graphName.add(str);
		}	
		return graphName;
	}
	
	public ArrayList<String> getFiltersOnDemandFunnelSearchPage() throws InterruptedException {
		graphName = new ArrayList<String>() ;		
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//div[@id='leftbat-arcodian']/div/div/button/span[contains(text(),'')]");
		iter = graphNameWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getAttribute("innerHTML");
			System.out.println(str);
			graphName.add(str);
		}	
		return graphName;
	}
	
	public ArrayList<String> getProfileInformation() throws InterruptedException {
		graphName = new ArrayList<String>() ;		
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//div[@class='pros_content_details']/div/div[1]");
		iter = graphNameWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			System.out.println(str);
			graphName.add(str);
		}
		
//		if (driver.findElements(By.xpath("//div[@class='install-base']")).size() > 0) 
//		{
//			graphName.add(driver.findElement(By.xpath("//div[@class='install-base']")).getText());
//		}
		return graphName;
	}
	
	
	public void navigateToDemandFunnelTab(String path) throws InterruptedException{
		ListOfLinksOnPage = null;
		ListOfLinksOnPage = (ArrayList<WebElement>) getListOfElements(path);		
		for (WebElement we : ListOfLinksOnPage) {
            System.out.println("Element number : "+pass+" ");
			
			try {
				if (we.isDisplayed()) {
					System.out.println(we.getText()+" : is displayed "+pass);
					switch(we.getText()) {
					case "DemandFunnel" :
						we.click();//page.getInstance(BasePage.class).clickClickUsingXPath("//a[contains(text(),'DemandFunnel')]");
						break;
					}
				}
			}catch(StaleElementReferenceException e) {
				pass++;
				System.out.println("Exception");
				continue;
			}
			pass++;
		}
	}

	public DemandFunnelPage clickOnLocFilter() throws InterruptedException {
		WebElement Filter;
		graphName = new ArrayList<String>() ;		
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//div[@id='leftbat-arcodian']/div/div/button/span[contains(text(),'')]");
		iter = graphNameWeb.iterator();
		while(iter.hasNext()) {
			Filter = iter.next();	
			str = Filter.getAttribute("innerHTML");
			if(str.equals("Location")) {
				Filter.click();
				return this;
		}	
		
	}
		return this;
	}

	public DemandFunnelPage setLocationFilter(String locationFilter) throws InterruptedException {
		SetTextWithFieldXPath("//input[@placeholder='Search Location']", locationFilter);
		Thread.sleep(1000);
		ClickUsingXPath("//mat-option/span[contains(text(),'"+locationFilter+"')]");
		return this;
	}

	public DemandFunnelPage applyFilter() throws InterruptedException {
		Thread.sleep(500);
		ScrollToElements("//span[contains(text(),'Apply')]");
		ClickUsingXPath("//span[contains(text(),'Apply')]");
		return this;
	}

	public DemandFunnelPage clickMinOrMax() throws InterruptedException {
		ClickUsingXPath("//a[@id='toggle']");Thread.sleep(1000);
		ClickUsingXPath("//a[@id='toggle']");Thread.sleep(1000);
		ClickUsingXPath("//a[@id='toggle']");Thread.sleep(1000);
		ClickUsingXPath("//a[@id='toggle']");Thread.sleep(1000);
		return this;
	}

	public DemandFunnelPage clickOnRecord() {
		ClickUsingXPath("//div[@class='table-container']/table/tbody/tr/td[1]");
		return this;
	}

	public DemandFunnelPage clickOnSimilarProfile() {
		ClickUsingXPath("//button/span[contains(text(),'Similar Comp')]");
		return this;
	}

	public boolean verifySimilarCompanies(String empSize, String industry) {			
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//table/tbody/tr");
		iter = graphNameWeb.iterator();
		while(iter.hasNext()) {			
			if((empSize != iter.next().findElement(By.xpath("td[3]")).getText()) &&  (industry != iter.next().findElement(By.xpath("td[4]")).getText())) {
				return false;
			}			
		}

		return true;
	}
	

	public String getNameCompanyOfProfile() throws InterruptedException {		
		graphName = new ArrayList<String>();	
		String FullName="",Location="";
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//div[@class='pros_content_details']/div/div[1]");
		iter = graphNameWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			switch(str) {
			case "Full Name":
				FullName = GetTextUsingXPath("//div[@class='pros_content_details']/div[6]/div[2]/p");
				break;
			case "Location":
				Location = GetTextUsingXPath("//div[@class='pros_content_details']/div[2]/div[2]/p");
				break;
			}
		}
				
		return (FullName+","+Location);
	}
}



