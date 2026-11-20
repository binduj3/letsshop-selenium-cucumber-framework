package io.github.binduj3.letsshop.runners;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

/**
 *   mvn test -Dcucumber.filter.tags="@smoke" -Dbrowser=firefox
 *
 * Re-running failures (replaces the old FailedRunner):
 *   mvn test -Dcucumber.features="$(paste -sd, target/rerun.txt)"
 */
@Suite
@IncludeEngines("cucumber")
@SelectPackages("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "io.github.binduj3.letsshop.steps")
public class TestRunner {
}
