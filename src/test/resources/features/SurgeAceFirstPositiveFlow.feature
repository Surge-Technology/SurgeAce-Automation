Feature:SurgeAceFirstPositiveFlow

Background

@AssertCheck
  Scenario: Add Edit and Delete Inquiry
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-Inquiry"
	  Then Validate if the home screen is loaded

@Inquiry
  Scenario: Add Edit and Delete Inquiry
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-Inquiry"
    Then Validate if the home screen is loaded
    Then Navigate to "Inquiry" screen by clicking on the "Students" side menu
    
    Then Click on the "Add Inquiry" button in the "Inquiry" module
    And Add "Inquiry" and save in the "CreateInquiry" page for "Scenario-AddInquiry"
    Then Search if the "added" record is displayed in the "InquiryList" page for "Scenario-SearchInquiryAfterAdd"
    
    And Edit "Inquiry" and save in the "EditInquiry" page for "Scenario-EditInquiry"
    Then Search if the "edited" record is displayed in the "InquiryList" page for "Scenario-SearchInquiryAfterEdit"
    Then Verify the edited detail in the "InquiryList" page for "Scenario-SearchInquiryAfterEdit"
    
    And Delete "Inquiry" and confirm in the "Delete" page for "Scenario-DeleteInquiry"
    Then Search if the "deleted" record is not displayed in the "InquiryList" page for "Scenario-SearchInquiryForDelete"
    
    Then Logout of the application
    

@Students
	Scenario: Add Edit and Delete Student
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-Students"
    Then Validate if the home screen is loaded
    Then Navigate to "Students" screen by clicking on the "Students" side menu
    
    Then Click on the "Add Student" button in the "Students" module
    And Add "Students" and save in the "CreateStudent" page for "Scenario-AddStudent"
    Then Search if the "added" record is displayed in the "StudentsList" page for "Scenario-SearchStudentAfterAdd"
    
    And Edit "Students" and save in the "EditStudent" page for "Scenario-EditStudent"
    Then Search if the "edited" record is displayed in the "StudentsList" page for "Scenario-SearchStudentAfterEdit"
        
    And Delete "Students" and confirm in the "Delete" page for "Scenario-DeleteStudent"
    Then Search if the "deleted" record is not displayed in the "StudentsList" page for "Scenario-SearchStudentForDelete"
    
    Then Logout of the application
    
@StaffAttendance
	Scenario: Staff Attendance In and Out Time
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-StaffAttendance"
    Then Validate if the home screen is loaded
    Then Navigate to "Staff Attendance" screen by clicking on the "Attendance" side menu
    
    Then Add the "IN" time for the staff in the "StaffAttendance" page for "Scenario-StaffAttendance"
    Then Verify the "Check-In" time in the grid in the "StaffAttendance" page for "Scenario-StaffAttendance"
    Then Add the "OUT" time for the staff in the "StaffAttendance" page for "Scenario-StaffAttendance"
    Then Verify the "Check-Out" time in the grid in the "StaffAttendance" page for "Scenario-StaffAttendance"
    
    Then Logout of the application
    
@StudentAttendance
	Scenario: Student Attendance Mark, Unmark and Delete
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-StudentAttendance"
    Then Validate if the home screen is loaded
    
    Then Navigate to "Students" screen by clicking on the "Students" side menu
    Then Click on the "Add Student" button in the "Students" module
    And Add "Students" and save in the "CreateStudent" page for "Scenario-AddStudent"
    
    Then Navigate to "Student Attendance" screen by clicking on the "Attendance" side menu
    #Then Select the Style, Master, Class and select the Student checkbox and click on "Mark" as Attend in the "StudentAttendance" page for "Scenario-StudentAttendance" 
    #Then Select the Style, Master, Class and select the Student checkbox and click on "Unmark" as Attend in the "StudentAttendance" page for "Scenario-StudentAttendance"
    #Then Select the Style, Master, Class and select the Student checkbox and click on "Delete" as Attend in the "StudentAttendance" page for "Scenario-StudentAttendance"
    Then Select the Class, Time and select the Student checkbox and click on "Mark" as Attend in the "StudentAttendance" page for "Scenario-StudentAttendance" 
    Then Select the Class, Time and select the Student checkbox and click on "Unmark" as Attend in the "StudentAttendance" page for "Scenario-StudentAttendance"
    Then Select the Class, Time and select the Student checkbox and click on "Delete" as Attend in the "StudentAttendance" page for "Scenario-StudentAttendance"
    
    Then Logout of the application  
    
