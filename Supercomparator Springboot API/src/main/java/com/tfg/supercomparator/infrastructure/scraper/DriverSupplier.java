package com.tfg.supercomparator.infrastructure.scraper;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public abstract class DriverSupplier {
    public FirefoxDriver getDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
        return new FirefoxDriver(firefoxOptions);
    }
}
