package Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features",glue= {"StepDefination"},monochrome=true,
plugin= {"pretty","json:target/cucumber.json"},
tags= {"@Regression"})
public class Runner { 

}
