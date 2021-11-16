package ru.shabalin.steps;

import cucumber.api.Scenario;
import ru.shabalin.utils.VariableContainer;

public class BaseTest {
    protected static final VariableContainer variableContainer = new VariableContainer();
    protected static Scenario scenario;
}
