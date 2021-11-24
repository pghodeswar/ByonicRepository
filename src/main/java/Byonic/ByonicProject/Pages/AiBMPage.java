package Byonic.ByonicProject.Pages;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;

public class AiBMPage extends BasePage{
	
	private ArrayList<WebElement> ListOfLinksOnPage;
	int pass = 1;
	private ArrayList<String> graphName;
	ArrayList<WebElement> graphNameWeb;
	private Iterator<WebElement> iter;
	private String str;

	public AiBMPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		}
	
	public void clickOnSearchOfAiBM() {
		ClickUsingXPath("//a[@href = '/AiBM/search']");		
	}

	public ArrayList<String> getGraphsOnAiBMDashboard() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='graph-content']/div/div[contains(text(),'By Company Size')]")));
		
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
	
	public ArrayList<String> getCardsOnAiBMSearchPage() throws InterruptedException {
		//Thread.sleep(2000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='metrics-content']/div/div[contains(text(),'By Intent Score')]")));
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
	
	public ArrayList<String> getTableHearderOnAiBMSearchPage() throws InterruptedException {
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
	
	public ArrayList<String> getFiltersOnAiBMSearchPage() throws InterruptedException {
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
	
	public ArrayList<String> getCompanyInformation() throws InterruptedException {
		graphName = new ArrayList<String>() ;		
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//div[@class='company__details']/div/div[1]/p");
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
	
	
	public void navigateToAiBMTab(String path) throws InterruptedException{
		ListOfLinksOnPage = null;
		ListOfLinksOnPage = (ArrayList<WebElement>) getListOfElements(path);		
		for (WebElement we : ListOfLinksOnPage) {
            System.out.println("Element number : "+pass+" ");
			
			try {
				if (we.isDisplayed()) {
					System.out.println(we.getText()+" : is displayed "+pass);
					switch(we.getText()) {
					case "AiBM" :
						we.click();//page.getInstance(BasePage.class).clickClickUsingXPath("//a[contains(text(),'AiBM')]");
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

	public AiBMPage clickOnLocFilter() throws InterruptedException {
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
		
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='leftbat-arcodian']/div/div/button/span[contains(text(),'Location')]")));
//		Thread.sleep(2000);
//		ClickUsingXPath("//div[@id='leftbat-arcodian']/div/div/button/span[contains(text(),'Location')]");
//		return this;
	}

	public AiBMPage setLocationFilter(String locationFilter) throws InterruptedException {
		SetTextWithFieldXPath("//input[@placeholder='Search Country']", locationFilter);
		Thread.sleep(1000);
		ClickUsingXPath("//mat-option/span[contains(text(),'"+locationFilter+"')]");
		return this;
	}

	public AiBMPage applyFilter() throws InterruptedException {
		Thread.sleep(500);
		ScrollToElements("//span[contains(text(),'Apply')]");
		ClickUsingXPath("//span[contains(text(),'Apply')]");
		return this;
	}

	public AiBMPage clickMinOrMax() throws InterruptedException {
		ClickUsingXPath("//a[@id='toggle']");Thread.sleep(1000);
		ClickUsingXPath("//a[@id='toggle']");Thread.sleep(1000);
		ClickUsingXPath("//a[@id='toggle']");Thread.sleep(1000);
		ClickUsingXPath("//a[@id='toggle']");Thread.sleep(1000);
		return this;
	}

	public AiBMPage clickOnCompany() {
		ClickUsingXPath("//div[@class='table__main']/table/tbody/tr/td[1]");
		return this;
	}

	public AiBMPage clickOnSimilarCompany() {
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
	

	public String getCompanyIndustryEmpSize() throws InterruptedException {		
		graphName = new ArrayList<String>();	
		String industry="",empSize="";
		graphNameWeb = new ArrayList<WebElement>();
		graphNameWeb = (ArrayList<WebElement>) getListOfElements("//div[@class='company__details']/div/div[1]/p");
		iter = graphNameWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			switch(str) {
			case "INDUSTRY":
				industry = GetTextUsingXPath("//div[@class='company__details']/div[6]/div[2]/p");
				break;
			case "EMPLOYEE SIZE":
				empSize = GetTextUsingXPath("//div[@class='company__details']/div[2]/div[2]/p");
				break;
			}
		}
				
		return (empSize+","+industry);
	}
}
