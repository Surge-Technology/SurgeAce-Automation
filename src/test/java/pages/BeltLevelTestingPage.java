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

public class BeltLevelTestingPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	JavascriptExecutor executor = null;
	Util util = new Util();
	
	public BeltLevelTestingPage(WebDriver driver) throws Exception{
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
	
	@FindBy(xpath=("//button[contains(text(),'Promote') and @disabled]"))
	public WebElement btn_promotedisabled;
	
	@FindBy(xpath=("//button[contains(text(),'Promote')]"))
	public WebElement btn_promoteenabled;
	
	@FindBy(xpath=("//td//input[@type='checkbox']"))
	public WebElement chkbx_student;
	
	@FindBy(xpath=("//td//input[@type='checkbox' and @checked]"))
	public WebElement chkbx_studentchecked;
	
	// ***********Search object***********************
	
	@FindBy(xpath=("//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
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
	
	public void search(Map<String, String> dataMap) {
		try {
			Thread.sleep(2000);
			txtbx_search.clear();
			txtbx_search.sendKeys(StudentsPage.studentFirstName);
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("//table//tbody//tr//td[@tabindex='2']")).isDisplayed())
			{
				pg.softAssertEquals(true, true, "Search in the list page of Student to promote is successful");
			}
			else {
				pg.softAssertEquals(false, true, "No results found");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void promoteEnableClick() throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(2000);
			chkbx_student.click();
			Thread.sleep(1000);
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_promoteenabled);
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'Successfully')]")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Student promoted to belt level - "+toastMsg);
				Thread.sleep(5000);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for Promote - "+toastMsg);
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,""+e);
			System.out.println(""+e);
		}
	}
	
	
	public void searchAfterPromote(Map<String, String> dataMap) throws Exception{
		try {
			Thread.sleep(1000);
			txtbx_search.clear();
			txtbx_search.sendKeys(StudentsPage.studentFirstName);
			Thread.sleep(500);
			if(driver.findElement(By.xpath("//tr//td[text()='There is no data to display']")).isDisplayed())
			{
				pg.softAssertEquals(true, true, "Student promoted is not displayed in the Belt Testing screen as expected");
			}
			else {
				pg.softAssertEquals(false, true, "Student promoted is still displayed in the Belt Testing screen");
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
