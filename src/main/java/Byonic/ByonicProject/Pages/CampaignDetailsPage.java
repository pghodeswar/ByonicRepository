package Byonic.ByonicProject.Pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;

public class CampaignDetailsPage extends BasePage{

	private ArrayList<String> campDetails;
	private Iterator<WebElement> iter;
	private String str;

	public CampaignDetailsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public ArrayList<String> getCampignsDetails() throws InterruptedException {
		campDetails = new ArrayList<String>() ;		
		ArrayList<WebElement> campDetailsWeb = new ArrayList<WebElement>();
		//Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-card-header/div/mat-card-title[contains(text(),'Advanced Requirements')]")));
		campDetailsWeb = (ArrayList<WebElement>) getListOfElements("//mat-card-header/div/mat-card-title");		
		iter = campDetailsWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			campDetails.add(str);			
		}	
		return campDetails;
	}
	
	public ArrayList<String> getNotesDetails() throws InterruptedException {
		campDetails = new ArrayList<String>() ;		
		ArrayList<WebElement> campDetailsWeb = new ArrayList<WebElement>();
		
		campDetailsWeb = (ArrayList<WebElement>) getListOfElements("//div[@class='notes_list']/table/tr[1]/th");		
		iter = campDetailsWeb.iterator();
		while(iter.hasNext()) {
			str = iter.next().getText();
			campDetails.add(str);			
		}	
		return campDetails;
	}

	public CampaignDetailsPage setNotes(String note) {
		SetTextWithFieldXPath("//textarea[@formcontrolname='campaignNote']", note);
		//ClickUsingXPath("//button[@type='submit']");
		ClickOfTheField();
		return this;
	}
	
    public String getLatestNoteFromTable() throws InterruptedException {
    	List<WebElement> strOfWeb = driver.findElements(By.xpath("//div[@class='notes_list']/table/tr"));    	    	
    	strOfWeb.get(strOfWeb.size()-1).click();    	
		return GetTextUsingXPath("//div[@id='campaign_note']/p");
    }
    
    public String getLatestNoteName() {
    	List<WebElement> strOfWeb = driver.findElements(By.xpath("//div[@class='notes_list']/table/tr"));    	    	
    	WebElement str = strOfWeb.get(strOfWeb.size()-1);
    	String strString = str.findElement(By.xpath("td[2]")).getText();
		return strString;    	
    }
    
}
