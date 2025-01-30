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

public class ContractPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	JavascriptExecutor executor = null;
	Util util = new Util();
	public static String contractName="";
	
	public ContractPage(WebDriver driver) throws Exception{
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
	
	@FindBy(xpath=("//button[contains(text(),'Add Contract')]"))
	public WebElement btn_addcontract;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Create Contract')]"))
	public WebElement lbl_createcontract;
	
	@FindBy(xpath=("//input[@placeholder='Contract name']"))
	public WebElement txtbx_contractname;
	
	@FindBy(xpath=("//input[@name='pricing']"))
	public WebElement txtbx_baseprice;
	
	@FindBy(xpath=("//label[contains(text(),'Duration')]//..//..//input[@role='combobox']"))
	public WebElement combo_duration;
	
	//****************Add more *************************
	@FindBy(xpath=("//input[@placeholder='Member(s)']"))
	public WebElement txtbx_members;
	
	@FindBy(xpath=("//label[contains(text(),'Frequency')]//..//..//input[@role='combobox']"))
	public WebElement combo_frequency;	
	
	@FindBy(xpath=("//label[contains(text(),'Addt')]//..//input"))
	public WebElement txtbx_addfee;
	
	@FindBy(xpath=("//label[contains(text(),'Discount')]//..//input"))
	public WebElement txtbx_discount;
	
	@FindBy(xpath=("//label[contains(text(),'Total')]//..//input"))
	public WebElement lbl_total;
	//*****************************************************
	@FindBy(xpath=("//i[@id='plusicon']"))
	public WebElement icon_plus;
	
	@FindBy(xpath=("//i[@id='minusicon']"))
	public WebElement icon_minus;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Save']"))
	public WebElement btn_save;
	
	// ***********Search object***********************
	
	@FindBy(xpath=("//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
	// ***********Edit objects***********************
	
	@FindBy(xpath=("//span//i[@id='pencilspace']"))
	public WebElement icon_edit;
	
	//************** Status objects *****************
	
	@FindBy(xpath=("//div[@class='react-switch-bg']//..//input[@aria-checked='true']"))
	public WebElement toggle_statustrue;
	
	@FindBy(xpath=("//div[@class='react-switch-bg']//..//input[@aria-checked='false']"))
	public WebElement toggle_statusfalse;
	
	@FindBy(xpath=("//div[@class='react-switch-bg']//..//input"))
	public WebElement toggle_status;
	
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
	
	//Registered successfully
	//Contract updated successfully
	//i[@id='plusicon']
	
	public void addScreenClick() throws Exception{
		try {
			btn_addcontract.click();
			Thread.sleep(3000);
			if(lbl_createcontract.isDisplayed())
			{
				System.out.println("Create Contract screen is displayed");
				pg.softAssertEquals(true, true,"Create Contract screen is displayed");
			}
			else {
				pg.softAssertEquals(false, true,"Create Contract screen is not displayed");
				System.out.println("Create Contract screen is not displayed");
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Create Contract screen is not displayed - "+e);
			System.out.println("Create Contract screen is not displayed - "+e);
		}
	}
	
	
	public void addContract(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			contractName = dataMap.get("ContractName")+"_"+util.dateTimeSDF();
			txtbx_contractname.sendKeys(contractName);
			txtbx_baseprice.sendKeys(dataMap.get("BasePrice"));
			
			combo_duration.sendKeys(dataMap.get("Duration"));
			combo_duration.sendKeys(Keys.ENTER);
			
			txtbx_members.sendKeys(dataMap.get("Member"));
			combo_frequency.sendKeys(dataMap.get("Frequency"));
			combo_frequency.sendKeys(Keys.ENTER);
			
			
			txtbx_addfee.sendKeys(dataMap.get("AdditionalFee"));
			txtbx_discount.sendKeys(dataMap.get("Discount"));
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Contract created successfully']")).getText();
			////div[@class='Toastify__toast-body']//div[contains(text(),'successfully')]
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed - "+toastMsg);
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
			txtbx_search.sendKeys(contractName);
			//txtbx_search.sendKeys(dataMap.get("SearchValue"));
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
	public void editContract(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(1000);
			icon_edit.click();
			Thread.sleep(500);
			String basePrice = txtbx_baseprice.getAttribute("value");
			txtbx_baseprice.click();
			Thread.sleep(2000);
			//txtbx_baseprice.clear();
			txtbx_baseprice.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			Thread.sleep(1000);
			txtbx_baseprice.sendKeys(dataMap.get("BasePrice"));
			Thread.sleep(500);
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			//btn_Save.click();
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Contract updated successfully']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Updated Base Price:"+dataMap.get("BasePrice")+", Previous value:"+basePrice+" -> "+toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for edit inquiry");
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void verifyEditedColumn(Map<String, String> dataMap) throws Exception{
		try {
			String basePrice = driver.findElement(By.xpath("//table//tbody//tr//td[@tabindex='4']")).getText();
			if(basePrice.equals(dataMap.get("BasePrice")))
			{
				pg.softAssertEquals(true, true, "Edited details - Actual:"+basePrice+", Expected:"+dataMap.get("BasePrice"));
			}
			else {
				pg.softAssertEquals(false, true, "Edited details - Actual:"+basePrice+", Expected:"+dataMap.get("BasePrice"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void statusUpdateContract(Map<String, String> dataMap) throws Exception{
		try {
			String toastMsg="";
			Thread.sleep(500);
			//if(toggle_statustrue.isDisplayed() || toggle_statusfalse.isDisplayed()) {
			if(toggle_status.isDisplayed()) {
				//toggle_status.click();
				executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", toggle_status);
				Thread.sleep(1000);
				toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Contract updated successfully']")).getText();
				if(toastMsg!=null) {
					pg.softAssertEquals(true, true, toastMsg);
				}
				else {
					pg.softAssertEquals(false, true, "No toaster message displayed after Status Update - "+toastMsg);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, "Toggle enable or disable is not displayed to change the Status");
		}
	}
	public void deleteContract(Map<String, String> dataMap) throws Exception{
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
					pg.softAssertEquals(true, true, "Contract: " +toastMsg);
					Thread.sleep(5000);
				}
				else {
					pg.softAssertEquals(false, true, "No message displayed for Delete contract");
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
			txtbx_search.sendKeys(contractName);
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
