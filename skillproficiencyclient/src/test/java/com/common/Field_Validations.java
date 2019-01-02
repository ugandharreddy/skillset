package com.common;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


//import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Reporter;

import com.attra.pms.controller.AdminController;


@SuppressWarnings({ "unused", "deprecation" })
public class Field_Validations extends CommonFunctions {
	
	private static final Logger logger = Logger.getLogger(Field_Validations.class.getName());
	//JavascriptLibrary javascript = new JavascriptLibrary();
	 String str[];
	 int i=0,j=0;
	public String timeout                        = "100000";      
    public int sleep                             = 3500;   
    String longInteger="123456790123456789012541.021547123";
    String longDesc="Long Description Long Description Long Description Long Description Long Description Long Description " +
	"Long Description Long Description Long Description Long Description Long Description";
	static String longDescAllowed="Long Description Long Description Long Description Long Description Long Description Long Description" +
	" Long Description L";
    
	 String searchFor                     = "//select[contains(@name,'searchByEnclosure:searchBy')]";
	 String searchFieldText              	= "//input[contains(@name,'searchcomponents:textFieldSearch')]";
	 String searchButton                  = "//span[contains(text(),'Search')]/parent::button";
	 String searchForfield                = "//select[contains(@name,'searchByEnclosure:searchBy')]";
	 String noRecords                     = "No records found...";
	 String rowFirst                      = "//table/tbody/tr[2]";
    
	 String nextButton="//span[contains(text(),'Next')]/parent::button";
    
