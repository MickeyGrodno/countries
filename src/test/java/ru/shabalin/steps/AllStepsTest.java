package ru.shabalin.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import ru.shabalin.utils.Utils;

import java.util.List;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertFalse;

public class AllStepsTest extends BaseTest {

    @Given("^a request for a URL \"([^\"]*)\" with parameter \"([^\"]*)\" has been sent\\. List of bordering country codes saved to variable \"([^\"]*)\"$")
    public void requestOnUrlAndSaveBordersFromResponse(String url, String countryCode, String countriesVar) {
        String requestUrl = Utils.urlMaker(url, countryCode);
        List<List<String>> borderCodes = Utils.doGetRequestAndGetResponseBordersData(requestUrl);
        scenario.write("The response body with parameter 'borders' is stored in a variable " + countriesVar + "\n" + borderCodes);
        variableContainer.setVar(countriesVar, borderCodes);
    }

    @Then("^check that the \"([^\"]*)\" value is present in the borders of the list countries \"([^\"]*)\"$")
    public void checkBordersCountries(String countryCode, String countriesVar) throws Throwable {
        boolean notListed = false;
        StringBuilder failureMessage = new StringBuilder(String.format("Lists that do not contain country code '%s' :\n", countryCode));
        List<List<String>> countryCodes = (List<List<String>>) variableContainer.getVar(countriesVar);
        String requestUrl = Utils.urlMaker("getCurrentCountryInfo", countryCodes.get(0));
        List<List<String>> borderingCountriesList = Utils.doGetRequestAndGetResponseBordersData(requestUrl);
        for (int i = 0; i < borderingCountriesList.size(); i++) {
            if (!borderingCountriesList.get(i).contains(countryCode)) {
                notListed = true;
                failureMessage.append(String.format("Code of the country - %s - %s\n", countryCodes.get(0).get(i), borderingCountriesList.get(i)));
            }
        }
        assertFalse(failureMessage.toString(), notListed);
    }

    @Given("^a request for a URL \"([^\"]*)\" with parameter \"([^\"]*)\" has been sent\\. The response body is stored in a variable \"([^\"]*)\"$")
    public void doRequestAndSaveResponse(String url, String countryCode, String responseVarName) {
        Response response = Utils.doGetRequestAndGetResponseBody(Utils.urlMaker(url, countryCode));
        scenario.write("The response body is stored in a variable " + responseVarName + "\n" + response.prettyPrint());
        variableContainer.setVar(responseVarName, response);
    }

    @Then("^JSON response schema from variable \"([^\"]*)\" must match \"([^\"]*)\"$")
    public void jsonResponseSchemaFromVariableMustMatch(String responseVarName, String schemaPath) {
        Response response = (Response) variableContainer.getVar(responseVarName);
        if (!schemaPath.equals("empty")) {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/" + schemaPath + ".json"));
        }
    }
}
