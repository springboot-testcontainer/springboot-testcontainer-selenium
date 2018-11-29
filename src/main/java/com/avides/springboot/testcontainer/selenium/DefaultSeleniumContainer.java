package com.avides.springboot.testcontainer.selenium;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.core.env.ConfigurableEnvironment;

import com.avides.springboot.testcontainer.common.container.AbstractBuildingEmbeddedContainer;

import lombok.Getter;
import lombok.SneakyThrows;

/**
 * Implementation of {@link SeleniumContainer}.
 */
public class DefaultSeleniumContainer extends AbstractBuildingEmbeddedContainer<SeleniumProperties> implements SeleniumContainer
{
    @Getter
    private RemoteWebDriver remoteWebDriver;

    /**
     * Constructs the {@link DefaultSeleniumContainer}.
     * 
     * @param service name of the service
     * @param environment {@link ConfigurableEnvironment}
     * @param properties {@link SeleniumProperties}
     */
    public DefaultSeleniumContainer(String service, ConfigurableEnvironment environment, SeleniumProperties properties)
    {
        super(service, environment, properties);
    }

    /**
     * Check if container is ready to use.
     * 
     * @param properties {@link SeleniumProperties}
     * @return true if container is ready, otherwise false
     */
    @SneakyThrows
    @Override
    protected boolean isContainerReady(SeleniumProperties properties)
    {
        remoteWebDriver = new RemoteWebDriver(getUrl(), getCapabilities(SeleniumBrowserType.getByProperty(properties.getBrowserName())));
        return true;
    }

    /**
     * Build the {@link URL} for accessing the {@link SeleniumContainer}.
     * 
     * @return URL for accessing the {@link SeleniumContainer}
     */
    @SneakyThrows
    private URL getUrl()
    {
        return new URL("http", getContainerHost(), getContainerPort(properties.getPort()), "/wd/hub");
    }

    /**
     * Resolve right {@link Capabilities} by given {@link SeleniumBrowserType} from `application.properties`.
     *
     * @param browserType `embedded.container.selenium.browser-name` from `application.properties`.
     * @return DesiredCapabilities or throws {@link UnsupportedOperationException} if not resolvable
     */
    private Capabilities getCapabilities(SeleniumBrowserType browserType)
    {
        if (browserType == SeleniumBrowserType.CHROME)
        {
            return DesiredCapabilities.chrome();
        }

        throw new UnsupportedOperationException(properties.getBrowserName() + " is not supported");
    }
}
