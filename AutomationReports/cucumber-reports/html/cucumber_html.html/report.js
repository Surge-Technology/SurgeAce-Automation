$(document).ready(function() {
	var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report')); formatter.uri("src/test/resources/features/SurgeAceFirst.feature");
	formatter.feature({
		"line": 1,
		"name": "SurgeAceFirst",
		"description": "",
		"id": "surgeacefirst",
		"keyword": "Feature"
	});
	formatter.before({
		"duration": 10828291700,
		"status": "passed"
	});
	formatter.scenario({
		"line": 6,
		"name": "Add Edit and Delete Inquiry",
		"description": "",
		"id": "surgeacefirst;add-edit-and-delete-inquiry",
		"type": "scenario",
		"keyword": "Scenario",
		"tags": [
			{
				"line": 3,
				"name": "@Background"
			},
			{
				"line": 5,
				"name": "@InquiryAddEditDelete"
			}
		]
	});
	formatter.step({
		"line": 7,
		"name": "Login with the username and password in the \"SurgeAceLogin\" page for \"Scenario-Inquiry\"",
		"keyword": "Given "
	});
	formatter.step({
		"line": 8,
		"name": "Validate if the home screen is loaded",
		"keyword": "Then "
	});
	formatter.step({
		"line": 9,
		"name": "Navigate to \"Inquiry\" screen by clicking on the \"Students\" side menu",
		"keyword": "Then "
	});
	formatter.step({
		"line": 10,
		"name": "Click on the \"Add Inquiry\" button",
		"keyword": "Then "
	});
	formatter.step({
		"line": 11,
		"name": "Add Inquiry and save in the \"CreateInquiry\" page for \"Scenario-AddInquiry\"",
		"keyword": "And "
	});
	formatter.match({
		"arguments": [
			{
				"val": "SurgeAceLogin",
				"offset": 45
			},
			{
				"val": "Scenario-Inquiry",
				"offset": 70
			}
		],
		"location": "StepDefinition.loginApp(String,String)"
	});
	formatter.result({
		"duration": 894204600,
		"status": "passed"
	});
	formatter.match({
		"location": "StepDefinition.homeScreen()"
	});
	formatter.result({
		"duration": 6392505100,
		"status": "passed"
	});
	formatter.match({
		"arguments": [
			{
				"val": "Inquiry",
				"offset": 13
			},
			{
				"val": "Students",
				"offset": 49
			}
		],
		"location": "StepDefinition.navigateToSideMenu(String,String)"
	});
	formatter.result({
		"duration": 8387914900,
		"status": "passed"
	});
	formatter.match({
		"arguments": [
			{
				"val": "Add Inquiry",
				"offset": 14
			}
		],
		"location": "StepDefinition.addScreen(String)"
	});
	formatter.result({
		"duration": 3205823600,
		"status": "passed"
	});
	formatter.match({
		"arguments": [
			{
				"val": "CreateInquiry",
				"offset": 29
			},
			{
				"val": "Scenario-AddInquiry",
				"offset": 54
			}
		],
		"location": "StepDefinition.addInq(String,String)"
	});
