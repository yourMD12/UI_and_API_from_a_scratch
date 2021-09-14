package com.bookit.step_definitions;

import com.bookit.pages.SelfPage;
import com.bookit.pages.SignInPage;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.SelfDescribing;

import javax.swing.*;

public class UI_stepDef {

    SignInPage signInPage = new SignInPage();
    SelfPage selfPage = new SelfPage();
    public static String UIname;
    public static String UIrole;
    public static String UIcampus;
    public static String UIteam;
    public static String UIbatch;

    @Given("User logs in with {string} and {string}")
    public void User_logs_in_with(String email, String password) {
        Driver.get().get(ConfigurationReader.get("url")); // Driver class line 34 already calls for the browser type
        signInPage.email.sendKeys(email);
        signInPage.password.sendKeys(password);
        signInPage.signInButton.click();
    }

    @When("User navigates to mySelf page")
    public void User_navigates_to_mySelf_page() throws InterruptedException {
        selfPage.goToSelf();
        Thread.sleep(3000);
    }

    @Then("User gets the UI info")
    public void User_gets_the_UI_info() {
        UIname = selfPage.name.getText();
        UIbatch = selfPage.batch.getText();
        UIcampus = selfPage.campus.getText();
        UIrole = selfPage.role.getText();
        UIteam = selfPage.team.getText();
    }

}
