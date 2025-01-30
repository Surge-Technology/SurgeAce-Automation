package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import pageobjectmanager.PageObjectManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class AbstractSteps {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    // public static WebDriver driverLocal;
    protected static ThreadLocal<PageObjectManager> pageObjectManager = new ThreadLocal<PageObjectManager>();
    private final Properties config = new Properties();
    public static Logger log;
    public static final long currTime = System.currentTimeMillis();
    ExtentReports extent;
    ExtentTest logger;

    protected WebDriver getDriver() {
        return driver.get();
    }
    public void startDriver() throws IOException, InterruptedException {
    	try {
       FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\SurgeAce_Regression.properties");
       config.load(fis);
             
       /* System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\msedgedriver_108_win32.exe");
        driver.set(new EdgeDriver());
        getDriver().get(config.getProperty("Application_URL"));
        getDriver().manage().window().maximize();
        Thread.sleep(2000);
		*/
        //WebDriverManager.edgedriver().clearDriverCache().setup();
       WebDriverManager.edgedriver().setup(); 
       driver.set(new EdgeDriver());
        getDriver().get(config.getProperty("Application_URL"));
        getDriver().manage().window().maximize();
        Thread.sleep(2000);
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
               
    }
    public PageObjectManager getPageObjectManager() {
		   return pageObjectManager.get();
	}
    
    
    //you can use Jenkins to run your script "continuously", 
    //however the part of running it in a remote machine you can easily achieve it while creating your WebDriver.
    
    //When you create your driver locally you do something like 
    //driver = new ChromeDriver(capabilities) 
    //however when you create a driver to communicate with a remote server you have to create a Remote Web Driver like
    //driver = new RemoteWebDriver(new URL("http://" + REMOTE_URL + ":4444/wd/hub"), capabilities);. For it to work though, 
    //you have to have a selenium standalone server running in your REMOTE_URL.
    
    
}





