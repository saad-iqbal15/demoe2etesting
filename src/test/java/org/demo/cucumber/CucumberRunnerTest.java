package org.demo.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@Scenario1",
                plugin = {"pretty", "json:target/cucumber.json"})
public class CucumberRunnerTest {
}
