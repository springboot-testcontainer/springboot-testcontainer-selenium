package com.avides.springboot.testcontainer.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SeleniumPropertiesTest
{
    @Test
    public void testDefaults()
    {
        SeleniumProperties properties = new SeleniumProperties();
        assertTrue(properties.isEnabled());
        assertEquals(30, properties.getStartupTimeout());
        assertNull(properties.getDockerImage());

        assertEquals(4444, properties.getPort());
        assertEquals("chrome", properties.getBrowserName());
    }
}
