package com.avides.springboot.testcontainer.selenium;

import lombok.RequiredArgsConstructor;

/**
 * Selenium container supported browsers.
 */
@RequiredArgsConstructor
public enum SeleniumBrowserType
{
    /**
     * Chrome with debug.
     */
    CHROME("selenium/standalone-chrome-debug", "3.141.59");

    private final String dockerImage;

    private final String dockerImageVersion;

    /**
     * Will concat the docker image name with the version (eg. selenium/standalone-chrome-debug:latest).
     *
     * @return docker image name with version
     */
    public String getDockerImage()
    {
        return dockerImage + ":" + dockerImageVersion;
    }

    /**
     * Will resolve the {@link SeleniumBrowserType} by name as {@link String}.
     * 
     * @param browserNameProperty browser name as {@link String}
     * @return resolved {@link SeleniumBrowserType} or throws {@link IllegalArgumentException} if no enum found
     */
    public static SeleniumBrowserType getByProperty(String browserNameProperty)
    {
        return SeleniumBrowserType.valueOf(browserNameProperty.toUpperCase());
    }
}
