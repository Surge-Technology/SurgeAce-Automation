package pages;

import org.openqa.selenium.WebDriver;
import java.io.File;
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

public class StudentsPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	Util util = new Util();
	public static String newFilename="";
	public static String studentFirstName = "";
	
	public StudentsPage(WebDriver driver) throws Exception{
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
	
	@FindBy(xpath=("//button[contains(text(),'Add Student')]"))
	public WebElement btn_addstudent;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Register Student')]"))
	public WebElement lbl_registerstudent;
	
	@FindBy(xpath=("//input[@name='firstName']"))
	public WebElement txtbx_fname;
	
	@FindBy(xpath=("//input[@name='lastName']"))
	public WebElement txtbx_lname;
	
	@FindBy(xpath=("//input[@name='birthDate']"))
	public WebElement txtbx_birthdate;
	
	@FindBy(xpath=("//input[@name='address']")) 
	public WebElement txtbx_address;
	
	@FindBy(xpath=("//input[@name='address2']"))
	public WebElement txtbx_address2;
	
	@FindBy(xpath=("//input[@name='city']"))
	public WebElement txtbx_city;	
	
	@FindBy(xpath=("(//div//input[@role='combobox'])[1]"))
	public WebElement combo_state;
	
	@FindBy(xpath=("//input[@name='zipcode']"))
	public WebElement txtbx_zipcode;	
	
	//********* gaurdian **********************************
	@FindBy(xpath=("//input[@name='gfirstName']"))
	public WebElement txtbx_gfname;
	
	@FindBy(xpath=("//input[@name='glastName']"))
	public WebElement txtbx_glname;
	
	@FindBy(xpath=("//input[@name='guardianCheckbox' and @type='checkbox']"))
	public WebElement chkbx_sameasstudents;	
	
	@FindBy(xpath=("//input[@name='gaddress']")) 
	public WebElement txtbx_gaddress;
	
	@FindBy(xpath=("//input[@name='gaddress2']"))
	public WebElement txtbx_gaddress2;
	
	@FindBy(xpath=("//input[@name='gcity']"))
	public WebElement txtbx_gcity;	
	
	@FindBy(xpath=("(//div//input[@role='combobox'])[2]"))
	public WebElement combo_gstate;
	
	@FindBy(xpath=("//input[@name='gzipcode']"))
	public WebElement txtbx_gzipcode;		
	
	@FindBy(xpath=("//input[@name='email']"))
	public WebElement txtbx_gemail;
	
	@FindBy(xpath=("//input[@name='phone']"))
	public WebElement txtbx_gphone;
	
	@FindBy(xpath=("(//div//input[@role='combobox'])[3]"))
	public WebElement combo_batch;
	
	//********************* profile photo upload *******************
	@FindBy(xpath=("//span[@class='btn btn-primary btn-file' and contains(text(),'Upload')]")) //Upload button for profile photo
	public WebElement btn_upload;
	
	// ************ Contract *******************************************
	
	@FindBy(xpath=("(//div//input[@role='combobox'])[4]"))
	public WebElement combo_cname;
	
	@FindBy(xpath=("(//div//input[@role='combobox'])[5]"))
	public WebElement combo_member;
	
	@FindBy(xpath=("(//div//input[@role='combobox'])[6]"))
	public WebElement combo_frequency;
	
	@FindBy(xpath=("//input[@name='fee']"))
	public WebElement txtbx_fee;		
	
	@FindBy(xpath=("//input[@name='discount']"))
	public WebElement txtbx_discount;
	
	@FindBy(xpath=("//input[@name='totalFee']"))
	public WebElement txtbx_totalfee;
	
	@FindBy(xpath=("//input[@name='status']"))
	public WebElement txtbx_status;
	
	@FindBy(xpath=("//input[@name='startDate']"))
	public WebElement txtbx_startdate;
	
	@FindBy(xpath=("//input[@name='endDate']"))
	public WebElement txtbx_enddate;
	
	@FindBy(xpath=("//button[text()='Pay']"))
	public WebElement btn_pay;
	
	@FindBy(xpath=("//span[@class='btn btn-primary btn-file' and contains(text(),'Contract Upload')]")) //Contract Upload button
	public WebElement btn_contractupload;
	
	// ************************ Search ************************************
	@FindBy(xpath=("//strong[text()='Students List']//..//..//..//..//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
	//******************************************************************
	
	@FindBy(xpath=("//button[@type='submit' and text()='Save']"))
	public WebElement btn_save;
	
	@FindBy(xpath=("//button[@type='button' and text()='Cancel']"))
	public WebElement btn_cancel;
	
	

	@FindBy(xpath=("//div[text()='Are you sure you want delete?']"))
	public WebElement popup_delete;
	

	@FindBy(xpath=("//button[text()='Yes, delete it!']"))
	public WebElement btn_yesdelete;
	

	@FindBy(xpath=("//button[text()='Cancel']"))
	public WebElement btn_canceldelete;

	// ***********Edit objects***********************
	
	@FindBy(xpath=("//strong[text()='Students List']//..//..//..//..//span//i[@id='pencilspace']"))
	public WebElement icon_edit;
	
	@FindBy(xpath=("//div[@class='row']//h5//strong[text()='Status']//..//..//..//button[text()='Edit']"))
	public WebElement btn_statusedit;
	
	@FindBy(xpath=("//label[contains(text(),'Belt Levels')]//..//..//input"))
	public WebElement combo_beltlevels;
	
	@FindBy(xpath=("//label[contains(text(),'Sub Levels')]//..//..//input"))
	public WebElement combo_sublevels;
	
	@FindBy(xpath=("//input[@name='anticipated']"))
	public WebElement chkbx_anticipated;
	
	@FindBy(xpath=("//input[@name='approved']"))
	public WebElement chkbx_approved;
	
	@FindBy(xpath=("//div[@class='modal-footer']//button[text()='Save']"))
	public WebElement btn_statuseditsave;
	
	@FindBy(xpath=("//div[@class='modal-footer']//button[text()='Cancel']"))
	public WebElement btn_statuseditcancel;
	
	@FindBy(xpath=("//div[@class='card']//span[@class='badgesize badge bg-primary']"))
	public WebElement lbl_beltlevel;
	
	@FindBy(xpath=("//div[@class='card']//span[@class='badgesize badge bg-success']"))
	public WebElement lbl_sublevel;
	
	//*************Deactivation**********************
	
	@FindBy(xpath=("//button[contains(text(),'Deactivate Student')]"))
	public WebElement btn_deactivate;
	
	@FindBy(xpath=("//label[contains(text(),'Reason')]//..//..//input"))
	public WebElement combo_reason;
	
	@FindBy(xpath=("//textarea[@name='details']"))
	public WebElement txtarea_details;
	
	@FindBy(xpath=("//div[@class='modal-footer']//button[text()='Save']"))
	public WebElement btn_deactsave;
	
	@FindBy(xpath=("//div[@class='modal-footer']//button[text()='Cancel']"))
	public WebElement btn_deactcancel;
	
			
	public void addScreenClick() throws Exception{
		try {
			btn_addstudent.click();
			Thread.sleep(3000);
			if(lbl_registerstudent.isDisplayed())
			{
				System.out.println("Register Student screen is displayed");
				pg.softAssertEquals(true, true,"Register Student screen is displayed");
			}
			else {
				pg.softAssertEquals(false, true,"Register Student screen is not displayed");
				System.out.println("Register Student screen is not displayed");
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Register Student screen is not displayed - "+e);
			System.out.println("Register Student screen is not displayed - "+e);
		}
	}
	
	public void addStudent(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		JavascriptExecutor executor = null;
		try {
			String randomchars = pg.generateRandomString(5);
			studentFirstName = dataMap.get("FName")+"_"+randomchars;
			System.out.println("Student First Name = "+studentFirstName);
			txtbx_fname.sendKeys(studentFirstName);
			txtbx_lname.sendKeys(dataMap.get("LName"));
			txtbx_birthdate.sendKeys(dataMap.get("DOB"));
			txtbx_address.sendKeys(dataMap.get("Address1"));
			txtbx_address2.sendKeys(dataMap.get("Address2"));
			txtbx_city.sendKeys(dataMap.get("City"));
			combo_state.sendKeys(dataMap.get("State"));
			combo_state.sendKeys(Keys.ENTER);
			txtbx_zipcode.sendKeys(dataMap.get("Zipcode"));
			
			txtbx_gfname.sendKeys(dataMap.get("GFName"));
			txtbx_glname.sendKeys(dataMap.get("GLName"));
			if(dataMap.get("SameAsStudent").equals("Yes")) {	
				chkbx_sameasstudents.click();
				Thread.sleep(500);
			}
			else {
				txtbx_gaddress.sendKeys(dataMap.get("GAddress1"));
				txtbx_gaddress2.sendKeys(dataMap.get("GAddress2"));
				txtbx_gcity.sendKeys(dataMap.get("GCity"));
				combo_gstate.sendKeys(dataMap.get("GState"));
				combo_gstate.sendKeys(Keys.ENTER);
				txtbx_gzipcode.sendKeys(dataMap.get("GZipcode"));
			}
			txtbx_gemail.sendKeys(dataMap.get("GEmail"));
			txtbx_gphone.sendKeys(dataMap.get("GPhone"));
			
			combo_batch.sendKeys(dataMap.get("Batch"));
			combo_batch.sendKeys(Keys.ENTER);

			combo_cname.sendKeys(dataMap.get("ContractName"));
			combo_cname.sendKeys(Keys.ENTER);
			
			combo_member.sendKeys(dataMap.get("Member"));
			combo_member.sendKeys(Keys.ENTER);
			
			combo_frequency.sendKeys(dataMap.get("Frequency"));
			combo_frequency.sendKeys(Keys.ENTER);
			
			// Upload profile photo
			fileStudentsPhoto();
			btn_upload.click();
			//pg.robotAction(System.getProperty("user.dir")+config.getProperty("studentProfilePhoto"));
			pg.robotAction(System.getProperty("user.dir")+config.getProperty("otherData")+"\\"+newFilename);
			Thread.sleep(500);
			fileStudentsPhotoToOld();
			
			// Upload Contract
			fileStudentsContract();
			
			if(btn_contractupload.isDisplayed()) {
				executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].scrollIntoView(true);", btn_contractupload); 
				Thread.sleep(1000);
				btn_contractupload.click();
				//pg.robotAction(System.getProperty("user.dir")+config.getProperty("studentContract"));
				pg.robotAction(System.getProperty("user.dir")+config.getProperty("otherData")+"\\"+newFilename);
				fileStudentsContractToOld();
			}
			Thread.sleep(500);
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save); 
			Thread.sleep(5000);
			/*toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[contains(text(),'Student registered successfully')]")).getText();
			//Student registered successfully
			System.out.println(toastMsg);
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed - "+toastMsg);
			}*/
			
			pg.waitTimeString(config.getProperty("SLEEP_TIME"),txtbx_search.toString());
			txtbx_search.clear();
			txtbx_search.sendKeys(studentFirstName);
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("//strong[text()='Students List']//..//..//..//..//table//tbody//tr//td[@tabindex='1']")).isDisplayed())
			{
				pg.softAssertEquals(true, true, "Student registered successfully - message is displayed");
			}
			else {
				pg.softAssertEquals(false, true, "Student registered successfully - message is not displayed");
			}
		}
		catch(Exception e) {
			System.out.println(e);
			//pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void search(Map<String, String> dataMap) {
		try {
			//Thread.sleep(3000);
			pg.waitTimeString(config.getProperty("SLEEP_TIME"),txtbx_search.toString());
			txtbx_search.clear();
			txtbx_search.sendKeys(studentFirstName); //studentFirstName dataMap.get("SearchValue")
			Thread.sleep(1000);
			if(driver.findElement(By.xpath("//strong[text()='Students List']//..//..//..//..//table//tbody//tr//td[@tabindex='1']")).isDisplayed())
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
	
	
	public void editStudent(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		JavascriptExecutor executor = null;
		try {
			Thread.sleep(2000);
			icon_edit.click();
			Thread.sleep(500);
			btn_statusedit.click();
			Thread.sleep(500);
			combo_beltlevels.sendKeys(dataMap.get("BeltLevels"));
			combo_beltlevels.sendKeys(Keys.ENTER);
			Thread.sleep(500);
			combo_sublevels.sendKeys(dataMap.get("SubLevels"));
			combo_sublevels.sendKeys(Keys.ENTER);
			Thread.sleep(500);
			/*if(dataMap.get("TestingAnticipated").equals("Yes"))
					chkbx_anticipated.click();
			
			if(dataMap.get("TestingApproved").equals("Yes"))
				chkbx_anticipated.click();*/
			
			btn_statuseditsave.click();
			Thread.sleep(3000);
			
			//***********new implementation - 10-Jan-2025 : Balaji explained *****************
			
			btn_statusedit.click();
			Thread.sleep(500);
			combo_sublevels.sendKeys(dataMap.get("SubLevelsNext"));
			combo_sublevels.sendKeys(Keys.ENTER);
			btn_statuseditsave.click();
			Thread.sleep(3000);
			
			//************************************************************
			
			String beltLevel = lbl_beltlevel.getText();
			String subLevel = lbl_sublevel.getText();
			
			if(lbl_beltlevel.isDisplayed() && lbl_sublevel.isDisplayed()) {
				pg.softAssertEquals(true, true, "Belt Level = "+beltLevel+", Sub Level = "+subLevel);
				
				//btn_save.click();
				executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].scrollIntoView(true);", btn_save);
				executor.executeScript("arguments[0].click();", btn_save);
				//btn_save.click();
				Thread.sleep(500);
				toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Student updated successfully']")).getText();
				if(toastMsg!=null) {
					pg.softAssertEquals(true, true, toastMsg);
				}
				else {
					pg.softAssertEquals(false, true, "No toaster message displayed for edit student");
				}
			}
			else {
				pg.softAssertEquals(false, true, "Incorrect values, Belt Level = "+beltLevel+", Sub Level = "+subLevel);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void editStudentToPromote(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		JavascriptExecutor executor = null;
		try {
			Thread.sleep(2000);
			icon_edit.click();
			Thread.sleep(500);
			btn_statusedit.click();
			Thread.sleep(500);
			combo_beltlevels.sendKeys(dataMap.get("BeltLevels"));
			combo_beltlevels.sendKeys(Keys.ENTER);
			
			combo_sublevels.sendKeys(dataMap.get("SubLevels"));
			combo_sublevels.sendKeys(Keys.ENTER);
						
			//***********new implementation - 24-Jan-2025 : Balaji explained *****************
			
			if(dataMap.get("TestingAnticipated").equals("Yes"))
				chkbx_anticipated.click();
			btn_statuseditsave.click();
			Thread.sleep(3000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Marked as Anticipated']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for after Marking as Aniticipated");
			}
			
			btn_statusedit.click();
			Thread.sleep(500);
			if(dataMap.get("TestingApproved").equals("Yes"))
				chkbx_approved.click();
			btn_statuseditsave.click();
			Thread.sleep(3000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Approved for Testing']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for after Marking as Approved");
			}
			
			//************************************************************
			
			/*String beltLevel = lbl_beltlevel.getText();
			String subLevel = lbl_sublevel.getText();
			
			if(lbl_beltlevel.isDisplayed() && lbl_sublevel.isDisplayed()) {
			*/
			
			String beltLevel = lbl_beltlevel.getText();
			String subLevel = driver.findElement(By.xpath("//div[@class='card']//span[contains(@class,'badgesize badge') and text()='"+dataMap.get("SubLevels")+"']")).getText();
			
			if(lbl_beltlevel.isDisplayed() && lbl_sublevel.isDisplayed()) {
				pg.softAssertEquals(true, true, "Belt Level = "+beltLevel+", Sub Level = "+subLevel);
				
				//btn_save.click();
				executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].scrollIntoView(true);", btn_save);
				executor.executeScript("arguments[0].click();", btn_save);
				//btn_save.click();
				Thread.sleep(500);
				toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Student updated successfully']")).getText();
				if(toastMsg!=null) {
					pg.softAssertEquals(true, true, toastMsg);
				}
				else {
					pg.softAssertEquals(false, true, "No toaster message displayed for edit student");
				}
			}
			else {
				pg.softAssertEquals(false, true, "Incorrect values, Belt Level = "+beltLevel+", Sub Level = "+subLevel);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void deleteStudent(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(1000);
			icon_edit.click();
			Thread.sleep(500);
			
			btn_deactivate.click();
			Thread.sleep(500);
			
			combo_reason.sendKeys(dataMap.get("StuReason"));
			combo_reason.sendKeys(Keys.ENTER);
			txtarea_details.sendKeys(dataMap.get("StuDetails"));
			btn_statuseditsave.click();
			Thread.sleep(500);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Student deactivated successfully']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for edit student");
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void searchAfterDelete(Map<String, String> dataMap) {
		try {
			Thread.sleep(500);
			txtbx_search.clear();
			txtbx_search.sendKeys(studentFirstName); //dataMap.get("SearchValue"));
			
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
	
	//extras - file handling
	
	
	public void fileStudentsPhoto() throws Exception{
		
		String oldFilename = fileFinder(config.getProperty("stuProfilePhoto"));
		fileRename(oldFilename);
		
	}
	
	public void fileStudentsContract() throws Exception{
		Thread.sleep(100);
		String oldFilenameContract = fileFinder(config.getProperty("stuContractJPG"));
		fileRename(oldFilenameContract);
	}
	
	public void fileStudentsPhotoToOld() throws Exception{
		
		//String oldFilename = fileFinder(config.getProperty("stuProfilePhoto"));
		String oldFilename = config.getProperty("stuProfilePhoto");
		fileRenameToOld(oldFilename);
		
	}
	
	public void fileStudentsContractToOld() throws Exception{
		
		//String oldFilename = fileFinder(config.getProperty("stuContractJPG"));
		String oldFilename = config.getProperty("stuContractJPG");
		fileRenameToOld(oldFilename);
	}
	
	 public String fileFinder(String uploadFile) throws Exception {
    	File directory = new File(System.getProperty("user.dir")+config.getProperty("otherData"));
    	String filename="";
        String[] flist = directory.list(); 
        int flag = 0; 
        if (flist == null) { 
            System.out.println("Empty directory."); 
        } 
        else { 
            // Linear search in the array 
            for (int i = 0; i < flist.length; i++) { 
                filename = flist[i]; 
                if (filename.equalsIgnoreCase(uploadFile)) { 
                    System.out.println(filename + " found"); 
                    flag = 1; 
                    break;
                   //String timeStamp = util.dateTimeSDF();
                } 
            } 
        } 
  
        if (flag == 0) { 
            System.out.println("File Not Found"); 
        } 
        else {
        	 return filename;
        }
        return filename;
	  }
	 
	 public void fileRename(String oldFilename) throws Exception{
		 File oldF = new File(System.getProperty("user.dir")+config.getProperty("otherData")+"\\"+oldFilename); 
		 String[] ofn = oldFilename.split("\\.");
		 String oldname = ofn[0];
		 newFilename = oldname+"_"+util.dateTimeSDF()+".jpg";
		 
		 File newF = new File(System.getProperty("user.dir")+config.getProperty("otherData")+"\\"+newFilename);
		 oldF.renameTo(newF);
	 }
	 
	 public void fileRenameToOld(String oldFilename) throws Exception{
		 File newF = new File(System.getProperty("user.dir")+config.getProperty("otherData")+"\\"+newFilename);
		 File oldF = new File(System.getProperty("user.dir")+config.getProperty("otherData")+"\\"+oldFilename);
		 newF.renameTo(oldF);
	 }
	
}
