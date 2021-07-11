package StepsFunctions;


import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fetchUserDetails {

    private static final String BASE_URL = "http://localhost:3000";

    private static List username;
    private static List mailId;
    private static List userId;

    private static List postID;
    private static List postTitle;
    private static List postBody;


    private static List commentID;
    private static List commentName;
    private static List<String> commentEmail;
    private static List<Boolean> validateEmails=new ArrayList<Boolean>();;

    private static List commentBody;

    private static Response response;
    private static String jsonString;
    private static final String EmailRegex = "^(.+)@(.+)$";


    @Given("Search for the user with username \"(.*?)\" and validate user email id \"(.*?)\"")
    public void fetchUserInfo(String user, String email) {
        try {

            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();
            response = request.get("/users?username="+user);
            Boolean userDetails;
            jsonString = response.asString();
            userId = JsonPath.from(jsonString).get("id");
            username = JsonPath.from(jsonString).get("username");
            mailId = JsonPath.from(jsonString).get("email");


            if (username.get(0).equals(user) && mailId.get(0).equals(email)) {

                System.out.println("*************** User Match with expected Information *****************");
                userDetails = true;
            } else {
                System.out.println("************** User Not Match with expected Information *******************");

                userDetails = false;
            }

            Assert.assertTrue(userDetails);


        } catch (Exception e) {
            System.out.println("Caught Exception During Fetch user details ->" + e);

        }
    }

    @When("Fetch posts written by the user")
    public void fetchUserPost() {
        try {

            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();
            response = request.get("/posts?userId=" + userId.get(0));
            Boolean userPostDetails;
            jsonString = response.asString();
            postID = JsonPath.from(jsonString).get("id");
            postTitle = JsonPath.from(jsonString).get("title");
            postBody = JsonPath.from(jsonString).get("body");


            if (postID.size() == postTitle.size() && postID.size() == postBody.size()) {

                System.out.println("********* Fetch user post information successfully *********");
                userPostDetails = true;
            } else {
                System.out.println("***************** User post not matched ******************");

                userPostDetails = false;
            }

            Assert.assertTrue(userPostDetails);


        } catch (Exception e) {
            System.out.println("Caught Exception During Fetch user post ->" + e);

        }
    }

    @When("Fetch the comments and validate if the emails in the comment section are in the proper format")
    public void VerifyUserComments() {
        try {

            System.out.println("Number of user post --->" + postID.size());


            for (int postIndex = 0; postIndex <= postID.size() - 1; postIndex++) {


                String fetchPostId = postID.get(postIndex).toString();

                RestAssured.baseURI = BASE_URL;
                RequestSpecification request = RestAssured.given();
                response = request.get("comments?postId=" + fetchPostId);
                jsonString = response.asString();
                commentID = JsonPath.from(jsonString).get("id");
                commentName = JsonPath.from(jsonString).get("name");
                commentEmail = JsonPath.from(jsonString).get("email");
                commentBody = JsonPath.from(jsonString).get("body");


                if (commentEmail.size() > 0) {

                    Pattern pattern = Pattern.compile(EmailRegex);
                    for (String value : commentEmail) {

                        Matcher matcher = pattern.matcher(value);

                        assert matcher.matches();
                        if (matcher.matches()) {
                            System.out.println("The Email address " + value + " is valid");

                            validateEmails.add(true);

                        } else {

                            System.out.println("The Email address " + value + " is invalid");
                            validateEmails.add(false);

                        }

                    }
                } else {
                    System.out.println("Comments not found.......for post ID => " + postIndex);
                }
            }
            Boolean validateCommentsEmail;

            System.out.println("Email Result -->"+validateEmails);
            if (!validateEmails.contains(false)) {

                System.out.println("********* Email validation passed ***********");
                validateCommentsEmail = true;
            } else {
                System.out.println("********** Email validation Failed  *************");

                validateCommentsEmail = false;
            }


            Assert.assertTrue(validateCommentsEmail);



    } catch(
    Exception e)

    {
        System.out.println("Caught Exception During Fetch user post ->" + e);

    }


}

}