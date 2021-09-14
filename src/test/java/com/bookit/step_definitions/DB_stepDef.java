package com.bookit.step_definitions;

import com.bookit.utilities.DBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import javax.management.relation.Role;
import java.util.Map;

public class DB_stepDef {

    Map<String,Object> dbMap;
    public static String dbName;
    public static String dbRole;
    public static String dbTeam;
    public static String dbBatch;
    public static String dbCampus;

    @When("User sends a query to DataBase with {string}")
    public void userSendsAQueryToDataBaseWith(String email) {
        String query = "select firstname, lastname,role,name, location, batch_number\n" +
                "from users u join(select t.id,name,batch_number,location from\n" +
                "team t join campus c on t.campus_id=c.id)r\n" +
                "on u.team_id=r.id \n" +
                "where u.email='" + email + "';";

        dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);
    }

    @Then("User gets DataBase information")
    public void userGetsDataBaseInformation() {
        dbName = dbMap.get("firstname") + " " + dbMap.get("lastname");
        dbCampus = (String) dbMap.get("location");
        dbBatch = "#" + dbMap.get("batch_number");
        dbRole = (String) dbMap.get("role");
        dbTeam = (String) dbMap.get("name");
    }

    @Then("All information from environments must match")
    public void allInformationFromEnvironmentsMustMatch() {
        System.out.println(UI_stepDef.UIname);
        System.out.println(API_stepDef.apiName);
        System.out.println(dbName);

        Assert.assertTrue(UI_stepDef.UIname.equals(dbName) && API_stepDef.apiName.equals(dbName));
        Assert.assertTrue(UI_stepDef.UIbatch.equals(dbBatch) && API_stepDef.apiBatch.equals(dbBatch));
        Assert.assertTrue(UI_stepDef.UIrole.equals(dbRole) && API_stepDef.apiRole.equals(dbRole));
        Assert.assertTrue(UI_stepDef.UIcampus.equals(dbCampus) && API_stepDef.apiCampus.equals(dbCampus));
        Assert.assertTrue(UI_stepDef.UIteam.equals(dbTeam) && API_stepDef.apiTeam.equals(dbTeam));

    }
}
