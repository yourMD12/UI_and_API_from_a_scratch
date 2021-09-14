package com.bookit.step_definitions;

import com.bookit.utilities.BookItAPIutils;
import com.bookit.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class API_stepDef {

    String token;
    String emailGlobal;
    String passwordGlobal;
    public static String apiName;
    public static String apiRole;
    public static String apiTeam;
    public static String apiBatch;
    public static String apiCampus;

    @When("User logs in BookIt API using {string} and {string}")
    public void userLogsInBookItAPIUsingAnd(String email, String password) {
        token = BookItAPIutils.generateToken(email,password);
        emailGlobal = email;
        passwordGlobal = password;
    }

    @Then("User gets API information")
    public void userGetsAPIInformation() {
        Response response = given().accept(ContentType.JSON)
                .and().header("Authorization",token)
                .when()
                .get(ConfigurationReader.get("apiUrl") + "/api/students/me");

        System.out.println(response.statusCode());
        JsonPath jsonPath = response.jsonPath();

        apiName = jsonPath.getString("firstName") + " " + jsonPath.getString("lastName");
        apiRole = jsonPath.getString("role");

        String [] otherQueries = BookItAPIutils.getMyInfo(emailGlobal,passwordGlobal);

        apiTeam = otherQueries[0];
        apiBatch = otherQueries[1];
        apiCampus = otherQueries[2];

    }


}
