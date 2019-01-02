/*
 * This are most commonly used functions in C5 testing.
 * Every TestCase should extend this class.
 *  
 *  setUp("server", "port", "browser", "url")
 *  Parameters:
 *  	Server - URL of a SeleniumRC server on which the test will be executed.
 *  	Port   - Port of the SeleniumRC server.
 *  	Browser - Browser to be used while running the test. (See Selenium documentation for list of browser codes).
 *  	URL     - Start page for the test.
 *  
 *  tearDown()
 *  Login("username", "password")
 *  Logout()
 *  
 *  checkText("text") 
 *  checkTextFromField()s
 *  checkNoText("text")
 *  
 *  isElementPresent()
 *  waitForPageLoaded()
 *  checkElement("element")
 *  checkNoElement("element")
 *  
 *  checkSelect()
 *  
 *  isChecked()
 *  isNotChecked()
 *  
 *  isEditable()
 *  isNotEditable()
 *  
 *  waitForElement("element")
 *  waitForNoElement("element")
 *  waitForText("text")
 *  waitForNoText("text")
 *  
 *  pressList()
 *  checkList()
 *  
 *  backIfError()
 *  reportError()
 *  
 *  getDateTime()
 *  startTimer()  
 *  endTimer(String Start, long StartTime)
 *  
 *  randomGeneration(Length, Type)
 *  	Types:
 *  	UPPER   - only uppercase characters.
 *  	LOWER   - only lowercase characters.
 *  	MIXED   - lowercase AND uppercase characters.
 *  	NUMERIC - only numbers.
 *  	SPECIAL - only special symbols.
 *  	ALL     - lowercase, uppercase, numbers and special symbols.
 *    
 *  
 *  Created by "LT Testing Team"
 *  Updated for Selenium 2 By Bangalore Testing Team
 */

package com.common;

import java.io.*;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;

//import org.testng.Reporter;
//import org.testng.annotations.AfterMethod;

//import org.testng.annotations.BeforeMethod;

//import org.testng.annotations.Parameters;

import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.Selenium;



@SuppressWarnings("deprecation")
public class CommonFunctions extends SeleneseTestBase{
	
	public static Selenium selenium;
	public static WebDriver driver;	
	public static String loginUsername = "venkata.seshaiah@attra.com.au";       							 // login username
	public static String loginPassword = "Lishi@365";     							 // login password
	public static String URL;
	String browser;
	public static String timeout                        = "10000";       // load timeout time
    public static int sleep                             = 3000;          // default thread sleep time
	public static int waitTime                          = 20;            // default witForElement time
	private static final Logger logger = Logger.getLogger(CommonFunctions.class.getName());

	
/* ---------------------------- RC Server StartUp --------------------------------------------*/

	// starts browser on WebDriver (WebDriver settings are held in testng.xml)
	 //@Before
	 //@Parameters({"browser", "url" , "loginUsername" , "loginPassword"})
	
