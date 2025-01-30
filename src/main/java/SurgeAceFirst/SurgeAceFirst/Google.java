package SurgeAceFirst.SurgeAceFirst;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Google {
	
    public static void main(String[] args) {
    	WebDriver driver = null;
    	try {
        System.out.println("Hello World!");
        System.setProperty("webdriver.edge.driver", "C:\\Users\\haripriya_b\\Desktop\\SurgeAutomation\\drivers\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys("Testing methodology");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@role='presentation']//span)[1]")).click();
        //driver.findElement(By.xpath("(//input[@value='Google Search'])[2]")).click();
        Thread.sleep(2000);
        if(driver.findElement(By.xpath("//h2")).isDisplayed()) {
        	System.out.println("Testing Methodologies search is displayed");
        }
        else {
        	System.out.println("Testing Methodologies search is not displayed");
        }
        driver.close();
		driver.quit();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    		driver.close();
    		driver.quit();
    		
    	}
    	
    }
}









// enquiry module - first complete this
// registration module (student module)
// belt / level testing module
// attendance module (note check in time)
// staff module (check in checkout)


// events module
// email template module
// certificate template module
// contract module

// user module
// batch module

// databse - Postgre sql

// user guide
