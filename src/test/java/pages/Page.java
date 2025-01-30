package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.google.common.io.Files;
import pageobjectmanager.PageObjectManager;
import utils.AbstractSteps;
import utils.Util;
import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import com.cucumber.listener.Reporter;

public class Page extends AbstractSteps{

	WebDriver driver;
	PageObjectManager pageObjectManager  = new PageObjectManager(driver);
	private final Properties config = new Properties();
	public static String mainWindowHandle;
	Util util = new Util();
	static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random random = new Random();
    
	public Page(WebDriver driver) throws Exception{
		//super(driver);
		this.driver = getDriver();
		//this.driver= driver;
		PageFactory.initElements(getDriver(), this);
		//PageFactory.initElements(driver, this);
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\SurgeAce_Regression.properties");
		config.load(fis);
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public Page() throws Exception{
		
	}
	
	@AfterStep
	public void addScreenshot(Scenario scenario) throws IOException {
		  File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
		  scenario.embed(fileContent, "image/png");
		 
		
	}
	public String toasterMessage(String sleepTime, String xpath) {
		String result_text = null;
		try {
			//result_text= driver.findElement(By.xpath("//div[@id='toast-container']")).getText();
			result_text= driver.findElement(By.xpath(xpath)).getText();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result_text;
	}
	
	public void waitTimeElement(String sleepTime, WebElement xpath) throws Exception {
		try {
			int sleep = Integer.parseInt(sleepTime);
			WebDriverWait wait = new WebDriverWait(driver,sleep);
			wait.until(ExpectedConditions.elementToBeClickable(xpath));
			wait.until(ExpectedConditions.visibilityOf(xpath));
			softAssertEquals(true, true, xpath.getText() + " field loaded on time");
		}
		catch(Exception e) {
			softAssertEquals(false, true, e + xpath.getText() + " field not loaded on time");
		}
	}
	
	public void waitTimeStringOld(String sleepTime, WebElement xpath) throws Exception {
		String loc = "";
		try {
			int sleep = Integer.parseInt(sleepTime);
			WebDriverWait wait = new WebDriverWait(driver, sleep);
			loc = generateXPATH(xpath, "");
			System.out.println(loc);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc)));
			//wait.until(ExpectedConditions.visibilityOf((WebElement) By.xpath(loc)));
			softAssertEquals(true, true, xpath + " field loaded on time");
			
		}
		catch(Exception e) {
			System.out.println(loc);
			System.out.println(e);
			softAssertEquals(false, true, e.toString() + xpath + " field not loaded on time");
		}
	}
	
	public void waitTimeString(String sleepTime, String xpath) throws Exception {
		try {
			int sleep = Integer.parseInt(sleepTime);
			//driver.manage().timeouts().implicitlyWait(sleep, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, sleep);
			System.out.println("www      "+xpath);
			String[] xpath1 = xpath.split("xpath: ");
			String xpath2 = xpath1[1].substring(0,xpath1[1].length()-1);
			System.out.println(xpath2);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath2)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath2)));
			System.out.println(xpath + " field loaded on time");
		}
		catch(Exception e) {
			System.out.println(e);
			softAssertEquals(false, true, e + xpath + " field not loaded on time");
		}
	}
	
	
	//String z=generateXPATH(webElement, "");//calling method,passing the element

	 public static String generateXPATH(WebElement childElement, String current) {
		 //try {
	        String childTag = childElement.getTagName();
	        if(childTag.equals("html")) {
	            return "/html[1]"+current;
	        }
	        WebElement parentElement = childElement.findElement(By.xpath("..")); 
	        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
	        int count = 0;
	        for(int i=0;i<childrenElements.size(); i++) {
	            WebElement childrenElement = childrenElements.get(i);
	            String childrenElementTag = childrenElement.getTagName();
	            if(childTag.equals(childrenElementTag)) {
	                count++;
	            }
	            if(childElement.equals(childrenElement)) {
	                return generateXPATH(parentElement, "/" + childTag + "[" + count + "]"+current);
	            }
	        }
	        
		/* }
		 catch(Exception e) {
			 System.out.println("culprit");
		 }
		 return null;*/
	        return generateXPATH(parentElement, "/" + childTag + "[" + count + "]"+current);
	    }
	 
	 public void waitForSpinner(WebDriverWait wait) {
		 try {
			 //<div _ngcontent-vty-c312="" class="spinner flex align-center justify-center">
			 //<ngx-spinner _ngcontent-eyq-c17="" bdcolor="rgba(0, 0, 0, 0.8)" size="medium" color="#fff" type="square-spin" _nghost-eyq-c16="" class="ng-tns-c16-0"><!----></ngx-spinner>
			 List<WebElement> spinner = driver.findElements(By.xpath("//ngx-spinner")) ;
			 wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.invisibilityOfAllElements(spinner));
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }
	 }
	 public boolean isElementDisplayed(WebElement element) { //static
		    try {
		        WebDriverWait wait = new WebDriverWait(driver, 1);
		        wait.until(ExpectedConditions.visibilityOf(element));
		        return element.isDisplayed();
		    } 
		    catch (Exception e) {//org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.TimeoutException e) {
		        return false;
		    }
		}
	 public void waitForElementToBeGone(WebElement element, int timeout) { //static
		    try {
				if (isElementDisplayed(element)) {
				    new WebDriverWait(driver, timeout).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	// **************** do not touch below new one ********************************************************* 
	public void softAssertEquals(boolean actual, boolean expected, String message) throws Exception {
		
		if(!actual) {
			addStepsWithScreenshotInReport(driver, message, actual, expected);
			
		}
		else {
			addStepInReport(message, actual, expected);
		}
	}
	
	public void softAssertEqualsWhileConitnue(boolean actual, boolean expected, String message) throws Exception {
		
		if(!actual) {
			addStepsWithScreenshotInReportWhileContinue(driver, message, actual, expected);
			
		}
		else {
			addStepInReport(message, actual, expected);
		}
	}
	
	public void softAssertEquals(String actual, String expected, String message) throws Exception {
	
		if(!actual.equals(expected)) {
			addStepsWithScreenshotInReport(driver, message, actual, expected);
			
		}
		else {
			addStepInReport(message, actual, expected);
		}
	}
	
	public void addStepsWithScreenshotInReportWhileContinue(WebDriver driver, String message, boolean actual, boolean expected) throws IOException
	{	
		try {
			SoftAssert softAssertion = new SoftAssert();
			softAssertion.assertEquals(actual, expected, message);
			String reportMessage = message + " <br> Actual: " + actual + " <br> Expected: " + expected;
			Reporter.addStepLog(reportMessage);
			//take screenshot and attach
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(System.getProperty("user.dir") + "/AutomationReports/Screenshots/Img_"+System.currentTimeMillis()+".png");
			Files.copy(sourcePath, destinationPath);   
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
			
			softAssertion.assertEquals(actual, expected);
			Assert.fail(message);
		}
		catch(Exception e) {
			System.out.println(e);
		}
    }
	
	public void addStepsWithScreenshotInReport(WebDriver driver, String message, boolean actual, boolean expected) throws IOException
	{	
		try {
			String reportMessage = message + " <br> Actual: " + actual + " <br> Expected: " + expected;
			Reporter.addStepLog(reportMessage);
			//take screenshot and attach
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(System.getProperty("user.dir") + "/AutomationReports/Screenshots/Img_"+System.currentTimeMillis()+".png");
			Files.copy(sourcePath, destinationPath);   
			Reporter.addScreenCaptureFromPath(destinationPath.toString());

			Assert.fail(message);
		}
		catch(Exception e) {
			System.out.println(e);
		}
    }
	
	public void addStepsWithScreenshotInReport(WebDriver driver, String message, String actual, String expected) throws IOException
	{
		try {
			//SoftAssert softAssertion = new SoftAssert();
			//softAssertion.assertEquals(actual, expected, message);
			//softAssertion.assertTrue(false, message);
			Assert.fail(message);
			String reportMessage = message + " <br> Actual: " + actual + " <br> Expected: " + expected;
			Reporter.addStepLog(reportMessage);
		
			//take screenshot and attach
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(System.getProperty("user.dir") + "AutomationReports/Screenshots/Img_"+System.currentTimeMillis()+".png");
			Files.copy(sourcePath, destinationPath);   
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
			
			//Take the screenshot
			/*File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile , new File("AutomationReports/Screenshots/"+System.currentTimeMillis()
			+ ".png"));
			Reporter.addScreenCaptureFromPath(System.getProperty("user.dir")+"AutomationReports/Screenshots/Img_"+System.currentTimeMillis()+".png");
			*/
		}
		catch(Exception e) {
			System.out.println(e);
		}
       
	}
	
	public void addStepInReport(String message, boolean actual, boolean expected) throws IOException
	
	{
		try {
			SoftAssert softAssertion = new SoftAssert();
			softAssertion.assertEquals(actual, expected, message);
			String reportMessage = message + " <br> Actual: " + actual + " <br> Expected: " + expected + " <br> " ;
			Reporter.addStepLog(reportMessage);	
						
		}
		catch(Exception e) {
			System.out.println(e);
		}
       
	}
	
	public void addStepInReport(String message, String actual, String expected) throws IOException
	
	{
		try {
			SoftAssert softAssertion = new SoftAssert();
			softAssertion.assertEquals(actual, expected, message);
			String reportMessage = message + " <br> Actual: " + actual + " <br> Expected: " + expected + " <br>";
			Reporter.addStepLog(reportMessage);	
			
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
       
	}
	
	// ********** do not touch below old one ***********************************************
	public void softAssertEquals1(boolean actual, boolean expected, String message) throws Exception {
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertEquals(actual, expected, message);
		String reportMessage = message + " <br> Actual: " + actual + " <br> Expected: " + expected + " <br>";
		if(!actual) {
			//extent.failTest(message);
			addStepsWithScreenshotInReport1(driver, reportMessage);
			
		}
		else {
			//extent.passTest(message);
			addStepInReport1(reportMessage);
		}
	}
	
	public void softAssertEquals1(String actual, String expected, String message) throws Exception {
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertEquals(actual, expected, message);
		String reportMessage = message + " <br> Actual: " + actual + " <br> Expected: " + expected + " <br>";
		if(!actual.equals(expected)) {
			//extent.failTest(message);
			addStepsWithScreenshotInReport1(driver, reportMessage);
			
		}
		else {
			//extent.passTest(message);
			addStepInReport1(reportMessage);
		}
	}
	
	public void addStepsWithScreenshotInReport1(WebDriver driver, String message) throws IOException
	
	{
		try {
			//Reporter.log(message);
			//Take the screenshot
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile , new File("AutomationReports/Screenshots/"+System.currentTimeMillis()
			+ ".png"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
       
	}
	
	public void addStepInReport1(String message) throws IOException
	
	{
		try {
			
			//Reporter.log(message);			
		}
		catch(Exception e) {
			System.out.println(e);
		}
       
	}

	
	//********************************Action Actions********************************************//
	
	public void dblClickEvent(WebElement element)
	{
		//Instantiate Action Class 
		Actions actions = new Actions(driver);
		
		//Double Click the element 
		actions.doubleClick(element).perform(); 
		System.out.println(element + " is double clicked"); 
	}
	
	public void enterPressEvent(WebElement element) {
		//Instantiate Action Class 
		Actions actions = new Actions(driver);
		
		//Double Click the element 
		actions.keyDown(Keys.ENTER).perform(); 
		System.out.println("ENTER key is pressed"); 
	}
	
	public void singleClickEvent(WebElement element)
	{
		//Instantiate Action Class 
		Actions actions = new Actions(driver);
		
		//Single Click the element 
		actions.click(element).perform(); 
		System.out.println(element + " is single clicked"); 
	}

	public void windowHandlingparent()
	{
		driver.switchTo().window(mainWindowHandle);
		System.out.println("switched to "+driver.getTitle()+"  Window");
	}
	
	public void windowHandling() {
		//Get handles of the windows
        mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                	driver.switchTo().window(ChildWindow);
                }
        }
     }
	
	public String mandatoryCheck(WebElement element) {
		String isMandatory = element.getAttribute("aria-required");
		return isMandatory;
	}
	
	
	public void robotAction(String path) throws AWTException, InterruptedException {
		StringSelection s = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s,null);
		Thread.sleep(4000);
		
		// Robot object creation
		Robot r = new Robot();
		r.setAutoDelay(250);
	
		//pressing ctrl+v
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
	
		//releasing ctrl+v
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
	
		Thread.sleep(3000);
		//pressing enter
		r.keyPress(KeyEvent.VK_ENTER);

		//releasing enter
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void robotActionEnter() throws Exception{
		Thread.sleep(3000);
		//pressing enter
		Robot r = new Robot();
		r.setAutoDelay(250);
		r.keyPress(KeyEvent.VK_ENTER);
	}
	
	public String generateRandomString(int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