	// public  void setUp(String browser, String url, String login, String password) throws InterruptedException
/*	 public  void setUp() throws InterruptedException
	 {		 		 
		 //		File pathToFirefoxBinary = new File(browser);  
		 //		FirefoxBinary firefoxbin = new FirefoxBinary(pathToFirefoxBinary);
		 //		driver = new FirefoxDriver(firefoxbin,null);
		 //		selenium = new WebDriverBackedSelenium(driver, url);		 
		 //		File file = new File("E:\\selinum\\IEDriverServer_Win32_2.31.0\\IEDriverServer.exe"); //IE instance
		 //		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		 //		WebDriver driver = new InternetExplorerDriver();
		 //		selenium = new WebDriverBackedSelenium(driver, url);		
		 //		driver.manage().window().maximize();		
		 		
	//	 if(browser.equalsIgnoreCase("IE"))
		 {
//		 IE Code
		// System.setProperty("webdriver.ie.driver", "E:\\Selenium\\lib\\IEDriverServer_Win32_2.32.3\\IEDriverServer.exe");
		 
		 /*System.setProperty("webdriver.ie.driver", "E:\\old drive\\selenium jars\\IEDriverServer.exe");
		 
		 
		 driver = new InternetExplorerDriver();
		 }
		 //Chrome code
		 if(browser.equalsIgnoreCase("Chrome"))
		 {
		 System.setProperty("webdriver.chrome.driver", "E:\\old drive\\selenium jars\\chromedriver.exe");
		 Thread.sleep(sleep);
		 driver=new ChromeDriver();
		 }
		 if(browser.equalsIgnoreCase("Firefox"))
		 {
		//System.setProperty("webdriver.gecko.driver", "D:\\selenium jars\\geckodriver.exe");	 
		
		System.setProperty("webdriver.gecko.driver", "E:\\old drive\\selenium jars\\geckodriver.exe");
	driver=new FirefoxDriver();
			selenium = new WebDriverBackedSelenium(driver, url);  	
		 }*/
			/* System.out.println("***** Lanching the Browser started ****");
		      if (browser.equalsIgnoreCase("chrome"))
		      {
		        DesiredCapabilities cap = DesiredCapabilities.chrome();
		        String key = "webdriver.chrome.driver";
		        String value = System.getProperty("user.dir") + "\\Library\\Drivers\\chromedriverNew.exe";
		        System.setProperty(key, value);
		        driver = new org.openqa.selenium.chrome.ChromeDriver(cap);
		      }
		      if (browser.equalsIgnoreCase("ie"))
		      {
		        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		        String key = "webdriver.ie.driver";
		        String value = System.getProperty("user.dir") + "\\Library\\Drivers\\IEDriverServer.exe";
		        System.setProperty(key, value);
		        driver = new InternetExplorerDriver(cap);
		      }
		      if (browser.equalsIgnoreCase("firefox"))
		      {
		        DesiredCapabilities cap = DesiredCapabilities.firefox();
		        String key = "webdriver.gecko.driver";
		        String value = System.getProperty("user.dir") + "\\Library\\Drivers\\geckodriver.exe";
		        System.setProperty(key, value);
		        driver = new FirefoxDriver(cap);
		      }
		      System.out.println("***** Lanching the Browser Ended ****");
		 
		      driver.manage().window().maximize();
    	
    	
    		URL=url;
 
    		loginUsername = login;
    		loginPassword = password;
	 }
	 }*/
	// shuts down browser
	/*@AfterMethod
	public void tearDown() throws Exception
	{				
		File scrnsht = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File outputDir = new File("D:\\FailureScrShots\\");
		File saved = new File(outputDir,this.getClass().getName()+".png");
		org.apache.commons.io.FileUtils.copyFile(scrnsht, saved);
		driver.quit();
		
		// this works for the TestNG reporter log but not for ReportNG since the results are under the html/ subdir 
		}*/

/* ---------------------------- Variable generation -----------------------------------------*/
	
	public enum Modes
	{
		UPPER, LOWER, MIXED, NUMERIC, SPECIAL, ALL;
	}
		