@Contract
		Scenario: Add, Edit, Status Update and Delete Contract
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-Contract"
    Then Validate if the home screen is loaded
    Then Navigate to "Contracts" screen by clicking on the "Contracts" side menu
    
    Then Click on the "Add Contract" button in the "Contract" module
    And Add "Contract" and save in the "Contract" page for "Scenario-AddContract"
    Then Search if the "added" record is displayed in the "ContractList" page for "Scenario-SearchContractAfterAdd"
    
    And Edit "Contract" and save in the "Contract" page for "Scenario-EditContract"
    Then Search if the "edited" record is displayed in the "ContractList" page for "Scenario-SearchContractAfterEdit"
    Then Verify the edited detail in the "ContractList" page for "Scenario-SearchContractAfterEdit"
    
    And Status Update "Contract" and save in the "Contract" page for "Scenario-StatusUpdateContract"
    
    And Delete "Contract" and confirm in the "Contract" page for "Scenario-DeleteContract"
    Then Search if the "deleted" record is not displayed in the "ContractList" page for "Scenario-SearchContractAfterDelete"
    
    Then Logout of the application 
    
@Certification
		Scenario: Add, Edit and Delete Certification
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-Certification"
    Then Validate if the home screen is loaded
    Then Navigate to "Certifications" screen by clicking on the "Certification" side menu
    
    Then Click on the "Create" button in the "Certification" module
    And Add "Certificate" and save in the "Certification" page for "Scenario-AddCertification"
    Then Search if the "added" record is displayed in the "CertificationList" page for "Scenario-SearchCertificationAfterAdd"
    
    And Edit "Certificate" and save in the "Certification" page for "Scenario-EditCertification"
    Then Search if the "edited" record is displayed in the "CertificationList" page for "Scenario-SearchCertificationAfterEdit"
    Then Verify the edited detail in the "CertificationList" page for "Scenario-SearchCertificationAfterEdit"
    
    And Delete "Certificate" and confirm in the "Certification" page for "Scenario-DeleteCertification"
    Then Search if the "deleted" record is not displayed in the "CertificationList" page for "Scenario-SearchCertificationAfterDelete"
    
    Then Logout of the application 

@Batches
		Scenario: Add and Delete Batch
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-Batches"
    Then Validate if the home screen is loaded
    Then Navigate to "Batches" screen by clicking on the "Batches" side menu
    
    Then Click on the "Add Batch" button in the "Batches" module
    And Add "Batch" and save in the "Batches" page for "Scenario-AddBatch"
    Then Search if the "added" record is displayed in the "BatchesList" page for "Scenario-SearchBatchAfterAdd"
    
    And Delete "Batch" and confirm in the "Batches" page for "Scenario-DeleteBatch"
    Then Search if the "deleted" record is not displayed in the "BatchesList" page for "Scenario-SearchBatchAfterDelete"
    
    Then Logout of the application  
    
