package com.avides.springboot.testcontainer.selenium;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * SeleniumContainer is used to get access to the {@link RemoteWebDriver}.
 */
public interface SeleniumContainer
{
    /**
     * {@link RemoteWebDriver}
     * 
     * @return the connected {@link RemoteWebDriver}
     */
    RemoteWebDriver getRemoteWebDriver();
}