	public static String randomGenerator(int count, String mode)
	{
		Random rand = new Random();
		String result = "";

		int[] specialArray = {33,64,35,36,37,38,42,45,43,60,62,95,63};

			try
			{
				switch(Modes.valueOf(mode))
				{
				case UPPER:		
					for(int i = 0; i < count ; i++)
					{
						int randomNumber = rand.nextInt(25) + 65;
						result = result + (char)randomNumber;
					}	
					break;
					
				case LOWER:
					for(int i = 0; i < count ; i++)
					{
						int randomNumber = rand.nextInt(25) + 65;
						result = result + (char)randomNumber;
					}
					result = result.toLowerCase();
					break;
					
				case MIXED:			
					for(int i = 0; i < count; i++)
					{
						int randomNumber = rand.nextInt(25) + 65;
						char tmpChar = (char) randomNumber;
						
						if(randomNumber %2 == 0)
							tmpChar = Character.toLowerCase(tmpChar);
						
						result = result + tmpChar;
					}
					break;
					
				case NUMERIC:
					for(int i = 0; i < count; i++)
					{			
						result = result + String.valueOf(rand.nextInt(10));
					}
					break;
					
				case SPECIAL:
					for(int i = 0; i < count; i++)
					{
						int randomNumber = rand.nextInt(13);
						result = result + (char) specialArray[randomNumber];
					}
					break;
	
				case ALL:
				{
					for(int i=0; i < count; i++)
					{
						int randomNumber = rand.nextInt();
					
						if(randomNumber %4 == 0) // Then we place a special symbol.
						{
							randomNumber = rand.nextInt(13);
							result = result + (char) specialArray[randomNumber];
						}
						else if(randomNumber %3 == 0) // Then we place a number
						{
							randomNumber = rand.nextInt(10);
							result = result + Integer.toString(randomNumber);
						}
						else // Then we place a character
						{
							randomNumber = rand.nextInt(25) + 65;
							if(randomNumber %2 == 0)
								result = result + Character.toLowerCase((char) randomNumber);
							else
								result = result + (char) randomNumber;
						}
					}
					break;
				}
			}	
				return result;
		}
		catch (Exception ex)
		{
			System.out.println(ex);
			return result;
		}
	}
	
	 /* @AfterSuite
	    public void AutoEmail(ITestContext ctx)throws Exception
		{ 
		  
	    	AutoEmailWithReport email=new AutoEmailWithReport();
	    	email.AutoEmail1(ctx);
	   }*/
/* -------------------------------- Variable generation end ----------------------------------*/
	

	
/* --------------------------------------- Validation ------------------------------------------ */
	
	public boolean isElementPresent(By by) { 
		try { 
			driver.findElement(by);
			return true; 
			} 
		catch (NoSuchElementException e) { 
			return false;
			}
		}
	
	public boolean isTextPresent(String text){
	    try{
	        boolean b = driver.getPageSource().contains(text);
	        return b;
	    }
	    catch(Exception e){
	        return false;
	    }
	  }
	
