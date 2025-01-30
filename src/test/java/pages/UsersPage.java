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

public class UsersPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	JavascriptExecutor executor = null;
	Util util = new Util();
	public static String login = "";
	public static String email = "";
	
	public UsersPage(WebDriver driver) throws Exception{
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
	
	@FindBy(xpath=("//button[contains(text(),'Add User')]"))
	public WebElement btn_adduser;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Create')]"))
	public WebElement lbl_createuser;
	
	@FindBy(xpath=("//input[@name='firstName']"))
	public WebElement txtbx_firstname;
	
	@FindBy(xpath=("//input[@name='lastName']"))
	public WebElement txtbx_lastname;
	
	@FindBy(xpath=("//input[@name='dateOfBirth']"))
	public WebElement txtbx_dateofbirth;
	
	@FindBy(xpath=("//input[@name='phoneNumber']"))
	public WebElement txtbx_phone;
	
	@FindBy(xpath=("//input[@name='email']"))
	public WebElement txtbx_email;
	
	@FindBy(xpath=("//label[contains(text(),'User Type')]//..//input[@role='combobox']"))
	public WebElement combo_usertype;
	
	@FindBy(xpath=("//label[contains(text(),'Employment Type')]//..//input[@role='combobox']"))
	public WebElement combo_employmenttype;
	
	@FindBy(xpath=("//input[@name='login']"))
	public WebElement txtbx_login;
	
	@FindBy(xpath=("//input[@name='password' and @type='password']"))
	public WebElement txtbx_password;
	
	@FindBy(xpath=("//input[@name='address']"))
	public WebElement txtbx_address;
	
	@FindBy(xpath=("//input[@name='address2']"))
	public WebElement txtbx_address2;
	
	@FindBy(xpath=("//input[@name='city']"))
	public WebElement txtbx_city;
	
	@FindBy(xpath=("//label[contains(text(),'State')]//..//input[@role='combobox']"))
	public WebElement combo_state;
	
	@FindBy(xpath=("//input[@name='zipcode']"))
	public WebElement txtbx_zipcode;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Save']"))
	public WebElement btn_save;
	
	@FindBy(xpath=("//button[text()='Cancel']"))
	public WebElement btn_cancel;
	
	// ***********Search object***********************
	
	@FindBy(xpath=("(//input[@placeholder='Search'])[2]"))
	public WebElement txtbx_search;
	
	// ***********Edit objects***********************
	
	@FindBy(xpath=("//td[@tabindex='8']//span//i[@id='pencilspace']"))
	public WebElement icon_edit;
	
	@FindBy(xpath=("//button[text()='Update']"))
	public WebElement btn_update;
	
	
	
	//************************************************
	
		public void addScreenClick() throws Exception{
		try {
			btn_adduser.click();
			Thread.sleep(3000);
			if(lbl_createuser.isDisplayed())
			{
				System.out.println("Create User screen is displayed");
				pg.softAssertEquals(true, true,"Create User screen is displayed");
			}
			else {
				pg.softAssertEquals(false, true,"Create User screen is not displayed");
				System.out.println("Create User screen is not displayed");
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Create User screen is not displayed - "+e);
			System.out.println("Create User screen is not displayed - "+e);
		}
	}
	
	
	public void addUser(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(2000);
			txtbx_firstname.sendKeys(dataMap.get("FirstName"));
			txtbx_lastname.sendKeys(dataMap.get("LastName"));
			txtbx_dateofbirth.sendKeys(dataMap.get("DOB"));
			txtbx_phone.sendKeys(dataMap.get("Phone"));
			String randomchars = pg.generateRandomString(3);
			email = dataMap.get("Email")+"_"+randomchars;
			txtbx_email.sendKeys(email+"@gmail.com");
			combo_usertype.sendKeys(PermissionsPage.roleName); //dataMap.get("UserType")
			combo_usertype.sendKeys(Keys.ENTER);
			combo_employmenttype.sendKeys(dataMap.get("EmploymentType"));
			combo_employmenttype.sendKeys(Keys.ENTER);
			login = email;
			txtbx_login.sendKeys(login);
			String password = pageObjectManager.getUtil().decrypt(dataMap.get("Password"));
			txtbx_password.sendKeys(password);
			txtbx_address.sendKeys(dataMap.get("Address"));
			txtbx_address2.sendKeys(dataMap.get("Address2"));
			txtbx_city.sendKeys(dataMap.get("City"));
			combo_state.sendKeys(dataMap.get("State"));
			combo_state.sendKeys(Keys.ENTER);
			txtbx_zipcode.sendKeys(dataMap.get("Zipcode"));
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='User created successfully']")).getText();
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
			txtbx_search.sendKeys(email);
			//txtbx_search.sendKeys(dataMap.get("SearchValue"));
			Thread.sleep(500);
			if(driver.findElement(By.xpath("(//table//tbody//tr//td[@tabindex='5'])[2]")).isDisplayed())
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
	public void editUser(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(1000);
			icon_edit.click();
			Thread.sleep(500);
			
			combo_usertype.sendKeys(dataMap.get("UserType"));
			combo_usertype.sendKeys(Keys.ENTER);
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_update);
			//btn_update.click();
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='User updated successfully']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, ""+toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for edit user");
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void verifyEditedColumn(Map<String, String> dataMap) throws Exception{
		try {
			String userType = driver.findElement(By.xpath("(//table//tbody//tr//td[@tabindex='5'])[2]")).getText();
			if(userType.equals(dataMap.get("UserType")))
			{
				pg.softAssertEquals(true, true, "Edited details - Actual:"+userType+", Expected:"+dataMap.get("UserType"));
			}
			else {
				pg.softAssertEquals(false, true, "Edited details - Actual:"+userType+", Expected:"+dataMap.get("UserType"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
