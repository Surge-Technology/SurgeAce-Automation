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

public class CertificationPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	JavascriptExecutor executor = null;
	Util util = new Util();
	public static String programName="";
	public static String newFilename="";
	
	public CertificationPage(WebDriver driver) throws Exception{
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
	
	@FindBy(xpath=("//button[contains(text(),'Create')]"))
	public WebElement btn_addcertificate;
	
	@FindBy(xpath=("//h5//strong[contains(text(),'Certification')]"))
	public WebElement lbl_certification;
	
	@FindBy(xpath=("//input[@name='programName']"))
	public WebElement txtbx_programname;
	
	@FindBy(xpath=("//label[contains(text(),'Awards')]//..//..//input[@role='combobox']"))
	public WebElement combo_awards;
	
	@FindBy(xpath=("//textarea[@name='bodyCopy']"))
	public WebElement textarea_bodycopy;
	
	@FindBy(xpath=("//input[@name='backgroundImage']")) //choose file for background
	public WebElement btn_choosefile;
		
	@FindBy(xpath=("//div[@id='certificateWrapper']//p[@class='bodyCopy']"))
	public WebElement img_preview;
	
	@FindBy(xpath=("//button[@type='submit' and text()='Save']"))
	public WebElement btn_save;
	
	// ***********Search object***********************
	
	@FindBy(xpath=("//input[@placeholder='Search']"))
	public WebElement txtbx_search;
	
	// ***********Edit objects************************
	
	@FindBy(xpath=("//span//i[@id='pencilspace']"))
	public WebElement icon_edit;
	
	//**********Delete objects***********************
	
	@FindBy(xpath=("//span//i[@class='fa fa-trash-o']"))
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
			btn_addcertificate.click();
			Thread.sleep(3000);
			if(lbl_certification.isDisplayed())
			{
				System.out.println("Create Certification screen is displayed");
				pg.softAssertEquals(true, true,"Create Certification screen is displayed");
			}
			else {
				pg.softAssertEquals(false, true,"Create Certification screen is not displayed");
				System.out.println("Create Certification screen is not displayed");
			}
		}catch(Exception e) {
			pg.softAssertEquals(false, true,"Create Certification screen is not displayed - "+e);
			System.out.println("Create Certification screen is not displayed - "+e);
		}
	}
	
	
	public void addCertificate(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			String randomchars = pg.generateRandomString(4);
			programName = dataMap.get("ProgramName")+"_"+randomchars;
			System.out.println("Program Name = "+programName);
			
			txtbx_programname.sendKeys(programName);
			combo_awards.sendKeys(dataMap.get("Awards"));
			combo_awards.sendKeys(Keys.ENTER);
			
			// choose background photo
			fileCertiBackgroundPhoto();
			//btn_choosefile.click();
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_choosefile);
			pg.robotAction(System.getProperty("user.dir")+config.getProperty("otherData")+"\\"+newFilename);
			Thread.sleep(500);
			fileCertiBackgroundPhotoToOld();
						
			textarea_bodycopy.sendKeys(dataMap.get("BodyCopy"));			
			
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(500);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Registered successfully']")).getText();
			////div[@class='Toastify__toast-body']//div[contains(text(),'successfully')]
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
			Thread.sleep(2000);
			txtbx_search.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			txtbx_search.sendKeys(programName);
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
		}
	}
	public void editCertificate(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(2000);
			icon_edit.click();
			Thread.sleep(500);
			String beforeEditBodyCopy = textarea_bodycopy.getText();
			textarea_bodycopy.click();
			Thread.sleep(1000);
			textarea_bodycopy.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			textarea_bodycopy.sendKeys(dataMap.get("BodyCopy"));
			Thread.sleep(500);
			String afterEditBodyCopy = textarea_bodycopy.getText();
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			//btn_Save.click();
			Thread.sleep(500);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Updated successfully']")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, "Updated Body Copy:\n"+afterEditBodyCopy+"\n Previous Body Copy:\n"+beforeEditBodyCopy);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for edit certificate");
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void verifyEditedColumn(Map<String, String> dataMap) throws Exception{
		try {
			String certificateBody = driver.findElement(By.xpath("//table//tbody//tr//td[@tabindex='3']")).getText();
			if(certificateBody.contains(dataMap.get("BodyCopy")))
			{
				pg.softAssertEquals(true, true, "Actual: \n"+certificateBody+"\n Expected:\n"+dataMap.get("BodyCopy"));
			}
			else {
				pg.softAssertEquals(false, true, "Actual: \n"+certificateBody+"\n Expected:\n"+dataMap.get("BodyCopy"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteCertificate(Map<String, String> dataMap) throws Exception{
		//String toastMsg = "";
		try {
			Thread.sleep(500);
			icon_delete.click();
			Thread.sleep(500);
			if(btn_yesdelete.isDisplayed()) {
				btn_yesdelete.click();
				Thread.sleep(500);
				String toastMsg = lbl_recorddeleted.getText();
				if(toastMsg.equals("Record Deleted!")) {
					btn_ok.click();
					pg.softAssertEquals(true, true, "Certifcation: " +toastMsg);
					Thread.sleep(5000);
				}
				else {
					pg.softAssertEquals(false, true, "No message displayed for Delete contract");
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
			txtbx_search.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			//txtbx_search.clear();
			txtbx_search.sendKeys(programName);
			Thread.sleep(2000);
			
			if(driver.findElement(By.xpath("//tr//td[text()='There is no data to display']")).isDisplayed())
			{
				System.out.println("After delete the record is not displayed in the list page as expected");
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
	
	public void fileCertiBackgroundPhoto() throws Exception{
		
		String oldFilename = fileFinder(config.getProperty("certificateBGPhoto"));
		fileRename(oldFilename);
		
	}
	public void fileCertiBackgroundPhotoToOld() throws Exception{
		
		//String oldFilename = fileFinder(config.getProperty("stuProfilePhoto"));
		String oldFilename = config.getProperty("certificateBGPhoto");
		fileRenameToOld(oldFilename);
		
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

}
