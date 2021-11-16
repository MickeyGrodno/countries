package ru.shabalin;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report", "json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue = "ru/shabalin/steps",
        tags = "@API")

public class RunnerTest
{
}
