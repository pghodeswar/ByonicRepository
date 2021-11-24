package Byonic.ByonicProject.Framework.RandomDataGenerator;

import java.util.Random;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import Byonic.ByonicProject.Framework.BasePageFolder.BasePage;

/*This class will return the object of the random class using which we can create 
 * random data of all types 
*/


public class RandomDataGenerator extends BasePage {
	


	
public RandomDataGenerator(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

public static Random RandomInstance()
{
	Random ran = new Random() ;
    return ran;	
}

//It will return random int data 
public static  int  RandomInt()
{ 
	int RandomInt = RandomDataGenerator.RandomInstance().nextInt(999999 - 99999 + 1) + 99999 ;
    return RandomInt ;	
}

public static  int  RandomPhone()
{ 
	int RandomInt = RandomDataGenerator.RandomInstance().nextInt(999999999 - 99999999 + 1) + 99999999 ;
    return RandomInt ;	
}


//It will return random string 
public static String RandomString()
{
	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz"; 

// create StringBuffer size of AlphaNumericString 
StringBuilder sb = new StringBuilder(6); 

for (int i = 0; i < 6; i++) { 

// generate a random number between 
// 0 to AlphaNumericString variable length 
int index 
= (int)(AlphaNumericString.length() 
* Math.random()); 

// add Character one by one in end of sb 
sb.append(AlphaNumericString 
  .charAt(index)); 
} 

return sb.toString(); 
} 	
}

