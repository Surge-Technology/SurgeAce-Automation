package pages;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class BatchesPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	Util util = new Util();
	public static String newFilename="";
	public static String studentFirstName = "";
	
	public BatchesPage(WebDriver driver) throws Exception{
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
	
	@FindBy(xpath=("//button[contains(text(),'Add Batch')]"))
	public WebElement btn_addbatch;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Create')]"))
	public WebElement lbl_createbatch;
	
	@FindBy(xpath=("//input[@name='batchName']"))
	public WebElement txtbx_bname;
	
	@FindBy(xpath=("//label[contains(text(),'Coach')]//..//input[@role='combobox']"))
	public WebElement combo_coach;
	
	@FindBy(xpath=("//label[contains(text(),'Style')]//..//input[@role='combobox']"))
	public WebElement combo_style;
	
	@FindBy(xpath=("//label[contains(text(),'Mode')]//..//input[@role='combobox']"))
	public WebElement combo_mode;
	
	@FindBy(xpath=("//input[@name='startDateTime']"))
	public WebElement txtbx_startdate;	
	
	@FindBy(xpath=("//input[@name='endDateTime']"))
	public WebElement txtbx_enddate;
		
	@FindBy(xpath=("//label[contains(text(),'Start Time')]//..//input"))
	public WebElement txtbx_starttime;
	
	@FindBy(xpath=("//label[contains(text(),'End Time')]//..//input"))
	public WebElement txtbx_endtime;
	
	@FindBy(xpath=("//textarea[@name='description']"))
	public WebElement txtarea_description;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Save']"))
	public WebElement btn_save;
	
	@FindBy(xpath=("//button[@type='button' and text()='Cancel']"))
	public WebElement btn_cancel;
	
	// ************************ Search ************************************
	@FindBy(xpath=("//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
	//*********************** Delete *******************************************
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
			btn_addbatch.click();
			Thread.sleep(3000);
			if(lbl_createbatch.isDisplayed())
			{
				System.out.println("Create Batch screen is displayed");
				pg.softAssertEquals(true, true,"Register Student screen is displayed");
			}
			else {
				pg.softAssertEquals(false, true,"Create Batch screen is not displayed");
				System.out.println("Create Batch screen is not displayed");
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Create Batch screen is not displayed - "+e);
			System.out.println("Create Batch screen is not displayed - "+e);
		}
	}
	
	public void addBatch(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		JavascriptExecutor executor = null;
		try {
			txtbx_bname.sendKeys(dataMap.get("BatchName"));
			combo_coach.sendKeys(dataMap.get("Coach"));
			combo_coach.sendKeys(Keys.ENTER);
			
			combo_style.sendKeys(dataMap.get("Style"));
			combo_style.sendKeys(Keys.ENTER);
			
			combo_mode.sendKeys(dataMap.get("Mode"));
			combo_mode.sendKeys(Keys.ENTER);
			
			//txtbx_startdate.sendKeys(dataMap.get("StartDate"));
			//txtbx_enddate.sendKeys(dataMap.get("EndDate"));
			
			LocalDate today = LocalDate.now();
			LocalDate futureDate = today.plusDays(2);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	        String toDay = today.format(formatter);
	        String plusDays = futureDate.format(formatter);
	        
	        txtbx_startdate.sendKeys(toDay);
	        txtbx_startdate.sendKeys(Keys.ENTER);
	        Thread.sleep(500);
	        txtbx_enddate.sendKeys(plusDays);
	        txtbx_enddate.sendKeys(Keys.ENTER);
	        Thread.sleep(500);
	        
			txtbx_starttime.click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__time-box']//ul//li[contains(text(),'"+dataMap.get("StartTime")+"')]")).click();
			Thread.sleep(500);
			txtbx_endtime.click();
			driver.findElement(By.xpath("//div[@class='react-datepicker__time-box']//ul//li[contains(text(),'"+dataMap.get("EndTime")+"')]")).click();
			Thread.sleep(500);
			txtarea_description.sendKeys(dataMap.get("Description"));
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save); 
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Batch created successfully']")).getText();
			//Batch created successfully
			System.out.println(toastMsg);
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed - "+toastMsg);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
			//pg.softAssertEquals(false, true, ""+e);
			//Batch name already exists with name : Batch Test
			//h2[contains(text(),'Batch name already exists with name :')]
			//button[text()='OK']
		}
	}
	
	public void search(Map<String, String> dataMap) {
		try {
			//Thread.sleep(3000);
			pg.waitTimeString(config.getProperty("SLEEP_TIME"),txtbx_search.toString());
			txtbx_search.clear();
			txtbx_search.sendKeys(dataMap.get("SearchValue"));
			Thread.sleep(1000);
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
	
	
	public void deleteBatch(Map<String, String> dataMap) throws Exception{
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
					pg.softAssertEquals(true, true, "Batch: " +toastMsg);
					Thread.sleep(5000);
				}
				else {
					pg.softAssertEquals(false, true, "No message displayed for Delete batch");
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/*public void searchAfterDelete(Map<String, String> dataMap) {
		try {
			Thread.sleep(500);
			txtbx_search.clear();
			txtbx_search.sendKeys(dataMap.get("SearchValue"));
			
			if(!driver.findElement(By.xpath("//table//tbody//tr//td[@tabindex='1']")).isDisplayed())
			{
				pg.softAssertEquals(true, true, "After delete the record is not displayed in the list page as expected");
			}
			else {
				pg.softAssertEquals(false, true, "The search record is displayed, which means delete is not happened correctly in previous step");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}*/
	public void searchAfterDeleteWithExactValue(Map<String, String> dataMap) {
		try {
			Thread.sleep(500);
			txtbx_search.clear();
			txtbx_search.sendKeys(dataMap.get("SearchValue"));
			
			String batchName = driver.findElement(By.xpath("//table//tbody//tr//td[@tabindex='2']")).getText();
			
			if(!batchName.equals(dataMap.get("SearchValue")))
			{
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
