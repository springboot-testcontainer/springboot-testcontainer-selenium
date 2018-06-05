package com.avides.springboot.testcontainer.selenium;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.core.env.ConfigurableEnvironment;

import com.avides.springboot.testcontainer.common.container.AbstractBuildingEmbeddedContainer;

import lombok.Getter;
import lombok.SneakyThrows;

public class DefaultSeleniumContainer extends AbstractBuildingEmbeddedContainer<SeleniumProperties> implements SeleniumContainer
{
    @Getter
    private RemoteWebDriver remoteWebDriver;

    public DefaultSeleniumContainer(String service, ConfigurableEnvironment environment, SeleniumProperties properties)
    {
        super(service, environment, properties);
    }

    @SneakyThrows
    @Override
    protected boolean isContainerReady(SeleniumProperties properties)
    {
        remoteWebDriver = new RemoteWebDriver(getUrl(), getCapabilities(SeleniumBrowserType.getByProperty(properties.getBrowserName())));
        return true;
    }

    @SneakyThrows
    private URL getUrl()
    {
        return new URL("http", getContainerHost(), getContainerPort(properties.getPort()), "/wd/hub");
    }

    /**
     * Resolve right {@link Capabilities} by given {@link SeleniumBrowserType} from `application.properties`.
     *
     * @param browserType
     *            `embedded.container.selenium.browser-name` from `application.properties`.
     * @return DesiredCapabilities
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
