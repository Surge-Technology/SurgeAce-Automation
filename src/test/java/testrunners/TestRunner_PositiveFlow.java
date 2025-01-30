package testrunners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/SurgeAceFirstPositiveFlow.feature", 
				 glue="stepdefinition",
				 plugin= {//"pretty",
						 //"json:AutomationReports/cucumber-reports/json/cucumber_json.json",
						 //"junit:AutomationReports/cucumber-reports/xml/cucumber_xml.xml",
						 //"html:AutomationReports/cucumber-reports/html/cucumber_html",
						 "com.cucumber.listener.ExtentCucumberFormatter:AutomationReports/cucumber-reports/extent/cucumber_extent_report.html"},
				 tags= {"@Students,@StaffAttendance,@StudentAttendance,"
				 		+ "@Contract,@Certification,@Batches,@Events,@PermissionsAndUsers,@EmailTemplates,@BeltLevelTesting,@Ranks"},
				 //tags= {"@InquiryAddEditDelete,@StudentAddEditDeactivate,@Contract,@Certification,@Batches"},
				 //tags= {"@StaffAttendance,@StudentAttendance"},
				 //tags= {"@Contract,@Certification,@Batches"},
				 //tags= {"@Ranks"},
				 monochrome=true // If it is set as true, it means that the console output for the Cucumber test are much more readable. 
				 //if it is set as false, then the console output is not as readable as it should be. Just try it.
)

public class TestRunner_PositiveFlow extends AbstractTestNGCucumberTests {
	
	/*@AfterClass
	public static void writeExtentReport() throws Exception
	{
		Reporter.loadXMLConfig(System.getProperty("user.dir")+"\\config\\extent-config.xml");
	}*/

}
