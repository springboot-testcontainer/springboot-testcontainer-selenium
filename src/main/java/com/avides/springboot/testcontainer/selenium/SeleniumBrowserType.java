package com.avides.springboot.testcontainer.selenium;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SeleniumBrowserType
{
    CHROME("selenium/standalone-chrome-debug", "3.12.0");

    private final String dockerImage;

    private final String dockerImageVersion;

    public String getDockerImage()
    {
        return dockerImage + ":" + dockerImageVersion;
    }

    public static SeleniumBrowserType getByProperty(String browserNameProperty)
    {
        return SeleniumBrowserType.valueOf(browserNameProperty.toUpperCase());
    }
}
