package steps;

import cucumber.api.Scenario;
import utils.VariableContainer;

public class BaseTest {
    protected static final VariableContainer variableContainer = new VariableContainer();
    protected static Scenario scenario;
}
