package pages;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmanager.PageObjectManager;
import utils.AbstractSteps;
import utils.Util;

public class PermissionsPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	JavascriptExecutor executor = null;
	Util util = new Util();
	public static String roleName="";
	
	public PermissionsPage(WebDriver driver) throws Exception{
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
	
	@FindBy(xpath=("//button[contains(text(),'Add Role')]"))
	public WebElement btn_addrole;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Create Roles')]"))
	public WebElement lbl_createroles;
	
	@FindBy(xpath=("//input[@name='roleName']"))
	public WebElement txtbx_rolename;
	
	@FindBy(xpath=("//td[@tabindex='2']//div[@class='react-switch-bg']"))
	public WebElement toggle_view;
	
	@FindBy(xpath=("//td[@tabindex='3']//div[@class='react-switch-bg']"))
	public WebElement toggle_create;
	
	@FindBy(xpath=("//td[@tabindex='4']//div[@class='react-switch-bg']"))
	public WebElement toggle_update;
	
	@FindBy(xpath=("//td[@tabindex='5']//div[@class='react-switch-bg']"))
	public WebElement toggle_delete;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Save']"))
	public WebElement btn_save;
	
	@FindBy(xpath=("//button[text()='Cancel']"))
	public WebElement btn_cancel;
	
	//Role asas created successfully -- where asas is rolename
	//div[@class='Toastify__toast-body']//div[contains(text(),'created successfully')]
	//div[@class='Toastify__toast-body']//div[contains(text(),'Role "+roleName+"created successfully')]"
	
	// ***********Search object***********************
	
	@FindBy(xpath=("//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
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
			btn_addrole.click();
			Thread.sleep(3000);
			if(lbl_createroles.isDisplayed())
			{
				System.out.println("Create Role screen is displayed");
				pg.softAssertEquals(true, true,"Create Role screen is displayed");
			}
			else {
				pg.softAssertEquals(false, true,"Create Role screen is not displayed");
				System.out.println("Create Role screen is not displayed");
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Create Role screen is not displayed - "+e);
			System.out.println("Create Role screen is not displayed - "+e);
		}
	}
	
	
	public void addRole(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			String randomchars = pg.generateRandomString(3);
			roleName = dataMap.get("RoleName")+"_"+randomchars;
			txtbx_rolename.sendKeys(roleName);
			toggle_view.click();
			toggle_create.click();
			toggle_update.click();
			toggle_delete.click();
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'Role "+roleName+" created successfully')]")).getText();
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
			txtbx_search.sendKeys(roleName);
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
	public void editRole(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(1000);
			icon_edit.click();
			Thread.sleep(500);
			
			toggle_view.click();
			Thread.sleep(500);
			if(driver.findElement(By.xpath("//td[@tabindex='2']//input[@aria-checked='false']")).isDisplayed()) {
				executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", btn_save);
				Thread.sleep(1000);
				
				//Role asas  Updated successfully
				toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'Role "+roleName+"  Updated successfully')]")).getText();
				if(toastMsg!=null) {
					pg.softAssertEquals(true, true, "Toggle View all unchecked -> "+toastMsg);
				}
				else {
					pg.softAssertEquals(false, true, "No toaster message displayed for edit roles");
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
}
