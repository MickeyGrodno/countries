package steps;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import ru.shabalin.utils.Utils;

public class Hooks {
    protected static Scenario scenario;

    @Before
    public void config() {
        RestAssured.baseURI = Utils.loadProperty("baseUrl");
    }
}
