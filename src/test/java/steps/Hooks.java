package steps;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import utils.Utils;

public class Hooks extends BaseTest{

    @Before
    public void config(Scenario scenario) {
        RestAssured.baseURI = Utils.loadProperty("baseUrl");
        BaseTest.scenario = scenario;
    }
}
