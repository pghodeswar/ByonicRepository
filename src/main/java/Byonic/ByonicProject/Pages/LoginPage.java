package Byonic.ByonicProject.Pages;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;
import de.taimos.totp.TOTP;

public class LoginPage extends BasePage {

	String userIdxpath = "//input[@data-placeholder='Login']";
	String psswdxpath = "//input[@data-placeholder='Password']";
	String MFAFieldPath = "//input[@data-placeholder='Authenticator Code']";
	String tryAgainpath = "//button[@type = 'button' and contains(text(),'Try again')]";
	String errordialog = "//div[@class='login__fail_container']";
	String signxpath = "//button[contains(text(),'SIGN IN')]";
	String MFASecretKey = "44LEQOGSUHM4XBHMUJDK3T4SSCDHJENS";
	int round = 4;
	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	//Login with *** MFA ***
	
	public LoginPage login(String userID,String password) throws InterruptedException { 
		resume : 
		while(round>0) {
			SetTextWithFieldXPath(userIdxpath, userID);
			SetTextWithFieldXPath(psswdxpath, password);
			SetTextWithFieldXPath(MFAFieldPath, getTOTPCode(MFASecretKey));
			ClickUsingXPath(signxpath);
			if(IsExistsUsingXPath(errordialog)) {
				ClickUsingXPath(tryAgainpath);
				round--;
				Thread.sleep(10000);
				continue resume;				
			}
			return this;	
		}
		return this;    
	}

public String getTOTPCode(String secretKey) {
	Base32 base32 = new Base32();
	byte[] bytes = base32.decode(secretKey);
	String hexKey = Hex.encodeHexString(bytes);
	return TOTP.getOTP(hexKey);
	}



public LoginPage login_Old(String userID,String password) {
	
	SetTextWithFieldXPath(userIdxpath, userID);
	SetTextWithFieldXPath(psswdxpath, password);
	ClickUsingXPath(signxpath);
	return this;		
}


	
}
