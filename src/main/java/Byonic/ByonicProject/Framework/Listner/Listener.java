package Byonic.ByonicProject.Framework.Listner;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Byonic.ByonicProject.Framework.BaseTestFolder.BaseTest;


public class Listener extends BaseTest implements ITestListener {

	ExtentTest test;
	String TestMethodName;
	ExtentReports extent;
	public static ExtentReports extentobj;
	


//Take screenshot 
public String TakeScreenshotBaseFunction(String TestCaseName) throws IOException
{
	TakesScreenshot ts =(TakesScreenshot ) driver;
	File  Src = ts.getScreenshotAs(OutputType.FILE);
	String DestinationFile = System.getProperty("user.dir")+"./Output_Screenshots/"+TestCaseName+".png";
	FileUtils.copyFile(Src, new File(DestinationFile));
	return DestinationFile;
}

public  static ExtentReports getReportObject()
{
	String path  = System.getProperty("user.dir")+"\\Output_Reports\\index.html";
	ExtentHtmlReporter  reporter = new ExtentHtmlReporter(path);//It creates a report at given path 
	reporter.config().setReportName("Byonic Automation Results");
	reporter.config().setDocumentTitle("Automation Results");
	
	//Document Title 

	 extentobj = new ExtentReports();
	extentobj.attachReporter(reporter);
	extentobj.setSystemInfo("Tester", "Pankaj Ghodeswar");
	return extentobj;

}



//*************************************************************************************
public void onTestStart(ITestResult result)
{
	if(extent==null)
	{
	 extent = getReportObject();
	}
	test = extent.createTest(result.getMethod().getMethodName());
}

public void onTestFailure(ITestResult result) {
	
	
	test.fail(result.getThrowable());
	 TestMethodName =result.getMethod().getMethodName();
	try {
		String Path =this.TakeScreenshotBaseFunction(TestMethodName);
        test.addScreenCaptureFromPath(Path);		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}

public void onFinish(ITestContext context)
{
//extent.flush();
}

public void onTestSuccess(ITestResult result) 
{
	test.log(Status.PASS, "Test Passed");
	//test.pass(result.getTestName());
	
}

public void onTestSkipped(ITestResult result) {}


public void onTestFailedButWithinSuccessPercentage(ITestResult result)
{
}
public void onStart(ITestContext context) {}

}
