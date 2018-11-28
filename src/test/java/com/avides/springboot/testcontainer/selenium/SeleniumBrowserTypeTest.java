package com.avides.springboot.testcontainer.selenium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

public class SeleniumBrowserTypeTest
{
    @Test
    public void testGetByProperty()
    {
        assertThat(SeleniumBrowserType.getByProperty("chrome")).isEqualTo(SeleniumBrowserType.CHROME);
    }

    @Test
    public void testGetByPropertyWithUnsupported()
    {
        assertThatThrownBy(() -> SeleniumBrowserType.getByProperty("firefox")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No enum constant com.avides.springboot.testcontainer.selenium.SeleniumBrowserType.FIREFOX");
    }

    @Test
    public void testGetDockerImage()
    {
        assertThat(SeleniumBrowserType.CHROME.getDockerImage()).isEqualTo("selenium/standalone-chrome-debug:3.141.59");
    }
}
