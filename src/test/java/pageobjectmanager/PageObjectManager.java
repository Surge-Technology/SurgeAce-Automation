package pageobjectmanager;

import org.openqa.selenium.WebDriver;
import utils.Util;
import pages.LoginSurgeAcePage;
import pages.PermissionsPage;
import pages.RanksPage;
import pages.StaffAttendancePage;
import pages.StudentAttendancePage;
//import pages.StudentAttendancePageOld;
import pages.StudentsPage;
import pages.UsersPage;
import pages.BatchesPage;
import pages.BeltLevelTestingPage;
import pages.CertificationPage;
import pages.ContractPage;
import pages.EmailTemplatesPage;
import pages.EventsPage;
import pages.InquiryPage;

	public class PageObjectManager {

		private final WebDriver driver;
		private Util utilPage;
		private LoginSurgeAcePage loginPage;
		private InquiryPage inquiryPage;
		private StudentsPage studentsPage;
		private StaffAttendancePage staffAttendancePage;
		//private StudentAttendancePageOld studentAttendancePage;
		private StudentAttendancePage studentAttendancePage;
		private ContractPage contractPage;
		private CertificationPage certificationPage;
		private BatchesPage batchesPage;
		private EventsPage eventsPage;
		private PermissionsPage permissionsPage;
		private UsersPage usersPage;
		private EmailTemplatesPage emailTemplatesPage;
		private BeltLevelTestingPage beltLevelTestingPage;
		private RanksPage ranksPage;
		
		public PageObjectManager(WebDriver driver) {
			this.driver = driver;
		}
		
		public Util getUtil() throws Exception {
			return (utilPage == null) ? utilPage = new Util() : utilPage;		
		}
		public LoginSurgeAcePage getLoginPage() throws Exception{
			return (loginPage == null) ? loginPage = new LoginSurgeAcePage(driver) : loginPage;
		}
		public InquiryPage getInquiryPage() throws Exception {
			return (inquiryPage == null) ? inquiryPage = new InquiryPage(driver) : inquiryPage;		
		}
		public StudentsPage getStudentsPage() throws Exception {
			return (studentsPage == null) ? studentsPage = new StudentsPage(driver) : studentsPage;		
		}
		public StaffAttendancePage getStaffAttendancePage() throws Exception {
			return (staffAttendancePage == null) ? staffAttendancePage = new StaffAttendancePage(driver) : staffAttendancePage;		
		}
		public StudentAttendancePage getStudentAttendancePage() throws Exception {
			return (studentAttendancePage == null) ? studentAttendancePage = new StudentAttendancePage(driver) : studentAttendancePage;		
		}
		public ContractPage getContractPage() throws Exception {
			return (contractPage == null) ? contractPage = new ContractPage(driver) : contractPage;		
		}
		public CertificationPage getCertificationPage() throws Exception {
			return (certificationPage == null) ? certificationPage = new CertificationPage(driver) : certificationPage;		
		}
		
		public BatchesPage getBatchesPage() throws Exception {
			return (batchesPage == null) ? batchesPage = new BatchesPage(driver) : batchesPage;		
		}
		
		public EventsPage getEventsPage() throws Exception {
			return (eventsPage == null) ? eventsPage = new EventsPage(driver) : eventsPage;		
		}
		public PermissionsPage getPermissionsPage() throws Exception {
			return (permissionsPage == null) ? permissionsPage = new PermissionsPage(driver) : permissionsPage;		
		}
		public UsersPage getUsersPage() throws Exception {
			return (usersPage == null) ? usersPage = new UsersPage(driver) : usersPage;		
		}
		
		public EmailTemplatesPage getEmailTemplatesPage() throws Exception {
			return (emailTemplatesPage == null) ? emailTemplatesPage = new EmailTemplatesPage(driver) : emailTemplatesPage;		
		}
		public BeltLevelTestingPage getBeltLevelTestingPage() throws Exception {
			return (beltLevelTestingPage == null) ? beltLevelTestingPage = new BeltLevelTestingPage(driver) : beltLevelTestingPage;		
		}
		public RanksPage getRanksPage() throws Exception {
			return (ranksPage == null) ? ranksPage = new RanksPage(driver) : ranksPage;		
		}
}