  public  void DropDownFieldValidations(String element,String fieldLabel,String option[],String mandErrMsg) throws Exception{
		
	    checkText(fieldLabel);
	    WebElement selectField = driver.findElement(By.xpath(element));
	    Select select = new Select(selectField); 
	   // Thread.sleep(sleep);
	    List<WebElement> options = select.getOptions();  
	    //Thread.sleep(sleep);
	    for (int i=0; i<option.length; i++){
	    	int flag=0;
	    	for(WebElement we:options)  
	 	    { 
	    		  if (we.getText().equals(option[i])){  
	    	    	  flag=1;
	    	    }
	 	    }
	    	 if(flag==0)
	    		 
	    		 logger.log(Priority.INFO, "<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO OPTION PRESENT in " +
	    					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[1].getLineNumber() +": " 
	    					+ option[i] + "</font>");
	    	    /* Reporter.log("<font color=\"FFFFFF\" style=\"background-color:#FF0000\">NO OPTION PRESENT in " +
	    					this.getClass() + " on "+ Thread.currentThread().getStackTrace()[1].getLineNumber() +": " 
	    					+ option[i] + "</font>");*/
	    }
	    if(!mandErrMsg.equals("")) {      
	    	selectField.sendKeys("");
	    	driver.findElement(By.xpath("//span[contains(text(),'Next')]/parent::button")).click();
		    Thread.sleep(sleep);
		    checkText(mandErrMsg);
		      
		 }
	    
	}

public void textFieldValidations(String element,String fieldName, String errMsgMndtry,int fieldlength) throws Exception{
	  isEditable(By.xpath(element));
	   checkText(fieldName);
	   WebElement el = driver.findElement(By.xpath(element));
	
	   if(!errMsgMndtry.equals("")) {
	   el.sendKeys("");
	   Thread.sleep(sleep);
	   driver.findElement(By.xpath("//span[contains(text(),'Next')]/parent::button")).click();
	   waitForPageLoaded(driver);
	   checkText(errMsgMndtry);
       }
	   
	   if(fieldlength!=0) {
	   driver.findElement(By.xpath(element)).clear();
	   driver.findElement(By.xpath(element)).sendKeys(randomGenerator(fieldlength+10, "UPPER"));
	   Thread.sleep(sleep);
	   int len=driver.findElement(By.xpath(element)).getAttribute("value").length();
	   Thread.sleep(sleep);
       assertTrue(len == fieldlength );
       driver.findElement(By.xpath(element)).clear();
       Thread.sleep(sleep);
	   }
       
      }

public void radioButtonValidations(String fieldName,String rdButton1Element,String rdButton2Element) throws Exception {
		
 	   WebElement rdButton1 = driver.findElement(By.xpath(rdButton1Element));
	   WebElement rdButton2 = driver.findElement(By.xpath(rdButton2Element));
	   checkText(fieldName);
	   checkElement(By.xpath(rdButton1Element));
	   checkElement(By.xpath(rdButton2Element));
	   isEditable(By.xpath(rdButton1Element));
       isEditable(By.xpath(rdButton2Element));
	  
	   checkElementChecked(rdButton1);		
	   checkElementNotChecked(rdButton2);
	   rdButton1.click();
	   Thread.sleep(sleep*2);
	   rdButton2.click();
	   Thread.sleep(sleep*2);
		
}

public void amountOrPercentFieldValidation(String TextElement,String field_Label,String erMsgMndtry,int lenght,String erMsgNeg,String erMsgMaxData,String excd100Err,String zeroErr) throws Exception {
    WebElement element = driver.findElement(By.xpath(TextElement));
	isEditable(By.xpath(TextElement));
	checkText(field_Label);
	if(!erMsgMndtry.equals("")) {
	  driver.findElement(By.xpath(TextElement)).clear();
	  driver.findElement(By.xpath(TextElement)).sendKeys("");
      Thread.sleep(sleep);
	  driver.findElement(By.xpath("//span[contains(text(),'Next')]/parent::button")).click();
	  waitForPageLoaded(driver);
	  checkText(erMsgMndtry);
	}
	if(lenght!=0) {
		driver.findElement(By.xpath(TextElement)).clear();
		driver.findElement(By.xpath(TextElement)).sendKeys(longInteger);
		int len=driver.findElement(By.xpath(TextElement)).getAttribute("value").length();
	    assertTrue(len == lenght );
		
	}
	if(!erMsgNeg.equals("")) {
	  driver.findElement(By.xpath(TextElement)).clear();
	  Thread.sleep(sleep);
	  driver.findElement(By.xpath(TextElement)).sendKeys("-150000");
	  Thread.sleep(sleep);
	  driver.findElement(By.xpath("//span[contains(text(),'Next')]/parent::button")).click();
	  waitForPageLoaded(driver);
	  checkText(erMsgNeg);
	  Thread.sleep(sleep);
	}
	if(!erMsgMaxData.equals("")) {
	  driver.findElement(By.xpath(TextElement)).clear();
	  Thread.sleep(sleep);
      driver.findElement(By.xpath(TextElement)).sendKeys("76431654983568242548.12345664");
	  Thread.sleep(sleep);
	  driver.findElement(By.xpath("//span[contains(text(),'Next')]/parent::button")).click();
	  waitForPageLoaded(driver);
	  checkText(erMsgMaxData);
	  Thread.sleep(sleep*4);
	}
	if(!excd100Err.equals("")) {
	  driver.findElement(By.xpath(TextElement)).clear();	
	  Thread.sleep(sleep);
	  driver.findElement(By.xpath(TextElement)).sendKeys("105");
	  driver.findElement(By.xpath("//span[contains(text(),'Next')]/parent::button")).click();
	  waitForPageLoaded(driver);
	  checkText(excd100Err);
	  Thread.sleep(sleep);
	}

	if(!zeroErr.equals("")) {
		driver.findElement(By.xpath(TextElement)).clear();
		driver.findElement(By.xpath(TextElement)).sendKeys("0");
		Thread.sleep(sleep);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]/parent::button")).click();
		waitForPageLoaded(driver);
		checkText(zeroErr);
	}	
	
	}