	public void select(String element,String opt) throws Exception{
		Thread.sleep(sleep);
		Select selectElement=new Select(driver.findElement(By.xpath(element)));
		selectElement.selectByVisibleText(opt);
		Thread.sleep(sleep);
		//JavascriptLibrary javascript = new JavascriptLibrary();
		//javascript.callEmbeddedSelenium(driver, "triggerEvent", driver.findElement(By.xpath(element)), "change");
		Thread.sleep(sleep);
		
		        }
		    

		
	// checks if Element exists, if not - continue test, but throws ERROR in report
	public boolean checkElement(By element) throws Exception
	{
		if(!isElementPresent(element)){
		Thread.sleep(sleep);
		
		if(!isElementPresent(element))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO ELEMENT PRESENT in " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": "
					+ element + "</font>");
			return false;
		}
		else
			return true;
		}
		else
			return true;
	}
	public boolean checkElement(By element, int time) throws Exception
	{		
		if(time > 0)
			Thread.sleep(time*1000);
		
		if(isElementPresent(element))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO ELEMENT PRESENT in " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
					+ element + "</font>");
			return false;
		}
		else
			return true;		
	}
	public boolean checkEditable(WebElement element) throws Exception
	{
		Thread.sleep(sleep);
		
		if(element.isEnabled())
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT NOT EDITABLE in " 
					+ this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +":" +
							" " + element + "</font>");
			return false;
		}
		else
			return true;
	}
	public boolean checkNotEditable(WebElement element) throws Exception
	{
		Thread.sleep(sleep);
		
		if(element.isEnabled())
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT EDITABLE in " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
					+ element + "</font>");
			return false;
		}
		else
			return true;
	}
	
	public boolean checkElementChecked(WebElement element) throws Exception
	{
		Thread.sleep(sleep);
		
		if(!element.isSelected())
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT NOT CHECKED in " 
					+ this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
					+ element + "</font>");
			return false;
		}
		else
			return true;
	}	
	public boolean checkElementNotChecked(WebElement element) throws Exception
	{
		Thread.sleep(sleep);
		
		if(element.isSelected())
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT CHECKED in " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
					+ element + "</font>");
			return false;
		}
		else
			return true;
	}	
	// checks if Text exists, if not - continue test, but throws ERROR in report
	public boolean checkText(String text) throws Exception
	{
		if(!driver.getPageSource().contains(text)) {
		Thread.sleep(sleep);
		
		if(!driver.getPageSource().contains(text))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO TEXT PRESENT in " +
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
					+ text + "</font>");
			return false;
		}
		else
			return true;
		}
		else
			return true;
	}
	public boolean checkText(String text, int time) throws Exception
	{
		if(time > 0)
			Thread.sleep(time*1000);
		
		if(!driver.getPageSource().contains(text))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO TEXT PRESENT in " +
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": "
					+ text + "</font>");
			return false;
		}
		else
			return true;			
	}
	public boolean checkText(String text, String where) throws Exception
	{
		if(!driver.findElement(By.xpath(where)).getText().equals(text)) {	
			Thread.sleep(sleep);

			if(!driver.findElement(By.xpath(where)).getText().equals(text)) 
			{
				logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO TEXT PRESENT in " + 
						this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
						+ text + "</font>");
				return false;
			}
			else
				return true;
		}
		else
			return true;
	}
	public boolean checkValue(String text, String where) throws Exception
	{
		if(!driver.findElement(By.xpath(where)).getAttribute("value").equals(text)){
			Thread.sleep(sleep);

			if(!driver.findElement(By.xpath(where)).getAttribute("value").equals(text))
			{
				logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO VALUE PRESENT in " + 
						this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
						+ text + "</font>");
				return false;
			}
			else
				return true;
		}
		else
			return true;
	}
	public boolean checkTextFromField(String element, String value)
	{
		if(!element.isEmpty())
		{
			String finalString = element.substring(0, element.length() -1 ) + "and (@value='" + value + "')]";
									
			if(isElementPresent(By.xpath(finalString)))
				return true;
			else
			{
				logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO TEXT PRESENT in " + 
						this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": "
						+ value + "</font>");
				return false;
			}
		}
		else
			return false;
	}		
	public boolean checkSelectedLabel(String text, Select select) throws Exception
	{
		if(!select.getFirstSelectedOption().getText().equals(text)){
			Thread.sleep(sleep);

			if(!select.getFirstSelectedOption().getText().equals(text))
			{
				logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO LABEL SELECTED in " +
						this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
						+ text + "</font>");
				return false;
			}
			else
				return true;
		}
		else
			return true;
	}
	
	// checks if Text is present, if it is - continue test, but throw's an ERROR in report
	public boolean checkNoText(String text) throws Exception
	{
		Thread.sleep(sleep);

		if(driver.getPageSource().contains(text))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">TEXT PRESENT in " +
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": "
					+ text + "</font>");
			return false;
		}
		else
			return true;
	}
	public boolean checkNoText(String text, int time) throws Exception
	{
		if(time > 0)
			Thread.sleep(time*1000);
		
		if(driver.getPageSource().contains(text))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">TEXT PRESENT in " +
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": "
					+ text + "</font>");
			return false;
		}
		else
			return true;
	}
	public boolean checkNoText(String text, String where) throws Exception
	{
		Thread.sleep(sleep);
		
		if(driver.findElement(By.xpath(where)).getText().equals(text))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO TEXT PRESENT in " +
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
					+ text + "</font>");
			return false;
		}
		else
			return true;
	}
	
	// checks if Element is present, if it is - continue test, but throw's an ERROR in report
	public boolean checkNoElement(By element) throws Exception
	{
		Thread.sleep(sleep);
		
		if(isElementPresent(element))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT PRESENT in " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " 
					+ element + "</font>");
			return false;	
		}
		else
			return true;
	}
	public boolean checkNoElement(By element, int time) throws Exception
	{
		if(time > 0)
			Thread.sleep(time*1000);
		
		if(isElementPresent(element))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT PRESENT in " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": "
					+ element + "</font>");
			return false;	
		}
		else
			return true;
	}
	public boolean isChecked(By element)
	{
		WebElement selectElement=driver.findElement(element);
		if(selectElement.isSelected())
			return true;
		else
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT NOT CHECKED in " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " + 
					element + "</font>");
			return false;
		}
	}
	public boolean isNotChecked(By by)
	{
		if(!(driver.findElement(by).isSelected()))
			return true;
		else
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT CHECKED in " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " + 
					by + "</font>");
			return false;	
		}
	}
		
	public boolean isEditable(By element)
	{
		if(driver.findElement(element).isEnabled())
			return true;
		else
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT NOT EDITABLE " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " + 
					element + "</font>");
			return false;
		}
	}
	public boolean isNotEditable(By element)
	{
		if(driver.findElement(element).isEnabled())
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">ELEMENT EDITABLE " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": " + 
					element + "</font>");
			return false;
		}
			
		else
			return true;
	}
	
	// waits for Text on page, if not presented - fails test with ERROR output
	public void waitForText (String text) throws Exception
	{
		Thread.sleep(sleep);	
		
		for (int second = 0;; second++)
		{
			if (second >= waitTime) fail("Timeout ("+ waitTime +": Text "+ text +" not found.");
			
			if (driver.getPageSource().contains(text))
				break;
			
			Thread.sleep(1000);
		}
		
		Thread.sleep(sleep);
	}
	public void waitForText (String text, int time) throws Exception
	{
		Thread.sleep(sleep);
		
		for (int second = 0;; second++)
		{
			if (second >= time) fail("Timeout ("+ time +": Text "+ text +" not found.");
			
			if (driver.getPageSource().contains(text))
				break;
			
			Thread.sleep(1000);
		}
		
		Thread.sleep(sleep);
	}	
	
	// waits for Element, if not presented - fails test with ERROR output
	public void waitForElement(By element) throws Exception
	{
		Thread.sleep(sleep);
		
		for (int second = 0;; second++)
		{
			if (second >= waitTime)
				fail("Timeout ("+ waitTime +": Element "+ element +" not found.");
			
			if (isElementPresent(element))
				break;
			
			Thread.sleep(1000);
		}	
		
		Thread.sleep(sleep);
	}
	public void waitForElement(By element, int time) throws Exception
	{
		Thread.sleep(sleep);
		
		for (int second = 0;; second++)
		{
			if (second >= time)
				fail("Timeout ("+ time +": Element "+ element +" not found.");
			
			if (isElementPresent(element))
				break;
			
			Thread.sleep(1000);
		}	
		
		Thread.sleep(sleep);
	}
	
	// waits for no text on page, if presented - fails test with ERROR output
	public void waitForNoText (String text) throws Exception
	{
		Thread.sleep(sleep);
		
		for (int second = 0;; second++)
		{
			if (second >= waitTime) fail("Timeout ("+ waitTime +": Text "+ text +" found.");
			
			if (!driver.getPageSource().contains(text))
				break;
			
			Thread.sleep(1000);
		}		
		
		Thread.sleep(sleep);
	}
	public void waitForNoText (String text, int time) throws Exception
	{
		Thread.sleep(sleep);
		
		for (int second = 0;; second++)
		{
			if (second >= waitTime*1000) fail("Timeout ("+ time*1000 +": Text "+ text +" found.");
			
			if (!driver.getPageSource().contains(text))
				break;
			
			Thread.sleep(1000);
		}	
		
		Thread.sleep(sleep);
	}
	
	// waits for no Element, if presented - fails test with ERROR output
	public void waitForNoElement(By element) throws Exception
	{
		Thread.sleep(sleep);
		
		for (int second = 0;; second++)
		{
			if (second >= waitTime)
				fail("Timeout ("+ waitTime +": Element "+ element +" found.");
			
			if (!isElementPresent(element))
				break;
			
			Thread.sleep(1000);
		}	
		
		Thread.sleep(sleep);
	}
	public void waitForNoElement(By element, int time) throws Exception
	{
		Thread.sleep(sleep);
		
		for (int second = 0;; second++)
		{
			if (second >= time)
				fail("Timeout ("+ time +": Element "+ element +" found.");
			
			if (!isElementPresent(element))
				break;
			
			Thread.sleep(1000);
		}	
		
		Thread.sleep(sleep);
	}

	// check if select is equals
	/*public boolean checkSelect(String where, String[] what) throws Exception
	{
		Thread.sleep(2*sleep);
		driver.findElement(By.xpath(where)).
		String[] out = selenium.getSelectOptions(where);
		
		Select mySelectElement = new Select(driver.findElement(By.xpath(where)));
		List<WebElement> allSelectedOptions =mySelectElement.getAllSelectedOptions();
		
		//----INCOMPLETE CODE....
		
		if(Arrays.equals(out, what))
			return true;
		else
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">Select drop downs in " + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +
					" is not equal.</font>");
			return false;
		}	
	}*/
	public  boolean checkTrue(boolean value) throws Exception {
		
		if(value==false)
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">EXPECTED TRUE BUT SAW FALSE STATEMENT" + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": </font>");
			return false;		
		
	}
