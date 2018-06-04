package com.avides.springboot.testcontainer.selenium;

import org.openqa.selenium.remote.RemoteWebDriver;

public interface SeleniumContainer
{
    String getApplicationUrl();

    RemoteWebDriver getRemoteWebDriver();
}
