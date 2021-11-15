package steps;

import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.runtime.junit.Assertions;
import io.restassured.RestAssured;
import ru.shabalin.utils.Utils;
import ru.shabalin.utils.VariableContainer;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class AllSteps {
    private static Scenario scenario;
    private static VariableContainer variableContainer = new VariableContainer();

    @Given("^a request for a URL \"([^\"]*)\" with parameter \"([^\"]*)\" has been sent. List of bordering country codes saved to variable \"([^\"]*)\"$")
    public void requestInUrl(String url, String countryCode, String countriesVar) {
        String requestUrl = Utils.urlMaker(url, countryCode);
//        scenario.write(String.format("Request for URL %s sent", RestAssured.baseURI+requestUrl));
        List<List<String>> borderCodes = Utils.doGetRequestAndGetResponseData(requestUrl);
        variableContainer.setVar(countriesVar, borderCodes);
    }


    @And("^check that the \"([^\"]*)\" value is present in the borders of the list countries \"([^\"]*)\"$")
    public void checkThatTheValueIsPresentInTheBordersOfTheListCountries(String countryCode, String countriesVar) throws Throwable {
        boolean notListed = false;
        String failureMessage = String.format("Lists that do not contain country code '%s' :\n", countryCode);
        List<List<String>> countryCodes = (List<List<String>>) variableContainer.getVar(countriesVar);
        String requestUrl = Utils.urlMaker("getCurrentCountryInfo", countryCodes);
        List<List<String>> borderingСountriesList = Utils.doGetRequestAndGetResponseData(requestUrl);
        for (List<String> list : borderingСountriesList) {
            if(!list.contains(countryCode)) {
                notListed = true;
                failureMessage += list;
            }
        }
        assertFalse(failureMessage,notListed);
    }
}
