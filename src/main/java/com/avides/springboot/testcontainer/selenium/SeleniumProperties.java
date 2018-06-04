package com.avides.springboot.testcontainer.selenium;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.avides.springboot.testcontainer.common.container.AbstractEmbeddedContainerProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SeleniumProperties is used to connect the `application.properties` with Spring Boot.
 *
 * @since 0.0.1-RELEASE
 */
@ConfigurationProperties("embedded.container.selenium")
@Getter
@Setter
@ToString(callSuper = true)
public class SeleniumProperties extends AbstractEmbeddedContainerProperties
{
    /**
     * Spring context bean name.
     *
     * @since 0.0.1-RELEASE
     */
    public static final String BEAN_NAME = "embeddedSeleniumContainer";

    /**
     * Selenium container port.
     *
     * @since 0.0.1-RELEASE
     */
    private int port = 4444;

    /**
     * Default browser.
     *
     * @since 0.0.1-RELEASE
     */
    private String browserName = "chrome";

    /**
     * Default browser docker image version.
     *
     * @since 0.0.1-RELEASE
     */
    private String browserDockerImageVersion = "3.12.0";
}
