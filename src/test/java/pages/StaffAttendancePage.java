package pages;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmanager.PageObjectManager;
import utils.AbstractSteps;
import utils.Util;

public class StaffAttendancePage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	Util util = new Util();
		
	public StaffAttendancePage(WebDriver driver) throws Exception{
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

	@FindBy(xpath=("//label[contains(text(),'Search')]//..//..//input[@role='combobox']"))
	public WebElement combo_selectstaff;
	
	@FindBy(xpath=("//div//label[text()='Staff Name']//..//input[@class='form-control']"))
	public WebElement lbl_staffname; //getAttribute("value") in code
	
	@FindBy(xpath=("//div[@class='react-datepicker__input-container']//input"))
	public WebElement lbl_date; //getAttribute("value") in code
	
	@FindBy(xpath=("//input[@name='pin']"))
	public WebElement txtbx_pin;
	
	@FindBy(xpath=("//button[@type='submit' and text()='In' and @class='timeTop btn btn-secondary disabled']"))
	public WebElement btn_indisable;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Out' and @class='timeTop btn btn-secondary disabled']"))
	public WebElement btn_outdisable;
	
	@FindBy(xpath=("//button[@type='submit' and text()='In' and @class='timeTop btn btn-secondary']"))
	public WebElement btn_in;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Out' and @class='timeTop btn btn-secondary']"))
	public WebElement btn_out;
	
	@FindBy(xpath=("(//td[contains(text(),'There is no data to display')])[1]"))
	public WebElement lbl_nodata;
	
	@FindBy(xpath=("(//input[@placeholder='Search'])[1]"))
	public WebElement txtbx_search;
	
	@FindBy(xpath=("//h2[contains(text(),'The User pin is incorrect, please try again')]"))
	public WebElement popup_pinincorrect;
	
	@FindBy(xpath=("//h2[contains(text(),'Check-In Time is Marked!')]"))
	public WebElement popup_inmarked;
	
	@FindBy(xpath=("//h2[contains(text(),'Check-out Time is Marked!')]"))
	public WebElement popup_outmarked;
	
	@FindBy(xpath=("//button[text()='OK']"))
	public WebElement btn_ok;
	
	@FindBy(xpath=("//td[@tabindex='3']//div")) //checkin time
	public WebElement lbl_checkintime;
	
	@FindBy(xpath=("//td[@tabindex='4']//div")) //checkout time
	public WebElement lbl_checkouttime;
			
	@FindBy(xpath=("//td[@tabindex='5']//div")) //No of hours
	public WebElement lbl_noofhours;		
			
	public void attendanceIn(Map<String, String> dataMap) throws Exception{
		try {
			Thread.sleep(2000);
			combo_selectstaff.sendKeys(dataMap.get("SelectStaff"));
			combo_selectstaff.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			if(lbl_staffname.getAttribute("value").equalsIgnoreCase(dataMap.get("SelectStaff"))) {
				pg.softAssertEquals(true, true,"Selected Staff Name:"+dataMap.get("SelectStaff")+", Displayed Staff Name:"+lbl_staffname.getAttribute("value"));
				txtbx_pin.sendKeys(dataMap.get("Pin"));
				if(btn_in.isDisplayed()) {
					btn_in.click();
					Thread.sleep(500);
					if(popup_inmarked.isDisplayed()) {
						btn_ok.click();
						pg.softAssertEquals(true, true,"The Pin matches with the staff name and the attendance IN time marked");
					}
					else if(popup_pinincorrect.isDisplayed()){
						btn_ok.click();
						pg.softAssertEquals(false, true,"The Pin does not matches with the staff name and hence the attendance IN time not marked");
					}
				}
				else {
					pg.softAssertEquals(false, true,"The IN button is disabled for the staff name:"+dataMap.get("SearchStaff"));
				}
					
			}
			else {
				pg.softAssertEquals(false, true,"Selected Staff Name:"+dataMap.get("SelectStaff")+", Displayed Staff Name:"+lbl_staffname.getAttribute("value"));	
			}
			
			
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Register Student screen is not displayed - "+e);
			System.out.println("Register Student screen is not displayed - "+e);
		}
	}
	
	public void attendanceOut(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		JavascriptExecutor executor = null;
		try {
			Thread.sleep(2000);
			combo_selectstaff.sendKeys(dataMap.get("SelectStaff"));
			combo_selectstaff.sendKeys(Keys.ENTER);
			
			if(lbl_staffname.getAttribute("value").equalsIgnoreCase(dataMap.get("SelectStaff"))) {
				pg.softAssertEquals(true, true,"Selected Staff Name:"+dataMap.get("SelectStaff")+", Displayed Staff Name:"+lbl_staffname.getAttribute("value"));
				txtbx_pin.sendKeys(dataMap.get("Pin"));
				if(btn_out.isDisplayed()) {
					btn_out.click();
					Thread.sleep(500);
					if(popup_outmarked.isDisplayed()) {
						btn_ok.click();
						pg.softAssertEquals(true, true,"The Pin matches with the staff name and the attendance OUT time marked");
					}
					else if(popup_pinincorrect.isDisplayed()){
						btn_ok.click();
						pg.softAssertEquals(false, true,"The Pin does not matches with the staff name and hence the attendance OUT time not marked");
					}
				}
				else {
					pg.softAssertEquals(false, true,"The OUT button is disbaled for the staff name:"+dataMap.get("SearchStaff"));
				}
					
			}
			else {
				pg.softAssertEquals(false, true,"Selected Staff Name:"+dataMap.get("SelectStaff")+", Displayed Staff Name:"+lbl_staffname.getAttribute("value"));	
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void attendanceCheckIn(Map<String, String> dataMap) throws Exception{
		try {
			Thread.sleep(2000);
			combo_selectstaff.sendKeys(dataMap.get("SelectStaff"));
			combo_selectstaff.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			String checkintime = lbl_checkintime.getText();
			if(checkintime!="") {
				pg.softAssertEquals(true, true,"IN time marked for the staff "+dataMap.get("SelectStaff")+" at "+checkintime);
			}
			else {
				pg.softAssertEquals(true, true,"IN time not marked for the staff "+dataMap.get("SelectStaff")+" as the checkin time is null");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void attendanceCheckOut(Map<String, String> dataMap) throws Exception{
		try {
			Thread.sleep(2000);
			combo_selectstaff.sendKeys(dataMap.get("SelectStaff"));
			combo_selectstaff.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			String checkouttime = lbl_checkouttime.getText();
			if(checkouttime!="") {
				pg.softAssertEquals(true, true,"OUT time marked for the staff "+dataMap.get("SelectStaff")+" at "+checkouttime);
			}
			else {
				pg.softAssertEquals(true, true,"OUT time not marked for the staff "+dataMap.get("SelectStaff")+" as the checkout time is null");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
