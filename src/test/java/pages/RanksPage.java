package pages;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectmanager.PageObjectManager;
import utils.AbstractSteps;
import utils.Util;

public class RanksPage extends AbstractSteps{
	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	Page pg = new Page(driver);
	private final Properties config = new Properties();
	JavascriptExecutor executor = null;
	Util util = new Util();
	public static String beltName = "";
	private final Color HEX_COLOUR = Color.fromString("#2F7ED8");;
	
	public RanksPage(WebDriver driver) throws Exception{
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
	
	@FindBy(xpath=("//td[@tabindex='2']//div"))
	public WebElement lbl_beltcategory; //-- get attribute of value from Rank Sets screen
	
	@FindBy(xpath=("//button[contains(text(),'Manage')]"))
	public WebElement btn_manage;
	
	@FindBy(xpath=("//button[contains(text(),'Add New Record')]"))
	public WebElement btn_addnewrecord;
	
	@FindBy(xpath=("//b[text()='Add Belt']"))
	public WebElement lbl_addbelt;
	
	@FindBy(xpath=("//input[@name='beltName']"))
	public WebElement txtbx_beltname;
	
	@FindBy(xpath=("//input[@name='code']"))
	public WebElement txtbx_code;
	
	@FindBy(xpath=("//input[@name='eligibility']"))
	public WebElement txtbx_monthsforeligibility;
	
	@FindBy(xpath=("//input[@name='SortOrder']"))
	public WebElement txtbx_sortorder;
	
	@FindBy(xpath=("//input[@name='isBlackBelt']"))
	public WebElement chkbx_isBlackBelt;
	
	@FindBy(xpath=("//input[@name='isActive']"))
	public WebElement chkbx_isActive;;
	
	@FindBy(xpath=("//input[@name='outerClr']"))
	public WebElement color_outerbelt;
	
	@FindBy(xpath=("//input[@name='innerClr']"))
	public WebElement color_innerbelt;
	
	@FindBy(xpath=("//button[contains(text(),'save')]"))
	public WebElement btn_save;
	
	@FindBy(xpath=("//th[@title='Sort Order']"))
	public WebElement table_sortorder;
	
	@FindBy(xpath=("//button[contains(text(),'Update')]"))
	public WebElement btn_update;
	
	@FindBy(xpath=("//button[contains(text(),'Cancel')]"))
	public WebElement btn_cancel;
	
	public void addScreenClick() throws Exception{
		try {
			btn_manage.click();
			Thread.sleep(1000);
			if(btn_addnewrecord.isDisplayed()) {
				btn_addnewrecord.click();
				pg.softAssertEquals(true, true, "Rank Sets and Achievements screen is displayed with Add New Record button");
			}
			else {
				pg.softAssertEquals(true, true, "Rank Sets and Achievements screen is not displayed with Add New Record button");
			}
		}
		catch(Exception e) {
			System.out.println("Rank Sets and Achievements screen is not displayed with Add New Record button"+e);
			pg.softAssertEquals(false, true,"Rank Sets and Achievements screen is not displayed with Add New Record button"+e);
		}
	}
	
	public void addRank(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		executor = (JavascriptExecutor)driver;
		try {
			Thread.sleep(2000);
			String randomchars = pg.generateRandomString(3);
			beltName = dataMap.get("BeltName")+" "+randomchars;
			txtbx_beltname.sendKeys(beltName);
			txtbx_code.sendKeys(dataMap.get("Code"));
			
			color_outerbelt.click();
			Actions builder = new Actions(driver);
			Action seriesOfActions=null;
			for(int i=1;i<=50;i++) {
				seriesOfActions = builder
								.moveToElement(color_outerbelt)
								.sendKeys(Keys.ARROW_UP)
								.build();
			}
			seriesOfActions.perform();
			Thread.sleep(500);
			
			color_innerbelt.click();
			for(int i=1;i<=70;i++) {
				seriesOfActions = builder
								.moveToElement(color_innerbelt)
								.sendKeys(Keys.ARROW_UP)
								.build();
			}
			seriesOfActions.perform();
			Thread.sleep(2000);
			
			builder.moveToElement(txtbx_monthsforeligibility).click().sendKeys(dataMap.get("MonthsForEligibility")).build().perform();
			builder.moveToElement(txtbx_sortorder).click().sendKeys(dataMap.get("SortOrder")).build().perform();
			Thread.sleep(1000);
			chkbx_isBlackBelt.click();
			chkbx_isActive.click();
						
			//executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(500);
			//toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Email-Template created successfully']")).getText();
			toastMsg = driver.findElement(By.xpath("//*[contains(text(),'added successfully')]")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for New Belt creation - "+toastMsg);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}

	
	public void addRankOld(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		executor = (JavascriptExecutor)driver;
		try {
			String randomchars = pg.generateRandomString(3);
			beltName = dataMap.get("BeltName")+" "+randomchars;
			txtbx_beltname.sendKeys(beltName);
			txtbx_code.sendKeys(dataMap.get("Code"));
			
			//dark green -> #40b542
			//color_outerbelt.sendKeys("#40b542");
			//executor.executeScript("arguments[0].click();", color_outerbelt);
			//executor.executeScript("arguments[0].setAttribute('value','#40b542');", color_outerbelt);
			//executor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2]);", color_outerbelt, "value", "#40b542");
			
			color_outerbelt.click();
			Actions builder = new Actions(driver);
			Action seriesOfActions=null;
			for(int i=1;i<=1;i++) {
				seriesOfActions = builder
									 .moveToElement(color_outerbelt)
									 .sendKeys(Keys.ARROW_UP)
									 .build();
			}
									/* .sendKeys(Keys.ARROW_UP)
									 .sendKeys(Keys.ARROW_UP)
									 .sendKeys(Keys.ARROW_UP)
									 .build();*/
			seriesOfActions.perform();
			Thread.sleep(500);
			//yellow -> #ede60c
			//color_innerbelt.sendKeys("#ede60c");
			//executor.executeScript("arguments[0].click();", color_innerbelt);
			//executor.executeScript("arguments[0].setAttribute('value','#ede60c')", color_innerbelt);
			//executor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2]);", color_innerbelt, "value", "#ede60c");
			color_innerbelt.click();
			for(int i=1;i<=5;i++) {
				seriesOfActions = builder
									 .moveToElement(color_innerbelt)
									 .sendKeys(Keys.ARROW_UP)
									 .build();
			}
			seriesOfActions.perform();
			Thread.sleep(2000);
			
			txtbx_monthsforeligibility.sendKeys(dataMap.get("MonthsForEligibility"));
			Thread.sleep(500);
			
			txtbx_sortorder.sendKeys(dataMap.get("SortOrder"));
			Thread.sleep(500);
			
			chkbx_isBlackBelt.click();
			chkbx_isActive.click();
						
			executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", btn_save);
			Thread.sleep(1000);
			toastMsg = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']//div[text()='Email-Template created successfully']")).getText();
			//Email-Template created successfully
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for Email Template creation - "+toastMsg);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, ""+e);
		}
	}
	
	public void sortOrderClick() throws Exception{
		
		try {
			Thread.sleep(3000);
			table_sortorder.click();
			table_sortorder.click(); //only if we click twice it is sorting with sort order as minimum number
			
			List<WebElement> elems = driver.findElements(By.xpath("//div[@class='react-bs-container-body']//tbody//tr//td"));
			
			/*for(WebElement elem: elems) {
				String belt = elem.getText();
				if(belt.equals(beltName))
				{
					pg.softAssertEquals(true, true, "The belt name added is displayed in the Rank Sets and Achievements grid");
					break;
				}
			}*/
			
			for(int i=1; i<=elems.size()-1 ;i++) {
				 String belt = driver.findElement(By.xpath("(//div[@class='react-bs-container-body']//tbody//tr//td)["+i+"]")).getText();
				 if(belt.equals(beltName)) { //"BeltRank ZPG"
					 pg.softAssertEquals(true, true, "The belt name added is displayed in the Rank Sets and Achievements grid");
					 Thread.sleep(500);
					 Actions builder = new Actions(driver);
					 builder.moveToElement(driver.findElement(By.xpath("(//div[@class='react-bs-container-body']//tbody//tr//td)["+i+"]//following-sibling::td//button"))).click().build().perform();
					 
					 //driver.findElement(By.xpath("(//div[@class='react-bs-container-body']//tbody//tr//td)["+i+"]//following-sibling::td//button")).click();
					 //above is to click the Edit button against the correct belt name, as there is no search feature. Issues raised for Search feature
					 break;
				 }
			}
		}
		catch(Exception e) {
			System.out.println(e);
			pg.softAssertEquals(false, true, "The belt name added is not displayed in the Rank Sets and Achievements grid");
		}
	}

	public void editRank(Map<String, String> dataMap) throws Exception{
		String toastMsg = "";
		try {
			Thread.sleep(1000);
			txtbx_code.clear();
			txtbx_code.sendKeys(dataMap.get("Code"));
			
			Thread.sleep(500);
			btn_update.click();
			
			Thread.sleep(500);
			toastMsg = driver.findElement(By.xpath("//*[contains(text(),'added successfully')]")).getText();
			if(toastMsg!=null) {
				pg.softAssertEquals(true, true, toastMsg);
			}
			else {
				pg.softAssertEquals(false, true, "No toaster message displayed for Edit Belt - "+toastMsg);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void verifyEditedColumn(Map<String, String> dataMap) throws Exception{
		try {
			Thread.sleep(2000);
			table_sortorder.click();
			table_sortorder.click(); //only if we click twice it is sorting with sort order as minimum number
			
			List<WebElement> elems = driver.findElements(By.xpath("//div[@class='react-bs-container-body']//tbody//tr//td"));
			
			for(int i=1; i<=elems.size()-1 ;i++) {
				 String belt = driver.findElement(By.xpath("(//div[@class='react-bs-container-body']//tbody//tr//td)["+i+"]")).getText();
				 if(belt.equals(beltName)) { // BeltRank ZPG
					 pg.softAssertEquals(true, true, "The belt name edited is displayed in the Rank Sets and Achievements grid");
					 String code = driver.findElement(By.xpath("(//div[@class='react-bs-container-body']//tbody//tr//td)["+i+"]//following-sibling::td")).getText();
					 if(code.equals(dataMap.get("Code"))){
						 pg.softAssertEquals(true, true, "The edited Code is updated as expected in the grid");
						 break;
					 }
					
				 }
			}
		}
		catch(Exception e) {
			pg.softAssertEquals(false, true, "The edited Code is not updated as expected in the grid");
		}
	}
}
