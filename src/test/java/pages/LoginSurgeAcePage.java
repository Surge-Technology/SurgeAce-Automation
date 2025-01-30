package pages;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjectmanager.PageObjectManager;
import utils.AbstractSteps;


public class LoginSurgeAcePage extends AbstractSteps{
	
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	   
	public LoginSurgeAcePage(WebDriver driver) throws Exception{
		//this.driver = getDriver();
		this.driver= driver;
		//PageFactory.initElements(getDriver(), this);
		PageFactory.initElements(driver, this);
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\SurgeAce_Regression.properties");
		config.load(fis);
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	protected Properties getConfig() {
	        return config;
	}

	@FindBy(xpath=("//input[@name='email']"))
	public WebElement txtbx_uname;
	
	@FindBy(xpath=("//input[@name='password']"))
	public WebElement txtbx_pwd;
	
	@FindBy(xpath=("//button[@class='button-login' and text()='Login']"))
	public WebElement btn_submit;
	
	@FindBy(xpath=("//div[@class='logo']"))
	public WebElement img_logo;
	
	@FindBy(xpath=("//a[@title='Students']"))
	public WebElement menu_students;
	
	@FindBy(xpath=("//a[@title='Attendence']"))
	public WebElement menu_attendance;
	
	@FindBy(xpath=("//a[@title='Contracts']"))
	public WebElement menu_contracts;
	
	@FindBy(xpath=("//a[@title='Certification']"))
	public WebElement menu_certification;
	
	@FindBy(xpath=("//a[@title='Batches']"))
	public WebElement menu_batches;
	
	@FindBy(xpath=("//a[@title='Events']"))
	public WebElement menu_events;
	
	@FindBy(xpath=("//a[@title='Users Management']"))
	public WebElement menu_usersmgmt;
	
	@FindBy(xpath=("//a[@title='Email Templates']"))
	public WebElement menu_emailtemplates;
	
	@FindBy(xpath=("//a[@title='Level Testing']"))
	public WebElement menu_leveltesting;
	
	@FindBy(xpath=("//a[@title='Ranks']"))
	public WebElement menu_ranks;
			
	@FindBy(xpath=("//h5//strong[contains(text(),'Inquiry')]"))
	public WebElement tab_inquiry;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Inquiry List')]"))
	public WebElement lbl_inquirylist;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Students')]"))
	public WebElement tab_students;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Staff Attendance')]"))
	public WebElement tab_staffattendance;
	
	@FindBy(xpath=("//label[text()='Staff Name']"))
	public WebElement lbl_staffname;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Students List')]"))
	public WebElement lbl_studentslist;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Student Attendance')]"))
	public WebElement tab_studentattendance;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Permissions')]"))
	public WebElement tab_permissions;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Users')]"))
	public WebElement tab_users;
		
	@FindBy(xpath=("//h4//strong[contains(text(),'Contracts')]"))
	public WebElement lbl_contracts;
	
	@FindBy(xpath=("//label[text()='Class']"))
	public WebElement lbl_class;
		
	@FindBy(xpath=("//h4//strong[contains(text(),'Certifications')]"))
	public WebElement lbl_certifications;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Batches')]"))
	public WebElement lbl_batches;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Events')]"))
	public WebElement lbl_events;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Roles Permissions')]"))
	public WebElement lbl_rolepermissions;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Users List')]"))
	public WebElement lbl_userlist;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Email Templates')]"))
	public WebElement lbl_emailtemplates;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Belt Testing')]"))
	public WebElement lbl_belttesting;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Rank Sets')]"))
	public WebElement lbl_ranksets;
	
	@FindBy(xpath=("//div[@class='avatar avatar-md']//img"))
	public WebElement img_profile;
	
	@FindBy(xpath=("//a[text()='Log Out']"))
	public WebElement btn_logout;
	
	/*@FindBy(xpath=("//img[@src='assets/images/chargeback-setup.svg']"))
	public WebElement menu_chargebacksetupicon;
	
	@FindBy(xpath=("//span[text()='Chargeback Setup'] | //img[@src='assets/images/chargeback-setup.svg']"))
	public WebElement menu_chargebacksetup;
		
	@FindBy(xpath=("//span[text()='Case Management'] | //img[@src='assets/images/case-management.svg']"))
	public WebElement menu_casemgmt;
	*/
	
	public void loginAssert(Map<String, String> dataMap) throws Exception {
		try {
			String toastMsg = "";
			txtbx_uname.sendKeys(dataMap.get("Username"));
			String password = pageObjectManager.getUtil().decrypt(dataMap.get("Password"));
			txtbx_pwd.sendKeys(password);
			btn_submit.click();
			//Thread.sleep(2000);
			//pg.waitTimeString(config.getProperty("SLEEP_TIME"), driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'successful')]")).toString());
			/*toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'successful')]")).getText();
		
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Entered username and password and clicked on Submit - "+toastMsg);
			}
			else {
				pg.softAssertEquals(false, true,"Login not successful - " + toastMsg);
			}*/
			if(img_logo.isDisplayed()) {
				System.out.println("Login successful");
				pg.softAssertEquals(true, true,"Login successful");
			}
			else {
				System.out.println("Login not successful");
				pg.softAssertEqualsWhileConitnue(false, true,"Login not successful");
			}
			
		}
		catch(Exception e) {
			System.out.println("Login not successful");
			pg.softAssertEqualsWhileConitnue(false, true,"Login not successful");

		}
	}
	public void login(Map<String, String> dataMap) throws Exception {
		try {
			String toastMsg = "";
			txtbx_uname.sendKeys(dataMap.get("Username"));
			String password = pageObjectManager.getUtil().decrypt(dataMap.get("Password"));
			txtbx_pwd.sendKeys(password);
			btn_submit.click();
			Thread.sleep(2000);
			//pg.waitTimeString(config.getProperty("SLEEP_TIME"), driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'successful')]")).toString());
			/*toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'successful')]")).getText();
		
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Entered username and password and clicked on Submit - "+toastMsg);
			}
			else {
				pg.softAssertEquals(false, true,"Login not successful - " + toastMsg);
			}*/
			if(img_logo.isDisplayed()) {
				System.out.println("Login successful");
				pg.softAssertEquals(true, true,"Login successful");
			}
			else {
				System.out.println("Login not successful");
				pg.softAssertEquals(false, true,"Login not successful");
			}
			
		}
		catch(Exception e) {
			System.out.println("Login not successful");
			pg.softAssertEquals(false, true,"Login not successful - " + e);

		}
	}
	
	public void homeScreenDisplay() throws Exception {
		try {
			String toastMsg = "";
			Thread.sleep(3000);
			pg.waitTimeString(config.getProperty("SLEEP_TIME"), img_logo.toString());
			if(img_logo.isDisplayed()) {
				System.out.println("SURGEACE home screen is displayed");
				pg.softAssertEquals(true, true,"SURGEACE home screen is displayed");
			}
			else {
				System.out.println("SURGEACE home screen is not displayed");
				pg.softAssertEquals(false, true,"Login not successful, so homescreen not displayed");
				toastMsg = pg.toasterMessage(config.getProperty("SLEEP_TIME"),"//div[@id='toast-container']");
				//Unknown error! Please contact your administrator.
				if(toastMsg!=null) {
					pg.softAssertEquals(false, true,"SURGEACE screen is not displayed" + toastMsg);
				}
			}
		}
		catch(Exception e) {
			System.out.println("SURGEACE home screen is not displayed");
			pg.softAssertEquals(false, true,"SURGEACE home screen is not displayed - " +e);
		}
	}
	
	public void navigate(String screen, String menu) throws Exception{
		try {
			Thread.sleep(3000);
			//JavascriptExecutor executor = null;
			switch(menu) {
			case "Students":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_students.toString());
				menu_students.click();
				//executor = (JavascriptExecutor)driver;
				//executor.executeScript("arguments[0].click();", menu_students);
				break;
			case "Attendance":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_attendance.toString());
				menu_attendance.click();
				break;
			case "Contracts":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_contracts.toString());
				menu_contracts.click();
				break;
			case "Certification":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_certification.toString());
				menu_certification.click();
				break;
			case "Batches":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_batches.toString());
				menu_batches.click();
				break;
			case "Events":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_events.toString());
				menu_events.click();
				break;
			case "Users Management":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_usersmgmt.toString());
				menu_usersmgmt.click();
				break;
			case "Email Templates":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_emailtemplates.toString());
				menu_emailtemplates.click();
				break;
			case "Level Testing":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_leveltesting.toString());
				menu_leveltesting.click();
				break;
			case "Ranks":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), menu_ranks.toString());
				menu_ranks.click();
				break;
			}
			
			switch(screen) {
			case "Inquiry":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), tab_inquiry.toString());
				tab_inquiry.click();
				if(lbl_inquirylist.isDisplayed()) {
					System.out.println("Inquiry List screen is displayed");
					pg.softAssertEquals(true, true,"Inquiry List screen is displayed");
				}
				else {
					System.out.println("Inquiry List screen is not displayed");
					pg.softAssertEquals(false, true,"Inquiry List screen is not displayed");
				}
				break;
			case "Students":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), tab_students.toString());
				tab_students.click();
				if(lbl_studentslist.isDisplayed()) {
					System.out.println("Students List screen is displayed");
					pg.softAssertEquals(true, true,"Students List screen is displayed");
				}
				else {
					System.out.println("Students List screen is not displayed");
					pg.softAssertEquals(false, true,"Students List screen is not displayed");
				}
				break;
			case "Staff Attendance":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), tab_staffattendance.toString());
				tab_staffattendance.click();
				if(lbl_staffname.isDisplayed()) {
					System.out.println("Staff Attendance screen is displayed");
					pg.softAssertEquals(true, true,"Staff Attendance screen is displayed");
				}
				else {
					System.out.println("Staff Attendance screen is not displayed");
					pg.softAssertEquals(false, true,"Staff Attendance screen is not displayed");
				}
				break;
			case "Student Attendance":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), tab_studentattendance.toString());
				tab_studentattendance.click();
				if(lbl_class.isDisplayed()) {
					System.out.println("Student Attendance screen is displayed");
					pg.softAssertEquals(true, true,"Student Attendance screen is displayed");
				}
				else {
					System.out.println("Student Attendance screen is not displayed");
					pg.softAssertEquals(false, true,"Student Attendance screen is not displayed");
				}
				break;
			case "Contracts":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), lbl_contracts.toString());
				//tab_contracts.click();
				if(lbl_contracts.isDisplayed()) {
					System.out.println("Contract screen is displayed");
					pg.softAssertEquals(true, true,"Contract screen is displayed");
				}
				else {
					System.out.println("Contract screen is not displayed");
					pg.softAssertEquals(false, true,"Contract screen is not displayed");
				}
				break;
			case "Certifications":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), lbl_certifications.toString());
				//tab_certifications.click();
				if(lbl_certifications.isDisplayed()) {
					System.out.println("Certifications screen is displayed");
					pg.softAssertEquals(true, true,"Certifications screen is displayed");
				}
				else {
					System.out.println("Certifications screen is not displayed");
					pg.softAssertEquals(false, true,"Certifications screen is not displayed");
				}
				break;
			case "Batches":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), lbl_batches.toString());
				//tab_batches.click();
				if(lbl_batches.isDisplayed()) {
					System.out.println("Batches screen is displayed");
					pg.softAssertEquals(true, true,"Batches screen is displayed");
				}
				else {
					System.out.println("Batches screen is not displayed");
					pg.softAssertEquals(false, true,"Batches screen is not displayed");
				}
				break;
			case "Events":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), lbl_events.toString());
				//tab_events.click();
				if(lbl_events.isDisplayed()) {
					System.out.println("Events screen is displayed");
					pg.softAssertEquals(true, true,"Events screen is displayed");
				}
				else {
					System.out.println("Events screen is not displayed");
					pg.softAssertEquals(false, true,"Events screen is not displayed");
				}
				break;
			
			case "Roles Permissions":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), tab_permissions.toString());
				tab_permissions.click();
				if(lbl_rolepermissions.isDisplayed()) {
					System.out.println("Role Permissions screen is displayed");
					pg.softAssertEquals(true, true,"Role Permissions screen is displayed");
				}
				else {
					System.out.println("Role Permissions screen is not displayed");
					pg.softAssertEquals(false, true,"Role Permissions screen is not displayed");
				}
				break;
				
			case "Users List":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), tab_users.toString());
				tab_users.click();
				if(lbl_userlist.isDisplayed()) {
					System.out.println("Users screen is displayed");
					pg.softAssertEquals(true, true,"Users screen is displayed");
				}
				else {
					System.out.println("Users screen is not displayed");
					pg.softAssertEquals(false, true,"Users screen is not displayed");
				}
				break;
			case "Email Templates":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), lbl_emailtemplates.toString());
				//tab_emailtemplates.click();
				if(lbl_emailtemplates.isDisplayed()) {
					System.out.println("Email Templates screen is displayed");
					pg.softAssertEquals(true, true,"Email Templates screen is displayed");
				}
				else {
					System.out.println("Email Templates screen is not displayed");
					pg.softAssertEquals(false, true,"Email Templates screen is not displayed");
				}
				break;
			case "Belt Testing":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), lbl_belttesting.toString());
				//tab_belttesting.click();
				if(lbl_belttesting.isDisplayed()) {
					System.out.println("Belt Testing screen is displayed");
					pg.softAssertEquals(true, true,"Belt Testing screen is displayed");
				}
				else {
					System.out.println("Belt Testing screen is not displayed");
					pg.softAssertEquals(false, true,"Belt Testing screen is not displayed");
				}
				break;
			case "Rank Sets":
				pg.waitTimeString(config.getProperty("SLEEP_TIME"), lbl_ranksets.toString());
				//tab_ranksets.click();
				if(lbl_ranksets.isDisplayed()) {
					System.out.println("Rank Sets screen is displayed");
					pg.softAssertEquals(true, true,"Rank Sets screen is displayed");
				}
				else {
					System.out.println("Rank Sets screen is not displayed");
					pg.softAssertEquals(false, true,"Rank Sets screen is not displayed");
				}
				break;
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void logOutApp() throws Exception
	{
		try {
			pg.waitTimeString(config.getProperty("SLEEP_TIME"), img_profile.toString());
			img_profile.click();
			Thread.sleep(1000);
			btn_logout.click();
			Thread.sleep(2000);
			
			if(txtbx_uname.isDisplayed()) {
				System.out.println("Logout successful");
				pg.softAssertEquals(true, true,"Logout successful");
			}
			else {
				System.out.println("Logout not successful");
				pg.softAssertEquals(false, true,"Logout not successful");
			}	
			driver.close();
			driver.quit();
		}
		catch(Exception e) {
			pg.softAssertEquals(false, true,"Logout not successful");
			System.out.println(e);
		}
	}
	
	public void driverCloseQuit() throws Exception{
		driver.close();
		driver.quit();
		
	}
	 public void goToMainWindow() throws Exception{
	    driver.switchTo().window(Page.mainWindowHandle);
	 }
	   
}
