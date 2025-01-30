package stepdefinition;

import java.util.Map;
import pageobjectmanager.PageObjectManager;
import pages.Page;
import utils.AbstractSteps;
import utils.ExcelReadWrite;
import utils.Util;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinition extends AbstractSteps {
	
	PageObjectManager pageObjectManager;
	public ExcelReadWrite excel;
	public Map<String, String> dataMap;
	Util ui;
	Page pg;
	
	public StepDefinition() throws Exception {
		super();
		ui = new Util();
		excel = new ExcelReadWrite();
		System.out.println(driver.get());
		
	}
	
	@Before
	public void setup() throws Exception{
			//WebDriverManager.edgedriver().setup();
			startDriver();
			excel = new ExcelReadWrite(System.getProperty("user.dir")+"\\src\\test\\resources\\data\\SurgeAceTestData.xlsx");
			pageObjectManager = new PageObjectManager(driver.get());
	}
	
	@Given("Login with the username and password in the \"(.*)\" page for \"(.*)\"")
	public void loginApp(String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		pageObjectManager.getLoginPage().login(dataMap);
	}
	
	@Then("Validate if the home screen is loaded")
	public void homeScreen() throws Exception{
		pageObjectManager.getLoginPage().homeScreenDisplay();
	}
	
	// Navigate to "Manage Field Rule" screen in "Setup" as "Admin" by selecting "Platform"
	/*@Then("Navigate to \"(.*)\" screen in \"(.*)\" as \"(.*)\" by selecting workspace as \"(.*)\"")
	public void navigateToSideMenu(String screen, String menu, String userRole, String workspace) throws Exception{
		pageObjectManager.getLoginPage().navigate(screen, menu, userRole, workspace);
	}*/
	
	//Navigate to "Inquiry" screen by clicking on the "Students" side menu
	@Then("Navigate to \"(.*)\" screen by clicking on the \"(.*)\" side menu")
	public void navigateToSideMenu(String screen, String menu) throws Exception{
		pageObjectManager.getLoginPage().navigate(screen, menu);
	}
	
	@Then("Click on the \"(.*)\" button in the \"(.*)\" module")
	public void addScreen(String addScreen, String module) throws Exception{
		if(module.equals("Inquiry"))
			pageObjectManager.getInquiryPage().addScreenClick();
		else if(module.equals("Students"))
			pageObjectManager.getStudentsPage().addScreenClick();
		else if(module.equals("Contract"))
			pageObjectManager.getContractPage().addScreenClick();
		else if(module.equals("Certification"))
			pageObjectManager.getCertificationPage().addScreenClick();
		else if(module.equals("Batches"))
			pageObjectManager.getBatchesPage().addScreenClick();
		else if(module.equals("Events"))
			pageObjectManager.getEventsPage().addScreenClick();
		else if(module.equals("Roles"))
			pageObjectManager.getPermissionsPage().addScreenClick();
		else if(module.equals("Users"))
			pageObjectManager.getUsersPage().addScreenClick();
		else if(module.equals("Email Templates"))
			pageObjectManager.getEmailTemplatesPage().addScreenClick();
		else if(module.equals("Belt Testing"))
			pageObjectManager.getBeltLevelTestingPage().promoteEnableClick();
		else if(module.equals("Ranks") && addScreen.equals("Add New Record"))
			pageObjectManager.getRanksPage().addScreenClick();
		else if(module.equals("Ranks") && addScreen.equals("Sort Order Column"))
			pageObjectManager.getRanksPage().sortOrderClick();
		
	}
	
	@And("Add \"(.*)\" and save in the \"(.*)\" page for \"(.*)\"")
	public void addScenario(String module, String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(module.equals("Inquiry")) {
			pageObjectManager.getInquiryPage().addInquiry(dataMap);
		}
		else if(module.equals("Students")) {
			pageObjectManager.getStudentsPage().addStudent(dataMap);
		}
		else if(module.equals("Contract")) {
			pageObjectManager.getContractPage().addContract(dataMap);
		}
		else if(module.equals("Certificate")) {
			pageObjectManager.getCertificationPage().addCertificate(dataMap);
		}
		else if(module.equals("Batch")) {
			pageObjectManager.getBatchesPage().addBatch(dataMap);
		}
		else if(module.equals("Event")) {
			pageObjectManager.getEventsPage().addEvent(dataMap);
		}
		else if(module.equals("Role")) {
			pageObjectManager.getPermissionsPage().addRole(dataMap);
		}
		else if(module.equals("User")) {
			pageObjectManager.getUsersPage().addUser(dataMap);
		}
		else if(module.equals("Email Template")) {
			pageObjectManager.getEmailTemplatesPage().addEmailTemplate(dataMap);
		}
		else if(module.equals("Belt")) {
			pageObjectManager.getRanksPage().addRank(dataMap);
		}
	}
		
	@Then("Search if the \"(.*)\" record is displayed in the \"(.*)\" page for \"(.*)\"")
	public void verifyInSearch(String action, String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(pageName.equals("InquiryList"))
			pageObjectManager.getInquiryPage().search(dataMap);
		else if(pageName.equals("StudentsList"))
			pageObjectManager.getStudentsPage().search(dataMap);
		else if(pageName.equals("ContractList"))
			pageObjectManager.getContractPage().search(dataMap);
		else if(pageName.equals("CertificationList"))
			pageObjectManager.getCertificationPage().search(dataMap);
		else if(pageName.equals("BatchesList"))
			pageObjectManager.getBatchesPage().search(dataMap);
		else if(pageName.equals("EventsList")) 
			pageObjectManager.getEventsPage().search(dataMap);
		else if(pageName.equals("Permissions")) 
			pageObjectManager.getPermissionsPage().search(dataMap);
		else if(pageName.equals("Users")) 
			pageObjectManager.getUsersPage().search(dataMap);
		else if(pageName.equals("EmailTemplates")) 
			pageObjectManager.getEmailTemplatesPage().search(dataMap);
		else if(pageName.equals("BeltLevelTesting") && scenarioName.equals("Scenario-SearchStudentInBeltLevelAfterApprove"))
			pageObjectManager.getBeltLevelTestingPage().search(dataMap);
		else if(pageName.equals("BeltLevelTesting") && scenarioName.equals("Scenario-SearchStudentInBeltLevelAfterPromote"))
			pageObjectManager.getBeltLevelTestingPage().searchAfterPromote(dataMap);
	}
	
	@And("Edit \"(.*)\" and save in the \"(.*)\" page for \"(.*)\"")
	public void editScenario(String module, String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(module.equals("Inquiry")) {
			pageObjectManager.getInquiryPage().editInquiry(dataMap);
		}
		else if(module.equals("Students")) {
			pageObjectManager.getStudentsPage().editStudent(dataMap);
		}
		else if(module.equals("Contract")) {
			pageObjectManager.getContractPage().editContract(dataMap);
		}
		else if(module.equals("Certificate")) {
			pageObjectManager.getCertificationPage().editCertificate(dataMap);
		}
		else if(module.equals("RegisterEventForCurrentStudent")) {
			pageObjectManager.getEventsPage().editEventForCurrStuRegistration(dataMap);
		}
		else if(module.equals("RegisterEventForGuestStudent")) {
			pageObjectManager.getEventsPage().editEventForGuestStuRegistration(dataMap);
		}
		else if(module.equals("CreateGroupForEvent")) {
			pageObjectManager.getEventsPage().editCreateGroupForEvent(dataMap);
		}
		else if(module.equals("EditGroupForEvent")) {
			pageObjectManager.getEventsPage().editEditGroupForEvent(dataMap);
		}
		else if(module.equals("NotifyForEvent")) {
			pageObjectManager.getEventsPage().editNotifyForEvent(dataMap);
		}
		else if(module.equals("Role")) {
			pageObjectManager.getPermissionsPage().editRole(dataMap);
		}
		else if(module.equals("User")) {
			pageObjectManager.getUsersPage().editUser(dataMap);
		}
		else if(module.equals("Email Template")) {
			pageObjectManager.getEmailTemplatesPage().editEmailTemplate(dataMap);
		}
		else if(module.equals("StudentToPromote")) {
			pageObjectManager.getStudentsPage().editStudentToPromote(dataMap);
		}
		else if(module.equals("Belt")) {
			pageObjectManager.getRanksPage().editRank(dataMap);
		}
	}
	
	@Then("Verify the edited detail in the \"(.*)\" page for \"(.*)\"")
	public void verifyEdit(String pageName, String scenarioName) throws Exception {
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(pageName.equals("InquiryList"))
			pageObjectManager.getInquiryPage().verifyEditedColumn(dataMap);
		else if(pageName.equals("ContractList"))
			pageObjectManager.getContractPage().verifyEditedColumn(dataMap);
		else if(pageName.equals("CertificationList"))
			pageObjectManager.getCertificationPage().verifyEditedColumn(dataMap);
		else if(pageName.equals("Users"))
			pageObjectManager.getUsersPage().verifyEditedColumn(dataMap);
		else if(pageName.equals("EmailTemplates"))
			pageObjectManager.getEmailTemplatesPage().verifyEditedColumn(dataMap);
		else if(pageName.equals("RankSet"))
			pageObjectManager.getRanksPage().verifyEditedColumn(dataMap);
		
	}
	
	@And("Delete \"(.*)\" and confirm in the \"(.*)\" page for \"(.*)\"")
	public void deleteScenario(String module, String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(module.equals("Inquiry")) {
			pageObjectManager.getInquiryPage().deleteInquiry(dataMap);
		}
		else if(module.equals("Students")) {
			pageObjectManager.getStudentsPage().deleteStudent(dataMap);
		}
		else if(module.equals("Contract")) {
			pageObjectManager.getContractPage().deleteContract(dataMap);
		}
		else if(module.equals("Certificate")) {
			pageObjectManager.getCertificationPage().deleteCertificate(dataMap);
		}
		else if(module.equals("Batch")) {
			pageObjectManager.getBatchesPage().deleteBatch(dataMap);
		}
		else if(module.equals("Email Template")) {
			pageObjectManager.getEmailTemplatesPage().deleteEmailTemplate(dataMap);
		}
	}
	
	@Then("Search if the \"(.*)\" record is not displayed in the \"(.*)\" page for \"(.*)\"")
	public void verifyInSearchForDelete(String action, String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(pageName.equals("InquiryList"))
			pageObjectManager.getInquiryPage().searchAfterDelete(dataMap);
		else if(pageName.equals("StudentsList"))
			pageObjectManager.getStudentsPage().searchAfterDelete(dataMap);
		else if(pageName.equals("ContractList"))
			pageObjectManager.getContractPage().searchAfterDelete(dataMap);
		else if(pageName.equals("CertificationList"))
			pageObjectManager.getCertificationPage().searchAfterDelete(dataMap);
		else if(pageName.equals("BatchesList"))
			//pageObjectManager.getBatchesPage().searchAfterDelete(dataMap);
			pageObjectManager.getBatchesPage().searchAfterDeleteWithExactValue(dataMap);
		else if(pageName.equals("EmailTemplates"))
			pageObjectManager.getEmailTemplatesPage().searchAfterDelete(dataMap);
	}
	
	@Then("Add the \"(.*)\" time for the staff in the \"(.*)\" page for \"(.*)\"")
	public void staffInOut(String flow, String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(flow.equals("IN"))
			pageObjectManager.getStaffAttendancePage().attendanceIn(dataMap);
		else if(flow.equals("OUT"))
			pageObjectManager.getStaffAttendancePage().attendanceOut(dataMap);
	}
	
	@Then("Verify the \"(.*)\" time in the grid in the \"(.*)\" page for \"(.*)\"")
	public void verifyStaffTime(String time, String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(time.equals("Check-In"))
			pageObjectManager.getStaffAttendancePage().attendanceCheckIn(dataMap);
		else if(time.equals("Check-Out"))
			pageObjectManager.getStaffAttendancePage().attendanceCheckOut(dataMap);
	}
	
	/*@Then("Select the Style, Master, Class and select the Student checkbox and click on \"(.*)\" as Attend in the \"(.*)\" page for \"(.*)\"")
	public void studentAttendance(String attendance, String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(attendance.equals("Mark"))
			pageObjectManager.getStudentAttendancePage().studentAttendanceMark(dataMap);
		else if(attendance.equals("Unmark"))
			pageObjectManager.getStudentAttendancePage().studentAttendanceUnmark(dataMap);
		else if(attendance.equals("Delete"))
			pageObjectManager.getStudentAttendancePage().studentAttendanceDelete(dataMap);
	}*/
	
	@Then("Select the Class, Time and select the Student checkbox and click on \"(.*)\" as Attend in the \"(.*)\" page for \"(.*)\"")
	public void studentAttendance(String attendance, String pageName, String scenarioName) throws Exception{
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(attendance.equals("Mark"))
			pageObjectManager.getStudentAttendancePage().studentAttendanceMark(dataMap);
		else if(attendance.equals("Unmark"))
			pageObjectManager.getStudentAttendancePage().studentAttendanceUnmark(dataMap);
		else if(attendance.equals("Delete"))
			pageObjectManager.getStudentAttendancePage().studentAttendanceDelete(dataMap);
	}
	
	
	@And("Status Update \"(.*)\" and save in the \"(.*)\" page for \"(.*)\"")
	public void updateStatus(String module, String pageName, String scenarioName) throws Exception {
		dataMap = excel.getRowData(pageName, "ScenarioName", scenarioName);
		if(module.equals("Contract")) {
			pageObjectManager.getContractPage().statusUpdateContract(dataMap);
		}
	}
	
	@Then("Logout of the application")
	public void logOut() throws Exception{
		pageObjectManager.getLoginPage().logOutApp();
	}
	
	@Then("Driver close and quit")
	public void driverExit() throws Exception{
		pageObjectManager.getLoginPage().driverCloseQuit();
	}
	
	
	/*@AfterStep
	public void addScreenshot(Scenario scenario) throws IOException {
		
		if(scenario.isFailed()) {
		  File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
		  scenario.embed(fileContent, "image/png");
		  //scenrio.attach(fileContent, "image/png", "screenshot");
		}
		 // File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 // File destinationPath = new File(System.getProperty("user.dir") + "/AutomationReports/Screenshots/Img_"+System.currentTimeMillis()+".png");
		 // Files.copy(sourcePath, destinationPath);   
		//  Reporter.addScreenCaptureFromPath(destinationPath.toString());
		
	}*/
	
	/***********************************************************************************************/


}
