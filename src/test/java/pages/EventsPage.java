package pages;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

public class EventsPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	JavascriptExecutor executor = null;
	Util util = new Util();
	public static String titleName="";
	public static String guestStudentFirstName="";
	public static String groupName="";
	
	public EventsPage(WebDriver driver) throws Exception{
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
	
	@FindBy(xpath=("//button[contains(text(),'Add Event')]"))
	public WebElement btn_addevent;
	
	@FindBy(xpath=("//h4//strong[contains(text(),'Create')]"))
	public WebElement lbl_createevent;
	
	@FindBy(xpath=("//input[@name='title']"))
	public WebElement txtbx_title;
	
	@FindBy(xpath=("//label[contains(text(),'Type of event')]//..//input[@role='combobox']"))
	public WebElement combo_typeofevent;
	
	@FindBy(xpath=("//label[contains(text(),'Category of event')]//..//input[@role='combobox']"))
	public WebElement combo_categoryofevent;	
	
	@FindBy(xpath=("//input[@name='customCheck']"))
	public WebElement toggle_customcheck;
	
	@FindBy(xpath=("//input[@name='startDate']"))
	public WebElement txtbx_startdate;	
	
	@FindBy(xpath=("//input[@name='endDate']"))
	public WebElement txtbx_enddate;
		
	@FindBy(xpath=("//label[contains(text(),'Start Time')]//..//input"))
	public WebElement txtbx_starttime;
	
	@FindBy(xpath=("//label[contains(text(),'End Time')]//..//input"))
	public WebElement txtbx_endtime;
	
	@FindBy(xpath=("//textarea[@name='description']"))
	public WebElement txtarea_description;
	
	@FindBy(xpath=("//input[@name='perdayFee']"))
	public WebElement txtbx_eventfee;
	
	@FindBy(xpath=("//input[@name='registrationFee']"))
	public WebElement txtbx_registrationfee;
	
	@FindBy(xpath=("//label[contains(text(),'Attending Days')]//..//input[@role='combobox']"))
	public WebElement combo_attendingdays;
	
	@FindBy(xpath=("//input[@placeholder='Days count']"))
	public WebElement txtbx_days;	
	
	@FindBy(xpath=("//input[@placeholder='Less amount']"))
	public WebElement txtbx_thenapply;
		
	@FindBy(xpath=("//label[text()='Discount/Fee']//..//input[@role='combobox']"))
	public WebElement combo_discountfee;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Save']"))
	public WebElement btn_save;
	
	@FindBy(xpath=("//button[@type='button' and text()='Cancel']"))
	public WebElement btn_cancel;
	
	// ***********Search object***********************
	
	@FindBy(xpath=("//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
	// ***********Register objects***********************
	
	@FindBy(xpath=("//button[text()='Register']"))
	public WebElement btn_register;
	
	@FindBy(xpath=("//input[@type='radio']"))
	public WebElement radio_currstudent;
	
	@FindBy(xpath=("//button[text()='Continue']"))
	public WebElement btn_continue;
	
	@FindBy(xpath=("//div[@class='col']//input"))
	public WebElement chkbx_eventdates;  //get WebElements and iterate and check all
	
	//@FindBy(xpath=("//button[text()='Save']"))
	//public WebElement btn_save;
	
	@FindBy(xpath=("//td[@tabindex='1']//span"))
	public WebElement lbl_registeredname; //getText and then compare with generated name using contains
	
	// **************** Guest Student objects *****************
	
	@FindBy(xpath=("//input[@name='firstName']"))
	public WebElement txtbx_firstname;
	
	@FindBy(xpath=("//input[@name='lastName']"))
	public WebElement txtbx_lastname;
	
	@FindBy(xpath=("//input[@name='birthDate']"))
	public WebElement txtbx_birthdate;
	
	@FindBy(xpath=("//input[@name='email']"))
	public WebElement txtbx_email;
	
	@FindBy(xpath=("//input[@name='phone']"))
	public WebElement txtbx_phone;
	
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
	
	//************ Email Objects ***********************
	//Create Group
	@FindBy(xpath=("//button[text()='Create Group']"))
	public WebElement btn_creategroup;
	
	@FindBy(xpath=("//button[text()='Edit Group']"))
	public WebElement btn_editgroup;
	
	@FindBy(xpath=("//button[text()='Notify']"))
	public WebElement btn_notify;
	
	
	@FindBy(xpath=("//h5//strong[text()='New Audience']"))
	public WebElement lbl_newaudience;
	
	@FindBy(xpath=("//input[@placeholder='Enter Group Name']"))
	public WebElement txtbx_groupname;
	
	@FindBy(xpath=("//th//input[@class='react-bs-select-all']"))
	public WebElement chkbx_checkall;  //checkbox all
	
	@FindBy(xpath=("//button[text()='Save Audience']"))
	public WebElement btn_saveaudience;
	//Created successfully
	
	//Update Group
	@FindBy(xpath=("//h5//strong[text()='Update Audience']"))
	public WebElement lbl_updateaudience;
	
	@FindBy(xpath=("//input[@role='combobox']")) //select group name
	public WebElement combo_groupname;
	 
	@FindBy(xpath=("//td[@tabindex='1']//input[@type='checkbox']")) //first row uncheck , //checked - try !getAttribute("checked")
	public WebElement chkbx_rowone;
	
	@FindBy(xpath=("//button[text()='Update Audience']"))
	public WebElement btn_updateaudience;
	//updated successfully
	
	//Notify
	@FindBy(xpath=("//input[@name='subject']"))
	public WebElement txtbx_subject;
	
	@FindBy(xpath=("//label[contains(text(),'Group Audience')]//..//input[@role='combobox']"))
	public WebElement combo_groupaudience;
	
	@FindBy(xpath=("//textarea[@name='message']"))
	public WebElement txtarea_message;
	
	@FindBy(xpath=("//button[text()='send']"))
	public WebElement btn_send;
	//Created successfully
	  
	@FindBy(xpath=("//strong[text()='Email']//ancestor::div[@class='col']//..//following-sibling::div[@class='rowborder row']//tbody//tr"))
	public WebElement table_subjectrows; //webelements iterate and find the lastly created notify is displayed
	
	@FindBy(xpath=("//button[text()='Cancel']"))
	public WebElement btn_cancelupdateaudeince;
	
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
	
	//Event already exists with name : Birthday
	//h2[contains(text(),'Event already exists with name :')]
	//button[text()='OK']
	
	
	public void addScreenClick() throws Exception{
		try {
			btn_addevent.click();
			Thread.sleep(3000);
			if(lbl_createevent.isDisplayed())
			{
				System.out.println("Create Event screen is displayed");
				pg.softAssertEquals(true, true,"Create Event screen is displayed");
			}
			else {
				pg.softAssertEquals(false, true,"Create Event screen is not displayed");
				System.out.println("Create Event screen is not displayed");
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Create Event screen is not displayed - "+e);
			System.out.println("Create Event screen is not displayed - "+e);
		}
	}
	
	
	public void addEvent(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			titleName = dataMap.get("Title")+"_"+util.dateTimeSDF();
			txtbx_title.sendKeys(titleName);
			
			combo_typeofevent.sendKeys(dataMap.get("TypeOfEvent"));
			combo_typeofevent.sendKeys(Keys.ENTER);
			
			combo_categoryofevent.sendKeys(dataMap.get("CategoryOfEvent"));
			combo_categoryofevent.sendKeys(Keys.ENTER);
			
			toggle_customcheck.click();
			Thread.sleep(500);
			
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
			
			txtbx_eventfee.sendKeys(dataMap.get("EventFee"));
			txtbx_registrationfee.sendKeys(dataMap.get("RegistrationFee"));
			
			combo_attendingdays.sendKeys(dataMap.get("AttendingDays"));
			combo_attendingdays.sendKeys(Keys.ENTER);
			
			txtbx_days.sendKeys(dataMap.get("Days"));
			
			txtbx_thenapply.sendKeys(dataMap.get("ThenApply"));
			
			combo_discountfee.sendKeys(dataMap.get("DiscountFee"));
			combo_discountfee.sendKeys(Keys.ENTER);
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(500);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'created successfully')]")).getText();
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
	
	public void search(Map<String, String> dataMap) throws Exception {
		try {
			Thread.sleep(3000);
			txtbx_search.clear();
			txtbx_search.sendKeys(titleName);
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
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void editEventForCurrStuRegistration(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(2000);
			btn_register.click();
			Thread.sleep(1000);
			btn_register.click();
			Thread.sleep(500);
			txtbx_search.clear();
			txtbx_search.sendKeys(StudentsPage.studentFirstName);
			Thread.sleep(500);
			
			radio_currstudent.click();
			Thread.sleep(500);
			//btn_continue.click();
			executor.executeScript("arguments[0].scrollIntoView(true);", btn_continue);
			executor.executeScript("arguments[0].click();", btn_continue);
			Thread.sleep(1000);
			List<WebElement> elems = driver.findElements(By.xpath("//div[@class='col']//input[@type='checkbox']"));
			for(int i=0; i<elems.size();i++) {
				i=i+1;
				driver.findElement(By.xpath("(//div[@class='col']//input[@type='checkbox'])["+i+"]")).click();
			}
			Thread.sleep(500);
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(500);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'Registered successfully')]")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
				Thread.sleep(2000);
				if(lbl_registeredname.isDisplayed()) {
					String regName = lbl_registeredname.getText();
					if(regName.contains(StudentsPage.studentFirstName)) {
						pg.softAssertEquals(true, true, "Current Student Name is displayed in the Registered table : "+regName);
					}
					else {
						pg.softAssertEquals(false, true, "Current Student Name is not displayed in the Registered table: "+regName);
					}
				}
				else {
					pg.softAssertEquals(false, true, "Current Student Name is not displayed in the Registered table: "+StudentsPage.studentFirstName);
				}
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for Event Registration - "+toastMsg);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void editEventForGuestStuRegistration(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(1000);
			//btn_register.click();
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_register);
			Thread.sleep(500);
			
			String randomchars = pg.generateRandomString(5);
			guestStudentFirstName = dataMap.get("FName")+"_"+randomchars;
			System.out.println("Student First Name = "+guestStudentFirstName);
			
			enterChar(guestStudentFirstName, txtbx_firstname);
			enterChar(dataMap.get("LName"), txtbx_lastname);
			enterChar(dataMap.get("DOB"), txtbx_birthdate);
			enterChar(dataMap.get("Email"), txtbx_email);
			enterChar(dataMap.get("Phone"), txtbx_phone);
			enterChar(dataMap.get("Address1"), txtbx_address);
			enterChar(dataMap.get("Address2"), txtbx_address2);
			enterChar(dataMap.get("City"), txtbx_city);
			enterChar(dataMap.get("State"), combo_state);
			combo_state.sendKeys(Keys.ENTER);
			enterChar(dataMap.get("Zipcode"), txtbx_zipcode);
			
			/*txtbx_firstname.sendKeys(guestStudentFirstName);
			txtbx_lastname.sendKeys(dataMap.get("LName"));
			txtbx_birthdate.sendKeys(dataMap.get("DOB"));
			txtbx_email.sendKeys(dataMap.get("Email"));
			txtbx_phone.sendKeys(dataMap.get("Phone"));
			txtbx_address.sendKeys(dataMap.get("Address1"));
			txtbx_address2.sendKeys(dataMap.get("Address2"));
			txtbx_city.sendKeys(dataMap.get("City"));
			combo_state.sendKeys(dataMap.get("State"));
			combo_state.sendKeys(Keys.ENTER);
			txtbx_zipcode.sendKeys(dataMap.get("Zipcode"));*/
			
			/*Actions actions = new Actions(driver);
			actions.moveToElement(txtbx_firstname).click().sendKeys(guestStudentFirstName).build().perform();
			actions.moveToElement(txtbx_lastname).click().sendKeys(dataMap.get("LName")).build().perform();
			actions.moveToElement(txtbx_birthdate).click().sendKeys(dataMap.get("DOB")).build().perform();
			actions.moveToElement(txtbx_email).click().sendKeys(dataMap.get("Email")).build().perform();
			actions.moveToElement(txtbx_phone).click().sendKeys(dataMap.get("Phone")).build().perform();
			actions.moveToElement(txtbx_address).click().sendKeys(dataMap.get("Address1")).build().perform();
			actions.moveToElement(txtbx_address2).click().sendKeys(dataMap.get("Address2")).build().perform();
			actions.moveToElement(txtbx_city).click().sendKeys(dataMap.get("City")).build().perform();
			actions.moveToElement(combo_state).click().sendKeys(dataMap.get("State")).build().perform();
			actions.moveToElement(combo_state).click().sendKeys(Keys.ENTER).build().perform();
			actions.moveToElement(txtbx_zipcode).click().sendKeys(dataMap.get("Zipcode")).build().perform();*/
			
			//btn_continue.click();
			executor.executeScript("arguments[0].scrollIntoView(true);", btn_continue);
			executor.executeScript("arguments[0].click();", btn_continue);
			Thread.sleep(1000);
			List<WebElement> elems = driver.findElements(By.xpath("//div[@class='col']//input[@type='checkbox']"));
			for(int i=0; i<elems.size();i++) {
				i=i+1;
				driver.findElement(By.xpath("(//div[@class='col']//input[@type='checkbox'])["+i+"]")).click();
				i=i-1;
			}
			Thread.sleep(500);
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(500);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'Registered successfully')]")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
				Thread.sleep(2000);
				if(driver.findElement(By.xpath("//td//span[contains(text(),'"+guestStudentFirstName+"')]")).isDisplayed()) {
					String regName = driver.findElement(By.xpath("//td//span[contains(text(),'"+guestStudentFirstName+"')]")).getText();
					if(regName.contains(guestStudentFirstName)) {
						pg.softAssertEquals(true, true, "Guest Student Name is displayed in the Registered table : "+regName);
					}
					else {
						pg.softAssertEquals(false, true, "Guest Student Name is not displayed in the Registered table: "+regName);
					}
				}
				else {
					pg.softAssertEquals(false, true, "Guest Student Name is not displayed in the Registered table: "+StudentsPage.studentFirstName);
				}
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for Event Registration - "+toastMsg);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void editCreateGroupForEvent(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(500);
			btn_creategroup.click();
			Thread.sleep(500);
			groupName = dataMap.get("GroupName")+"_"+util.dateTimeSDF();
			txtbx_groupname.sendKeys(groupName);
			chkbx_checkall.click();
			//Created successfully
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_saveaudience);
			Thread.sleep(500);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'Created successfully')]")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "New Audience -> Group Name : "+groupName+" : "+toastMsg);
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
	
	public void editEditGroupForEvent(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(2000);
			btn_editgroup.click();
			Thread.sleep(1000);
			combo_groupname.sendKeys(groupName);
			combo_groupname.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			//table//tbody//td//span[contains(text(),'First_XXSZL')]//ancestor::td//..//td//input
			if(!driver.findElement(By.xpath("//table//tbody//td//span[contains(text(),'"+guestStudentFirstName+"')]//ancestor::td//..//td//input")).getAttribute("checked").isEmpty())
			{
				driver.findElement(By.xpath("//table//tbody//td//span[contains(text(),'"+guestStudentFirstName+"')]//ancestor::td//..//td//input")).click();
				//updated  successfully
				executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", btn_updateaudience);
				Thread.sleep(500);
				toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'updated  successfully')]")).getText();
				if(toastMsg!=null) {
					pg.softAssertEquals(true, true, "Update Audience -> Group Name : "+groupName+" : "+toastMsg);
					Thread.sleep(500);
					btn_editgroup.click();
					Thread.sleep(500);
					combo_groupname.sendKeys(groupName);
					combo_groupname.sendKeys(Keys.ENTER);
					if(driver.findElement(By.xpath("//table//tbody//td//span[contains(text(),'"+guestStudentFirstName+"')]//ancestor::td//..//td//input")).getAttribute("checked")==null)
					{
						pg.softAssertEquals(true, true, "Update Audience -> Group Name : "+groupName+" : The Student Name is unchecked as expected");
						btn_cancelupdateaudeince.click();
						Thread.sleep(500);
					}
					else {
						pg.softAssertEquals(false, true, "Update Audience -> Group Name : "+groupName+" : The Student Name is still checked");
					}
				}
				else {
					pg.softAssertEquals(false, true, "No toaster message displayed - "+toastMsg);
				}
			}
			else {
				pg.softAssertEquals(false, true, "The students are not checked already in Update Audience page - "+toastMsg);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void editNotifyForEvent(Map<String, String> dataMap) throws Exception{
		//String toastMsg = "";
		try {
			Thread.sleep(2000);
			btn_notify.click();
			Thread.sleep(500);
			txtbx_subject.sendKeys(dataMap.get("Subject"));
			combo_groupaudience.sendKeys(groupName);
			combo_groupaudience.sendKeys(Keys.ENTER);
			//txtarea_message.sendKeys(dataMap.get("Message"));
			enterChar(dataMap.get("Message"), txtarea_message);
			btn_send.click();
			/*Thread.sleep(4500);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'Created successfully')]")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Email Notification to the Group : "+toastMsg);
				Thread.sleep(2000);
				List<WebElement> elem = driver.findElements(By.xpath("//strong[text()='Email']//ancestor::div[@class='col']//..//following-sibling::div[@class='rowborder row']//tbody//tr"));
				if(elem!=null) {
					for(int i=1;i<elem.size();i++) {
						if(driver.findElement(By.xpath("//strong[text()='Email']//ancestor::div[@class='col']//..//following-sibling::div[@class='rowborder row']//tbody//tr//td[contains(text(),'"+dataMap.get("Subject")+"')]")).isDisplayed()){
							pg.softAssertEquals(true, true, "Email Notification sent to the Group is displayed under the Email section");
							break;
						}
					}
				}
				else {
					pg.softAssertEquals(false, true, "Email Notification sent to the Group is not displayed under the Email section");
				}
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for Email Notification to the Group - "+toastMsg);
			}*/
			
			Thread.sleep(6000);
			List<WebElement> elem = driver.findElements(By.xpath("//strong[text()='Email']//ancestor::div[@class='col']//..//following-sibling::div[@class='rowborder row']//tbody//tr"));
			System.out.println(elem.size());
			if(elem.size()>0) {
				for(int i=0;i<=elem.size();i++) {
					if(driver.findElement(By.xpath("//strong[text()='Email']//ancestor::div[@class='col']//..//following-sibling::div[@class='rowborder row']//tbody//tr//td[contains(text(),'"+dataMap.get("Subject")+"')]")).isDisplayed()){
						Thread.sleep(500);
						pg.softAssertEquals(true, true, "Email Notification created successfully and is displayed under the Email section");
						break;
					}
				}
			}
			else {
				pg.softAssertEquals(false, true, "Email Notification not created and hence it is not displayed under the Email section");
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void enterChar(String value, WebElement elem) throws Exception{
		for (char c : value.toCharArray()) {
			elem.sendKeys(Character.toString(c));
		    Thread.sleep(50); // Adjust the delay as necessary
		}
	}
}
