package pages;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmanager.PageObjectManager;
import utils.AbstractSteps;
import utils.Util;

public class EmailTemplatesPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	JavascriptExecutor executor = null;
	Util util = new Util();
	
	public EmailTemplatesPage(WebDriver driver) throws Exception{
		this.driver = getDriver();
		PageFactory.initElements(getDriver(), this);
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\SurgeAce_Regression.properties");
		config.load(fis);
	
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	protected Properties getConfig() {
	     return config;
	}
	
	//********** Add objects *********************************
	
	@FindBy(xpath=("//button[contains(text(),'Add Email Template')]"))
	public WebElement btn_addemailtemplate;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Create')]"))
	public WebElement lbl_createemailtemplate;
	
	@FindBy(xpath=("//input[@name='templateName']"))
	public WebElement txtbx_templatename;
	
	@FindBy(xpath=("//label[contains(text(),'Email Type')]//..//input[@role='combobox']"))
	public WebElement combo_emailtype;	
	
	@FindBy(xpath=("//input[@name='templateSubject']"))
	public WebElement txtbx_subject;
	
	@FindBy(xpath=("//textarea[@name='emailBody']"))
	public WebElement txtarea_emailbody;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Save']"))
	public WebElement btn_save;
	
	@FindBy(xpath=("//button[@type='button' and text()='Cancel']"))
	public WebElement btn_cancel;
	
	// ***********Search object***********************
	
	@FindBy(xpath=("//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Update']"))
	public WebElement btn_update;
	
	// ***********Edit objects***********************
	
	@FindBy(xpath=("//span//i[@id='pencilspace']"))
	public WebElement icon_edit;
	
	//**********Delete objects***********************
	
	@FindBy(xpath=("//span//i[@id='trashspace']"))
	public WebElement icon_delete;
	
	@FindBy(xpath=("//h2[text()='Are you sure?']"))
	public WebElement popup_delete;
	
	@FindBy(xpath=("//button[text()='Yes, delete it!']"))
	public WebElement btn_yesdelete;
	

	@FindBy(xpath=("//button[text()='Cancel']"))
	public WebElement btn_canceldelete;
	
	
	@FindBy(xpath=("//h2[text()='Record Deleted!']"))
	public WebElement lbl_recorddeleted;
	
	@FindBy(xpath=("//button[text()='OK']"))
	public WebElement btn_ok;
	
	public void addScreenClick() throws Exception{
		try {
			btn_addemailtemplate.click();
			Thread.sleep(3000);
			if(lbl_createemailtemplate.isDisplayed())
			{
				System.out.println("Create Email Template screen is displayed");
				pg.softAssertEquals(true, true,"Create Email Template screen is displayed");
			}
			else {
				pg.softAssertEquals(false, true,"Create Email Template screen is not displayed");
				System.out.println("Create Email Template screen is not displayed");
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Create Email Template screen is not displayed - "+e);
			System.out.println("Create Email Template screen is not displayed - "+e);
		}
	}
	
	
	public void addEmailTemplate(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			
			txtbx_templatename.sendKeys(dataMap.get("EmailTemplateName"));
			combo_emailtype.sendKeys(dataMap.get("EmailType"));
			combo_emailtype.sendKeys(Keys.ENTER);
			
			txtbx_subject.sendKeys(dataMap.get("Subject"));
			txtarea_emailbody.sendKeys(dataMap.get("EmailBody"));
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Email-Template created successfully']")).getText();
			//Email-Template created successfully
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for Email Template creation - "+toastMsg);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void search(Map<String, String> dataMap) {
		try {
			Thread.sleep(1000);
			txtbx_search.clear();
			txtbx_search.sendKeys(dataMap.get("SearchValue"));
			
			Thread.sleep(500);
			if(driver.findElement(By.xpath("//table//tbody//tr//td[@tabindex='1']")).isDisplayed())
			{
				pg.softAssertEquals(true, true, "Search in the list page is successful");
			}
			else {
				pg.softAssertEquals(false, true, "No results found");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void editEmailTemplate(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(1000);
			icon_edit.click();
			Thread.sleep(500);
			String subject = txtbx_subject.getAttribute("value");
			
			//txtbx_subject.clear();
			txtbx_subject.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			txtbx_subject.sendKeys(dataMap.get("Subject"));
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_update);
			//btn_update.click();
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Email-Template updated successfully']")).getText();
			if(toastMsg!=null) {
				//Email-Template updated successfully
				pg.softAssertEquals(true, true, "Updated Subject: "+dataMap.get("Subject")+", Previous value: "+subject+" -> "+toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for edit email template");
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void verifyEditedColumn(Map<String, String> dataMap) throws Exception{
		try {
			String subject = driver.findElement(By.xpath("//table//tbody//tr//td[@tabindex='3']")).getText();
			if(subject.equals(dataMap.get("Subject")))
			{
				pg.softAssertEquals(true, true, "Edited details - Actual:"+subject+", Expected:"+dataMap.get("Subject"));
			}
			else {
				pg.softAssertEquals(false, true, "Edited details - Actual:"+subject+", Expected:"+dataMap.get("Subject"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteEmailTemplate(Map<String, String> dataMap) throws Exception{
		//String toastMsg = "";
		try {
			Thread.sleep(500);
			icon_delete.click();
			Thread.sleep(500);
			if(btn_yesdelete.isDisplayed()) {
				btn_yesdelete.click();
				Thread.sleep(500);
				String toastMsg = lbl_recorddeleted.getText();
				if(toastMsg.equals("Record Deleted!")) {
					btn_ok.click();
					pg.softAssertEquals(true, true, "Email Template: " +toastMsg);
					Thread.sleep(5000);
				}
				else {
					pg.softAssertEquals(false, true, "No message displayed for Delete email template");
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void searchAfterDelete(Map<String, String> dataMap) {
		try {
			Thread.sleep(500);
			txtbx_search.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			//txtbx_search.clear();
			txtbx_search.sendKeys(dataMap.get("SearchValue"));
			Thread.sleep(2000);
			
			if(driver.findElement(By.xpath("//tr//td[text()='There is no data to display']")).isDisplayed())
			{
				System.out.println("After delete the record is not displayed in the list page as expected");
				pg.softAssertEquals(true, true, "After delete the record is not displayed in the list page as expected");
			}
			else {
				pg.softAssertEquals(false, true, "The search record is displayed, which means delete is not happened correctly in previous step");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
