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

public class InquiryPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	JavascriptExecutor executor = null;
	public static String inquiryFirstName="";
	
	public InquiryPage(WebDriver driver) throws Exception{
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
	@FindBy(xpath=("//button[contains(text(),'Add Inquiry')]"))
	public WebElement btn_addinquiry;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Create Inquiry')]"))
	public WebElement lbl_createinquiry;
	
	@FindBy(xpath=("//input[@name='firstName']"))
	public WebElement txtbx_fname;
	
	@FindBy(xpath=("//input[@name='lastName']"))
	public WebElement txtbx_lname;
	
	@FindBy(xpath=("//input[@name='phone']"))
	public WebElement txtbx_phone;
	
	@FindBy(xpath=("//input[@name='email']"))
	public WebElement txtbx_email;
	
	
	@FindBy(xpath=("//input[@name='guardianCheckbox']"))
	public WebElement chkbx_gaurdianispotential;
	
	@FindBy(xpath=("//input[@name='StudentFirstName']"))
	public WebElement txtbx_stufname;
	
	@FindBy(xpath=("//input[@name='StudentLastName']"))
	public WebElement txtbx_stulname;
	
	@FindBy(xpath=("//input[@name='birthDate']"))
	public WebElement txtbx_birthdate;
	
	@FindBy(xpath=("//label[contains(text(),'Relationship')]//..//input[@role='combobox']"))
	public WebElement combo_relationship;
	
	@FindBy(xpath=("//input[@name='Location']"))
	public WebElement txtbx_location;
	
	@FindBy(xpath=("//label[contains(text(),'Inquiry Type')]//..//input[@role='combobox']"))
	public WebElement combo_inquirytype;
	
	@FindBy(xpath=("//label[contains(text(),'Inquiry Status')]//..//input[@role='combobox']")) //if Trial Class selected, then Consent Form fields with Upload button should be displayed
	public WebElement combo_inquirystatus;
	
	
	@FindBy(xpath=("//label[contains(text(),'Programs')]//..//input[@role='combobox']"))
	public WebElement combo_programs;
	
	@FindBy(xpath=("//label//span[contains(text(),'How did you hear about us?')]//..//..//input[@role='combobox']"))
	public WebElement combo_hearaboutus;	
	
	@FindBy(xpath=("//span[@class='btn btn-primary btn-file']")) //Consent Form label with Upload button
	public WebElement btn_upload;
	
	@FindBy(xpath=("//textarea[@name='notes']"))
	public WebElement txtarea_notes;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Save']"))
	public WebElement btn_save;
	
	// ***********Search object***********************
	
	@FindBy(xpath=("//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
	// ***********Edit objects***********************
	
	@FindBy(xpath=("//span//i[@title='Edit']"))
	public WebElement icon_edit;
	
	//**********Delete objects***********************
	
	@FindBy(xpath=("//span//i[@title='Delete']"))
	public WebElement icon_delete;
	
	@FindBy(xpath=("//div[text()='Are you sure you want delete?']"))
	public WebElement popup_delete;
	
	@FindBy(xpath=("//button[text()='Yes, delete it!']"))
	public WebElement btn_yesdelete;
	

	@FindBy(xpath=("//button[text()='Cancel']"))
	public WebElement btn_canceldelete;
	
	//Registered successfully
	//Updated successfully
	
	public void addScreenClick() throws Exception{
		try {
			btn_addinquiry.click();
			Thread.sleep(3000);
			if(lbl_createinquiry.isDisplayed())
			{
				System.out.println("Create Inquiry screen is displayed");
				pg.softAssertEquals(true, true,"Create Inquiry screen is displayed");
			}
			else {
				pg.softAssertEquals(false, true,"Create Inquiry screen is not displayed");
				System.out.println("Create Inquiry screen is not displayed");
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Create Inquiry screen is not displayed - "+e);
			System.out.println("Create Inquiry screen is not displayed - "+e);
		}
	}
	
	
	public void addInquiry(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			//txtbx_fname.sendKeys(dataMap.get("FName"));
			String randomchars = pg.generateRandomString(5);
			inquiryFirstName = dataMap.get("FName")+"_"+randomchars;
			System.out.println("Student First Name = "+inquiryFirstName);
			txtbx_fname.sendKeys(inquiryFirstName);
			txtbx_lname.sendKeys(dataMap.get("LName"));
			txtbx_phone.sendKeys(dataMap.get("Phone"));
			txtbx_email.sendKeys(dataMap.get("Email"));
			
			chkbx_gaurdianispotential.click();
			Thread.sleep(500);
			txtbx_birthdate.sendKeys(dataMap.get("DOB"));
			combo_relationship.sendKeys(dataMap.get("Relationship"));
			combo_relationship.sendKeys(Keys.ENTER);
			
			combo_inquirytype.sendKeys(dataMap.get("InquiryType"));
			combo_inquirytype.sendKeys(Keys.ENTER);
			
			combo_inquirystatus.sendKeys(dataMap.get("InquiryStatus"));
			combo_inquirystatus.sendKeys(Keys.ENTER);
			
			combo_programs.sendKeys(dataMap.get("Programs"));
			combo_programs.sendKeys(Keys.ENTER);
						
			txtbx_location.sendKeys(dataMap.get("Location"));
			
			if(dataMap.get("InquiryStatus").equals("Trial Class") && dataMap.get("UploadClick").equals("Yes")) {
				btn_upload.click();
				//pg.robotAction(System.getProperty("user.dir")+"\\src\\test\\resources\\data\\Inquiry_ConsentForm_TrialClass.docx");
				pg.robotAction(System.getProperty("user.dir")+config.getProperty("inquiryConsentForm"));
			}
			combo_hearaboutus.sendKeys(dataMap.get("HearAboutUs"));
			combo_hearaboutus.sendKeys(Keys.ENTER);
			//pg.robotActionEnter();
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			//btn_Save.click();
			Thread.sleep(1000);
			//toastMsg = pg.toasterMessage(config.getProperty("SLEEP_TIME"),"//div[@id='toast-container']");
			/*toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Registered successfully']")).getText();
			//Registered successfully
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed - "+toastMsg);
			}*/
			
			pg.waitTimeString(config.getProperty("SLEEP_TIME"),txtbx_search.toString());
			txtbx_search.clear();
			txtbx_search.sendKeys(dataMap.get("FName"));
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("//strong[text()='Inquiry List']//..//..//..//..//table//tbody//tr//td[@tabindex='1']")).isDisplayed())
			{
				pg.softAssertEquals(true, true, "Registered successfully - message is displayed for Inquiry");
			}
			else {
				pg.softAssertEquals(false, true, "Registered successfully - message is not displayed for Inquiry");
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
			//txtbx_search.clear();
			txtbx_search.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			txtbx_search.sendKeys(inquiryFirstName); //dataMap.get("SearchValue"));
			
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
	public void editInquiry(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(1000);
			icon_edit.click();
			Thread.sleep(500);
			String programs = driver.findElement(By.xpath("(//div//input[@role='combobox'])[3]//parent::div//parent::div//div")).getText();
			combo_programs.sendKeys(dataMap.get("Programs"));
			combo_programs.sendKeys(Keys.ENTER);
			Thread.sleep(500);
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			//btn_Save.click();
			Thread.sleep(500);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Updated successfully']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Updated Programs:"+dataMap.get("Programs")+", Previous value:"+programs+" -> "+toastMsg);
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
			String servicePrograms = driver.findElement(By.xpath("//table//tbody//tr//td[@tabindex='4']//span")).getText();
			if(servicePrograms.equals(dataMap.get("Programs")))
			{
				pg.softAssertEquals(true, true, "Edited details - Actual:"+servicePrograms+", Expected:"+dataMap.get("Programs"));
			}
			else {
				pg.softAssertEquals(false, true, "Edited details - Actual:"+servicePrograms+", Expected:"+dataMap.get("Programs"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteInquiry(Map<String, String> dataMap) throws Exception{
		//String toastMsg = "";
		try {
			Thread.sleep(500);
			icon_delete.click();
			Thread.sleep(500);
			if(btn_yesdelete.isDisplayed()) {
				btn_yesdelete.click();
				Thread.sleep(500);
				//toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Record deleted successfully']")).getText();
				String toastMsg = driver.findElement(By.xpath("//*[contains(text(),'deleted successfully')]")).getText();
				if(toastMsg.equals("Record deleted successfully")) {
					pg.softAssertEquals(true, true, toastMsg);
					Thread.sleep(2000);
				}
				else {
					pg.softAssertEquals(false, true, "No toaster message displayed for delete inquiry");
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
			//txtbx_search.clear();
			txtbx_search.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			txtbx_search.sendKeys(inquiryFirstName); //dataMap.get("SearchValue"));
			
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
	}
	
	
}
