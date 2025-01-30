package pages;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
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

public class StudentAttendancePageOld extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	Util util = new Util();
		
	public StudentAttendancePageOld(WebDriver driver) throws Exception{
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

	@FindBy(xpath=("//label[contains(text(),'Style')]//..//..//input[@role='combobox']"))
	public WebElement combo_selectstyle;
	
	@FindBy(xpath=("//label[contains(text(),'Master')]//..//..//input[@role='combobox']"))
	public WebElement combo_selectmaster;
	
	@FindBy(xpath=("//label[contains(text(),'Class')]//..//..//input[@role='combobox']"))
	public WebElement combo_selectclass;
	
	@FindBy(xpath=("//th//input[@type='checkbox']"))
	public WebElement chkbx_overallcheck;
	
	@FindBy(xpath=("//i[@id='trashspace']"))
	public WebElement icon_delete;
	
	@FindBy(xpath=("//button[text()='Mark as Attend']"))
	public WebElement btn_markasattend;
	
		
	public void studentAttendanceMark(Map<String, String> dataMap) throws Exception{
		try {
			String toastMsg = "";
			Thread.sleep(2000);
			combo_selectstyle.sendKeys(dataMap.get("SelectStyle"));
			combo_selectstyle.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selectmaster.sendKeys(dataMap.get("SelectMaster"));
			combo_selectmaster.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selectclass.sendKeys(dataMap.get("SelectClass"));
			combo_selectclass.sendKeys(Keys.ENTER);
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
			Thread.sleep(2000);
			combo_selectstyle.sendKeys(dataMap.get("SelectStyle"));
			combo_selectstyle.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selectmaster.sendKeys(dataMap.get("SelectMaster"));
			combo_selectmaster.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selectclass.sendKeys(dataMap.get("SelectClass"));
			combo_selectclass.sendKeys(Keys.ENTER);
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
	
	//work on this on 8Jan2025
	public void studentAttendanceDelete(Map<String, String> dataMap) throws Exception{
		try {
			String toastMsg = "";
			Thread.sleep(2000);
			combo_selectstyle.sendKeys(dataMap.get("SelectStyle"));
			combo_selectstyle.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selectmaster.sendKeys(dataMap.get("SelectMaster"));
			combo_selectmaster.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			combo_selectclass.sendKeys(dataMap.get("SelectClass"));
			combo_selectclass.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			// in future pass the name of the student created as a static variable here to select and delete attendance and check.
			//for now use the first row in the grid to delete attendance.
			if(icon_delete.isDisplayed()) {
				icon_delete.click();
				//Attendence done successfully
				Thread.sleep(2000);
				toastMsg = driver.findElement(By.xpath("//div[@class='tab-pane active']//div[@class='Toastify__toast-body']//div[text()='Attendence deleted successfully']")).getText();
				if(toastMsg!=null) {
					pg.softAssertEquals(true, true, "Students attendance deleted using delete icon - "+toastMsg);
					//check with balaji what has to be validated after delete
					Thread.sleep(5000);
				}
				else {
					pg.softAssertEquals(false, true, "No toaster message displayed - "+toastMsg);
				}
			}
		}
		catch(Exception e) {
			pg.softAssertEquals(false, true, "There is no Student rows to delete in Student Attendance screen: "+ e);
			System.out.println("There is no Student rows to delete in Student Attendance screen: "+ e);
		}
	}
}