@Events
		Scenario: Add and Register Events
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-Events"
    Then Validate if the home screen is loaded
    
    Then Navigate to "Students" screen by clicking on the "Students" side menu
    Then Click on the "Add Student" button in the "Students" module
    And Add "Students" and save in the "CreateStudent" page for "Scenario-AddStudent"
    
    Then Navigate to "Events" screen by clicking on the "Events" side menu
    Then Click on the "Add Event" button in the "Events" module
    And Add "Event" and save in the "Events" page for "Scenario-AddEvent"
    Then Search if the "added" record is displayed in the "EventsList" page for "Scenario-SearchEventAfterAdd"
    
    And Edit "RegisterEventForCurrentStudent" and save in the "Events" page for "Scenario-RegisterEventForCurrentStudent"
    And Edit "RegisterEventForGuestStudent" and save in the "Events" page for "Scenario-RegisterEventForGuestStudent"
    And Edit "CreateGroupForEvent" and save in the "Events" page for "Scenario-CreateGroupForEvent"
    And Edit "EditGroupForEvent" and save in the "Events" page for "Scenario-EditGroupForEvent"
    And Edit "NotifyForEvent" and save in the "Events" page for "Scenario-NotifyForEvent"
    Then Logout of the application
    
    
 @PermissionsAndUsers
 		Scenario: Add and Edit Roles, Add and Edit Users
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-PermissionsAndUsers"
    Then Validate if the home screen is loaded
    
    Then Navigate to "Roles Permissions" screen by clicking on the "Users Management" side menu
    Then Click on the "Add Role" button in the "Roles" module
    And Add "Role" and save in the "Permissions" page for "Scenario-AddRole"
    Then Search if the "added" record is displayed in the "Permissions" page for "Scenario-SearchRoleAfterAdd"
    And Edit "Role" and save in the "Permissions" page for "Scenario-EditRole"
     
    Then Navigate to "Users List" screen by clicking on the "Users Management" side menu
    Then Click on the "Add User" button in the "Users" module
    And Add "User" and save in the "Users" page for "Scenario-AddUser"
    Then Search if the "added" record is displayed in the "Users" page for "Scenario-SearchUserAfterAdd"
    
    And Edit "User" and save in the "Users" page for "Scenario-EditUser"
    Then Search if the "edited" record is displayed in the "Users" page for "Scenario-SearchUserAfterEdit"
    Then Verify the edited detail in the "Users" page for "Scenario-SearchUserAfterEdit"
  	Then Logout of the application

@EmailTemplates
	Scenario: Add, Edit and Delete Email Templates
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-EmailTemplates"
    Then Validate if the home screen is loaded
    Then Navigate to "Email Templates" screen by clicking on the "Email Templates" side menu
    
    Then Click on the "Add Email Template" button in the "Email Templates" module
    And Add "Email Template" and save in the "EmailTemplates" page for "Scenario-AddEmailTemplate"
    Then Search if the "added" record is displayed in the "EmailTemplates" page for "Scenario-SearchEmailTemplateAfterAdd"
    
    And Edit "Email Template" and save in the "EmailTemplates" page for "Scenario-EditEmailTemplate"
    Then Search if the "edited" record is displayed in the "EmailTemplates" page for "Scenario-SearchEmailTemplateAfterEdit"
    Then Verify the edited detail in the "EmailTemplates" page for "Scenario-SearchEmailTemplateAfterEdit"
    
    And Delete "Email Template" and confirm in the "EmailTemplates" page for "Scenario-DeleteEmailTemplate"
    Then Search if the "deleted" record is not displayed in the "EmailTemplates" page for "Scenario-SearchEmailTemplateAfterDelete"
    
    Then Logout of the application 
    
@BeltLevelTesting
	Scenario: Promote Students to next level
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-BeltLevelTesting"
    Then Validate if the home screen is loaded
    Then Navigate to "Students" screen by clicking on the "Students" side menu
    Then Click on the "Add Student" button in the "Students" module
    And Add "Students" and save in the "CreateStudent" page for "Scenario-AddStudent"
    Then Search if the "added" record is displayed in the "StudentsList" page for "Scenario-SearchStudentAfterAdd"
    
    And Edit "StudentToPromote" and save in the "EditStudent" page for "Scenario-EditStudentToPromote"
    
    Then Navigate to "Belt Testing" screen by clicking on the "Level Testing" side menu
    Then Search if the "added" record is displayed in the "BeltLevelTesting" page for "Scenario-SearchStudentInBeltLevelAfterApprove"
    Then Click on the "Promote" button in the "Belt Testing" module
    Then Search if the "promoted" record is displayed in the "BeltLevelTesting" page for "Scenario-SearchStudentInBeltLevelAfterPromote"

    Then Logout of the application
    
@Ranks
   Scenario: Add and Edit Rank Sets
    Given Login with the username and password in the "SurgeAceLogin" page for "Scenario-RankSets"
    Then Validate if the home screen is loaded
    Then Navigate to "Rank Sets" screen by clicking on the "Ranks" side menu
    Then Click on the "Add New Record" button in the "Ranks" module
    And Add "Belt" and save in the "RankSet" page for "Scenario-AddBeltForRankSet"
    
    Then Click on the "Sort Order Column" button in the "Ranks" module
    And Edit "Belt" and save in the "RankSet" page for "Scenario-EditBeltForRankSet"
    Then Verify the edited detail in the "RankSet" page for "Scenario-VerifyBeltAfterEdit"
    Then Logout of the application
    
  
    
    
    