public boolean checkFalse(boolean value) throws Exception {
		
		if(value==true)
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">EXPECTED FALSE BUT SAW TRUE STATEMENT" + 
					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +": </font>");
			return false;			
	}
	
	
	public void backIfError(String errorMessage)
	{
		if(isTextPresent(errorMessage))
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\">"+ errorMessage +" in "+ 
					this.getClass() +" on "+Thread.currentThread().getStackTrace()[2].getLineNumber() +"</font>");
			driver.navigate().back();
			waitForPageLoaded(driver);
		}
	}
	
/* --------------------------------------- Validation end   ------------------------------------------ */
	
	
	
/* --------------------------------------- Login/Logout ---------------------------------------------- */

	
	public void Login(String username, String password,WebDriver driver) throws Exception
	{		
		//String RoleEmployerLink = "//span[contains(text(),'Role: [EMPLOYEE]')]";
    	
    	String loginButton=".//*[@id='loginUser']/button";
    	
    	//String loginUserNameLocation = "//input[contains(@name,'username')]";
    	String passwordLocation   =  "//input[contains(@name,'password')]";
    	
    	//driver = new HtmlUnitDriver(false);
    	driver.get("http://192.168.200.108:8080/SkillProficiencyClient");
    	
    /*	waitForElement(By.xpath("//input[contains(@name,'username')]"));*/
    	driver.findElement(By.xpath("//input[contains(@name,'username')]")).clear();
    	
    	driver.findElement(By.xpath("//input[contains(@name,'username')]")).sendKeys(username);
    	
    	driver.findElement(By.xpath(passwordLocation)).sendKeys(password);
    	Thread.sleep(sleep);
    	WebElement element= driver.findElement(By.xpath(loginButton));
    	element.click();
    	//waitForPageLoaded(driver);
    	//waitForElement(By.xpath(RoleEmployerLink));
    	//waitForPageLoaded(driver);
	}

	
	public void Logout() throws Exception
	{
		String buttonLogout = "//div[contains(@class,'logout')]";
		
		waitForElement(By.xpath(buttonLogout));
		driver.findElement(By.xpath(buttonLogout)).click();
		waitForPageLoaded(driver);
	}
	
