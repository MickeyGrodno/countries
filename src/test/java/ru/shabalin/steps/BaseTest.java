package ru.shabalin.steps;

import cucumber.api.Scenario;
import ru.shabalin.utils.StepData;

public class BaseTest {
    protected static final StepData stepData = new StepData();
    protected static Scenario scenario;
}
