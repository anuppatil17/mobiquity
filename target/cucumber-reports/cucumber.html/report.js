$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/End2End_Test.feature");
formatter.feature({
  "name": "End to End test mobiquity assignment",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@ApiAutomation"
    }
  ]
});
formatter.scenario({
  "name": "Fetch User Information and validate user details",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@ApiAutomation"
    },
    {
      "name": "@fetch_userDetails"
    }
  ]
});
formatter.step({
  "name": "Search for the user with username \"Delphine\" and validate user email id \"Chaim_McDermott@dana.io\"",
  "keyword": "Given "
});
formatter.match({
  "location": "fetchUserDetails.fetchUserInfo(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Fetch posts written by the user",
  "keyword": "When "
});
formatter.match({
  "location": "fetchUserDetails.fetchUserPost()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Fetch the comments and validate if the emails in the comment section are in the proper format",
  "keyword": "Then "
});
formatter.match({
  "location": "fetchUserDetails.VerifyUserComments()"
});
formatter.result({
  "status": "passed"
});
});