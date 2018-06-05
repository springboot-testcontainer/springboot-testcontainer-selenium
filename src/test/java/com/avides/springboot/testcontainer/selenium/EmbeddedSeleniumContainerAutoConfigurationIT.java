package com.avides.springboot.testcontainer.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

public class EmbeddedSeleniumContainerAutoConfigurationIT extends AbstractIT
{
    private static final String DEFAULT_TEST_URL = "https://www.avides.com/";

    @Autowired
    private SeleniumContainer embeddedSeleniumContainer;

    @Test
    public void testGetApplicationUrl()
    {
        assertThat(embeddedSeleniumContainer.getApplicationUrl()).isNotNull();
        assertThat(embeddedSeleniumContainer.getApplicationUrl()).startsWith("http://");
        assertThat(embeddedSeleniumContainer.getApplicationUrl()).contains(":");
        assertThat(embeddedSeleniumContainer.getApplicationUrl()).endsWith("/");
    }

    @Test
    public void testOpenUrl()
    {
        assertThat(embeddedSeleniumContainer.getRemoteWebDriver().getCurrentUrl()).isNotEqualTo(DEFAULT_TEST_URL);

        embeddedSeleniumContainer.getRemoteWebDriver().get(DEFAULT_TEST_URL);
        assertThat(embeddedSeleniumContainer.getRemoteWebDriver().getCurrentUrl()).isEqualTo(DEFAULT_TEST_URL);
    }

    @Configuration
    @EnableAutoConfiguration
    static class TestConfiguration
    {
        // nothing
    }
}