/* ----------------------------------------- Login/Logout end ------------------------------------------ */
	
	
	
/* ------------------------------------------ Element pressing/selection -------------------------------- */
	
//	// press element in list by column
//	public static void pressList(String search, int col) throws Exception
//	{
//		int i = 1;
//		while(!selenium.getText("//table/tbody/tr[contains(@onclick, 'row:"+ i +"')]/td["+ col +"]/div").equals(search))
//		{		
//			if(i>25)
//				fail("No element");
//				
//			//Thread.sleep(sleep);
//			i++;
//		}
//
//		selenium.click("//table/tbody/tr[contains(@onclick, 'row:"+ i +"')]/td["+ col +"]/div");		
//	}

	 public void waitForPageLoaded(WebDriver driver) {

	        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	           public Boolean apply(WebDriver driver) {
	             return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	           }
	         };

	        Wait<WebDriver> wait = new WebDriverWait(driver,30);
	         try {
	                 wait.until(expectation);
	         } catch(Throwable error) {
	        	 Assert.assertFalse("Timeout waiting for Page Load Request to complete.", true);
	                 //Assert.assertFalse(true,"Timeout waiting for Page Load Request to complete.");
	         }
	    } 
	 
	public void pressList(String search, int col) throws Exception
	{		
		try
		{
			int divCounter	    = 1;		// Div counter, should be 1.
			int cycleCounter    = 1;		// Cycle counter, should always start with 1.
			int recordsPerPage	= 25;		// How many records can be contained on one page.
			
			if(isElementPresent(By.xpath("//a[contains(@onclick, 'first')]")))
				driver.findElement(By.xpath("//a[contains(@onclick, 'first')]")).click();
			
			while(!driver.findElement(By.xpath("//table/tbody/tr[contains(@onclick, 'row:"+ cycleCounter +"')]/td["+ col +"]/div")).getText().equals(search))
			{
				if((cycleCounter % (recordsPerPage * divCounter) == 0) && isElementPresent(By.xpath("//a[contains(@onclick, 'next')]")))
				{
					divCounter++;
					driver.findElement(By.xpath("//a[contains(@onclick, 'next')]")).click();
					Thread.sleep(sleep);
				}
				else if ((cycleCounter % (recordsPerPage * divCounter) == 0) && !isElementPresent(By.xpath("//a[contains(@onclick, 'next')]")))
					throw new Exception("No element found:" + search);
				
				cycleCounter++;
			}
			driver.findElement(By.xpath("//table/tbody/tr[contains(@onclick, 'row:"+ cycleCounter +"')]/td["+ col +"]/div")).click();
			waitForPageLoaded(driver);
		}
		catch(Exception ex)
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\"> " + ex.getMessage() + " in "
					+ this.getClass() +" on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +"</font>");
			fail("No element "+ search + "in list.");
		}
	}
	
	 public void pickPopup(String selectionTarget, String popupButton, int searchByCollumn) throws Exception
		{
			String popupWindow = "//div[contains(@class, 'ui-dialog ui-widget ui-widget-content ui-corner-all  ui-draggable')]";
			int i = 2;
			int k = 1;
			boolean isCorrect = false;
			
			try
			{
				driver.findElement(By.xpath(popupButton)).click();
				waitForElement(By.xpath(popupWindow),60);
				 
				driver.findElement(By.xpath("//button[contains(@onclick, 'allbutton')]")).click();
				Thread.sleep(3*sleep);
				System.out.println("thread.sleep");
				
				while(isElementPresent(By.xpath("//tr[" + i + "]")))
				{
					while(isElementPresent(By.xpath("//tr[" + i + "]/td[" + k + "]/div")))
					{

						if(selectionTarget.equalsIgnoreCase(driver.findElement(By.xpath("//tr[" + i + "]/td[" + k + "]/div")).getText()))
						{

							isCorrect = true;
							break;
						}

						k++;
						Thread.sleep(sleep);
					}
					
					if(isCorrect)
					{
						driver.findElement(By.xpath("//tr[" + i + "]")).click();
						Thread.sleep(sleep);
						break;
					}
					i++;
					k = 1;
					Thread.sleep(sleep);
					
					
				}
				
			}
			catch(Exception ex)
			{
				throw ex;
			}
		}
		

	
	public boolean checkList(String search, int col, boolean notPresent) throws Exception	// if true - check for element not present
	{		
		try
		{
			int divCounter	= 1;			// Div counter, should be 1.
			int cycleCounter = 1;			// Cycle counter, should always start with 1.
			int recordsPerPage	= 25;		// How many records can be contained on one page.
			
			if(!isElementPresent(By.xpath("//table/tbody/tr[contains(@onclick, 'row:" + cycleCounter + "')]/td["+ col +"]/div")))
			{
				if(notPresent)
					return true;
				else
					throw new Exception("Not found");
			}
			
			while(!driver.findElement(By.xpath("//table/tbody/tr[contains(@onclick, 'row:" + cycleCounter + "')]/td["+ col +"]/div")).getText().equals(search))
			{
				Thread.sleep(sleep);
				
				if((cycleCounter % (recordsPerPage * divCounter) == 0) && isElementPresent(By.xpath("//a[contains(@onclick, 'next')]")))
				{
					divCounter++;
					driver.findElement(By.xpath("//a[contains(@onclick, 'next')]")).click();
					Thread.sleep(sleep);
				}
				else if (!isElementPresent(By.xpath("//a[contains(@onclick, 'next')]"))
						&& !isElementPresent(By.xpath("//table/tbody/tr[contains(@onclick, 'row:" + (cycleCounter+1) + "')]")))
				{
					if(notPresent)
						return true;
					else
						throw new Exception("No element found: " + search);
				}
				cycleCounter++;
			}
			if(notPresent)
				throw new Exception("Element found: " + search);
			else
				return true;
		}
		catch(Exception ex)
		{
			logger.log(Priority.INFO,"<font color=\"FFFFFF\" style=\"background-color:#FF0000\"> " + ex.getMessage() + " in "
			+ this.getClass() +" on "+ Thread.currentThread().getStackTrace()[2].getLineNumber() +"</font>");
			
			return false;
		}
	}
	
	
