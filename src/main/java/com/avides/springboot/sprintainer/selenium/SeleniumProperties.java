package com.avides.springboot.sprintainer.selenium;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.avides.springboot.springtainer.common.container.AbstractEmbeddedContainerProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SeleniumProperties is used to connect the `application.properties` with Spring Boot.
 */
@ConfigurationProperties("embedded.container.selenium")
@Getter
@Setter
@ToString(callSuper = true)
public class SeleniumProperties extends AbstractEmbeddedContainerProperties
{
    /**
     * Spring context bean name for {@link SeleniumContainer}.
     */
    public static final String BEAN_NAME = "embeddedSeleniumContainer";

    /**
     * Spring context bean name for {@link org.openqa.selenium.remote.RemoteWebDriver}.
     */
    public static final String REMOTE_WEB_DRIVER_BEAN_NAME = "embeddedSeleniumRemoteWebDriver";

    /**
     * Selenium container port.
     */
    private int port = 4444;

    /**
     * Default browser.
     */
    private String browserName = "chrome";

    /**
     * Default browser docker image version.
     */
    private String browserDockerImageVersion = "3.141.59";
}
