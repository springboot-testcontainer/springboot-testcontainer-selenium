package com.avides.springboot.sprintainer.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.avides.springboot.sprintainer.selenium.util.ApplicationIpDetector;

public class EmbeddedSeleniumContainerAutoConfigurationIT extends AbstractIT
{
    private static final String DEFAULT_TEST_URL = "https://www.avides.com/";

    @Autowired
    private RemoteWebDriver seleniumRemoteWebDriver;

    @Test
    public void testGetApplicationIP()
    {
        assertThat(ApplicationIpDetector.detect()).matches("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    }

    @Test
    public void testOpenUrl()
    {
        assertThat(seleniumRemoteWebDriver.getCurrentUrl()).isNotEqualTo(DEFAULT_TEST_URL);

        seleniumRemoteWebDriver.get(DEFAULT_TEST_URL);
        assertThat(seleniumRemoteWebDriver.getCurrentUrl()).isEqualTo(DEFAULT_TEST_URL);
    }

    @Configuration
    @EnableAutoConfiguration
    static class TestConfiguration
    {
        // nothing
    }
}
