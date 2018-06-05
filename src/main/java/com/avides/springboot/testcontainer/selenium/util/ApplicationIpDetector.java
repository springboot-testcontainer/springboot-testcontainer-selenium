package com.avides.springboot.testcontainer.selenium.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link ApplicationIpDetector} is used to determine the IP of the application under test.
 *
 * @since 0.0.1-RELEASE
 */
@UtilityClass
@Slf4j
public class ApplicationIpDetector
{
    private final Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    /**
     * Determines the IP of the application under test. First try this by looking up the {@link InetAddress} for {@link NetworkInterface} with IPv4 pattern. If
     * there is no network interface with that pattern try to detect the Docker host IP. If that fails throw an exception.
     *
     * @since 0.0.1-RELEASE
     */
    public String detect()
    {
        return detectHostIPv4().orElseThrow(() -> new RuntimeException("Failed to determine application IP"));
    }

    /**
     * Detects the host IPv4 address.
     */
    private Optional<String> detectHostIPv4()
    {
        try
        {
            return Collections.list(NetworkInterface.getNetworkInterfaces())
                    .stream()
                    .map(NetworkInterface::getInetAddresses)
                    .map(Collections::list)
                    .flatMap(ArrayList<InetAddress>::stream)
                    .filter(inetAddress -> inetAddress.getHostAddress().matches(PATTERN.pattern()))
                    .map(InetAddress::getHostAddress)
                    .findFirst();
        }
        catch (SocketException e)
        {
            log.error("Failed to determine IP via network interface name", e);
        }
        return Optional.empty();
    }
}