public  void pickerAndLinkValidation(String link,String fieldLabel,String screenLabel,String pickerElement,String errMndtry,String searchOption,String searchValue,String elemSuspnd)throws Exception {
	WebDriverWait myWait = new WebDriverWait(driver, 10);
	if(!pickerElement.equalsIgnoreCase("")){
    WebElement pickerField = driver.findElement(By.xpath(pickerElement));
	checkElement(By.xpath(pickerElement));
	checkValue("",pickerElement);
	}
	checkText(fieldLabel);
    if(!errMndtry.equals("")) {
	  driver.findElement(By.xpath(pickerElement)).sendKeys("");
	  Thread.sleep(sleep);	
      driver.findElement(By.xpath("//span[contains(text(),'Next')]/parent::button")).click();
	  waitForPageLoaded(driver);
	  checkText(errMndtry);
	}
	driver.findElement(By.linkText(link)).click();
	waitForPageLoaded(driver);
	checkElement(By.xpath("//span[text()='"+screenLabel+"' and @class='ui-dialog-title']"));
	Select dropDown=new Select(driver.findElement( By.xpath(searchFor)));
	dropDown.selectByVisibleText(searchOption);
	Thread.sleep(sleep);
	waitForElement((By.xpath(searchFieldText)));
	driver.findElement(By.xpath(searchFieldText)).sendKeys(randomGenerator(8,"NUMERIC"));
	Thread.sleep(sleep);
	driver.findElement(By.xpath(searchButton)).click();
	Thread.sleep(sleep);
    checkText(noRecords);
	Thread.sleep(sleep);
	if(!elemSuspnd.equals("")){
    	driver.findElement(By.xpath(searchFieldText)).clear();
    	Thread.sleep(sleep);
		driver.findElement(By.xpath(searchFieldText)).sendKeys(elemSuspnd);
		driver.findElement(By.xpath(searchButton)).click();
		Thread.sleep(sleep);
		checkText(noRecords);
	}
    driver.findElement(By.xpath(searchFieldText)).clear();
	driver.findElement(By.xpath(searchFieldText)).sendKeys(searchValue);
	Thread.sleep(sleep);
	driver.findElement(By.xpath(searchFieldText)).clear();
	driver.findElement(By.xpath(searchFieldText)).sendKeys(searchValue);
	Thread.sleep(sleep);
    driver.findElement(By.xpath(searchButton)).click();
	Thread.sleep(sleep);
	waitForElement((By.xpath(rowFirst)));
	driver.findElement(By.xpath(rowFirst)).click();
	Thread.sleep(sleep);
}	

public void codeFieldValidns(String element,String fieldLabel,String errMndtry,String digitOrAlpha,int fieldLength,String codeExist,String errCodExist) throws Exception {
	
	isEditable(By.xpath(element));
	checkValue("", element);
    checkText(fieldLabel);
    if(!errMndtry.equals("")) {
    	driver.findElement(By.xpath(element)).clear();
      driver.findElement(By.xpath(element)).sendKeys("");
      Thread.sleep(sleep);
	  driver.findElement(By.xpath(nextButton)).click();
	  waitForPageLoaded(driver);
	  checkText(errMndtry);
    }
    if(digitOrAlpha.equals("Digit")) {
    	driver.findElement(By.xpath(element)).clear();
    	Thread.sleep(sleep);
    	driver.findElement(By.xpath(element)).sendKeys(randomGenerator(20,"NUMERIC"));
    }
    else
    {
    	driver.findElement(By.xpath(element)).clear();
    	Thread.sleep(sleep);
    	driver.findElement(By.xpath(element)).sendKeys(randomGenerator(20,"UPPER"));
    }
   
   // javascript.callEmbeddedSelenium(driver, "triggerEvent", driver.findElement(By.xpath(element)), "blur");
	int len=driver.findElement(By.xpath(element)).getAttribute("value").length();
    if(len!=fieldLength)
    	checkText("lenght of "+element+" is "+len);
    driver.findElement(By.xpath(element)).clear();
    if(!codeExist.equals("")) {
    	System.out.println(driver.findElement(By.xpath(element)).getAttribute("value"));
    driver.findElement(By.xpath(element)).sendKeys(codeExist);
    driver.findElement(By.xpath(nextButton)).click();
	waitForPageLoaded(driver);
	checkText(errCodExist);		
    }
}


}
