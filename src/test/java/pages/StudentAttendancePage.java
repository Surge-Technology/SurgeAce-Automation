package pages;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmanager.PageObjectManager;
import utils.AbstractSteps;
import utils.Util;

public class StudentAttendancePage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	Util util = new Util();
	StudentsPage stuPage = new StudentsPage(driver);
	
	public StudentAttendancePage(WebDriver driver) throws Exception{
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

	@FindBy(xpath=("//input[@name='currentDate']"))
	public WebElement lbl_currentdate;
	
	@FindBy(xpath=("//label[contains(text(),'Class')]//..//..//input[@role='combobox']"))
	public WebElement combo_selectclass;
	
	@FindBy(xpath=("//label[contains(text(),'Time')]//..//..//input[@role='combobox']"))
	public WebElement combo_selecttime;
	
	@FindBy(xpath=("//th//input[@type='checkbox']"))
	public WebElement chkbx_overallcheck;
	
	@FindBy(xpath=("//i[@id='trashspace']"))
	public WebElement icon_delete;
	
	@FindBy(xpath=("//button[text()='Mark as Attend']"))
	public WebElement btn_markasattend;
	
	@FindBy(xpath=("//strong//..//..//..//..//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
	@FindBy(xpath=("//tr//td[@tabindex='1']//input[@type='checkbox']"))
	public WebElement chkbx_singlecheck;
		
	public void studentAttendanceMark(Map<String, String> dataMap) throws Exception{
		try {
			String toastMsg = "";
			Thread.sleep(2000);
			
			combo_selectclass.sendKeys(dataMap.get("SelectClass"));
			combo_selectclass.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selecttime.sendKeys(dataMap.get("SelectTime"));
			combo_selecttime.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			chkbx_overallcheck.click();
			Thread.sleep(500);
			btn_markasattend.click();
			//Attendence done successfully
			Thread.sleep(2000);
			toastMsg = driver.findElement(By.xpath("//div[@class='tab-pane active']//div[@class='Toastify__toast-body']//div[text()='Attendence done successfully']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Students attendance marked - "+toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed - "+toastMsg);
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true, "Mark Student Exception: "+ e);
			System.out.println("Mark Student Exception: "+ e);
		}
	}
	
	public void studentAttendanceUnmark(Map<String, String> dataMap) throws Exception{
		try {
			String toastMsg = "";
			Thread.sleep(4000);
			combo_selectclass.sendKeys(dataMap.get("SelectClass"));
			combo_selectclass.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selecttime.sendKeys(dataMap.get("SelectTime"));
			combo_selecttime.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			chkbx_overallcheck.click();
			Thread.sleep(500);
			btn_markasattend.click();
			//Attendence done successfully
			Thread.sleep(2000);
			toastMsg = driver.findElement(By.xpath("//div[@class='tab-pane active']//div[@class='Toastify__toast-body']//div[text()='Attendence done successfully']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Students attendance unmarked - "+toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed - "+toastMsg);
			}
		}
		catch(Exception e) {
		pg.softAssertEquals(false, true, "Unmark Student Exception: "+ e);
		System.out.println("Unmark Student Exception: "+ e);
		}
	}

	public void studentAttendanceDelete(Map<String, String> dataMap) throws Exception{
		try {
			String toastMsg = "";
			Thread.sleep(5000);
			
			//********** single check *************************
			combo_selectclass.sendKeys(dataMap.get("SelectClass"));
			combo_selectclass.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selecttime.sendKeys(dataMap.get("SelectTime"));
			combo_selecttime.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			txtbx_search.clear();
			txtbx_search.sendKeys(StudentsPage.studentFirstName);
			chkbx_singlecheck.click();
			
			Thread.sleep(500);
			btn_markasattend.click();
			Thread.sleep(2000);
			toastMsg = driver.findElement(By.xpath("//div[@class='tab-pane active']//div[@class='Toastify__toast-body']//div[text()='Attendence done successfully']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Student's attendance marked for - "+StudentsPage.studentFirstName+" : "+toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed - "+toastMsg);
			}
			
			//********** single delete *************************
			combo_selectclass.sendKeys(dataMap.get("SelectClass"));
			combo_selectclass.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selecttime.sendKeys(dataMap.get("SelectTime"));
			combo_selecttime.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			txtbx_search.clear();
			txtbx_search.sendKeys(StudentsPage.studentFirstName);
			Thread.sleep(500);
			
			if(icon_delete.isDisplayed()) {
				icon_delete.click();
				Thread.sleep(2000);
				toastMsg = driver.findElement(By.xpath("//div[@class='tab-pane active']//div[@class='Toastify__toast-body']//div[text()='Attendence deleted successfully']")).getText();
				if(toastMsg!=null) {
					pg.softAssertEquals(true, true, "Student's attendance deleted using delete icon - "+toastMsg);
					Thread.sleep(5000);
				}
				else {
					pg.softAssertEquals(false, true, "No toaster message displayed after attendance delete - "+toastMsg);
				}
			}
			
			//**********************verify if unchecked correct after delete****************************************
			combo_selectclass.sendKeys(dataMap.get("SelectClass"));
			combo_selectclass.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selecttime.sendKeys(dataMap.get("SelectTime"));
			combo_selecttime.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			txtbx_search.clear();
			txtbx_search.sendKeys(StudentsPage.studentFirstName);
			Thread.sleep(500);
			
			List<WebElement> elem = driver.findElements(By.xpath("//input[@type='checkbox' and @checked]"));
			if(elem.size()== 0) { //elem==null
				pg.softAssertEquals(true, true, "Student's attendance checkbox is unchecked after delete");
				Thread.sleep(5000);
			}
			else {
				pg.softAssertEquals(false, true, "Student's attendance checkbox is checked even after delete");
			}	
			
			
		}
		catch(Exception e) {
			pg.softAssertEquals(false, true, "There is no Student rows to delete in Student Attendance screen: "+ e);
			System.out.println("There is no Student rows to delete in Student Attendance screen: "+ e);
		}
	}
}