/* -------------------------------------------- Element selection end ------------------------------------- */
	
	
	
/* -------------------------------------------- Time registration ------------------------------------------- */
	
	public static String getDateTime()
    {
    	Calendar calendar  = Calendar.getInstance();
    	
	    int year           = calendar.get(Calendar.YEAR);
	    int month          = calendar.get(Calendar.MONTH);
	    int day            = calendar.get(Calendar.DAY_OF_MONTH);
	    String dateDel     = "-";

	    int hh             = calendar.get(Calendar.HOUR_OF_DAY);
	    int mm             = calendar.get(Calendar.MINUTE);
	    int ss             = calendar.get(Calendar.SECOND);
	    String timeDel     = ":";
	    
	    String date        = year + dateDel + month + dateDel + day
		                   +";"+ 
		                   + hh   + timeDel + mm    + timeDel + ss;
	    	    
	    return date;
    }  
    
	public static long startTimer()
	{
		return new Date().getTime();			
	}
	
    public void endTimer(String Start, long StartTime, String comment) throws IOException
    {
		FileWriter fstream  = new FileWriter("out.csv", true);
		BufferedWriter out  = new BufferedWriter(fstream);
		
		long timer          = new Date().getTime() - StartTime;
		
		out.append(";"+ this.getClass().getName() +";"+ Start +";"+ timer +";"+ comment +";\n");

		out.close();
		fstream.close();
    }

/* -------------------------------------------- Time registration end ----------------------------------------- */
    
/* ---------------------------------------------Error reporting    -------------------------------------------- */

    public void reportError(String errorMessage)
    {
    	logger.log(Priority.INFO,"WARNING: " + errorMessage);
    }
    
/* ------------------------------------------------------------------------------------------------------------- */
}