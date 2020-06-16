# springtainer-selenium

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.avides.springboot.springtainer/springtainer-selenium/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.avides.springboot.springtainer/springtainer-selenium)
[![Build](https://github.com/springtainer/springtainer-selenium/workflows/release/badge.svg)](https://github.com/springtainer/springtainer-selenium/actions)
[![Nightly build](https://github.com/springtainer/springtainer-selenium/workflows/nightly/badge.svg)](https://github.com/springtainer/springtainer-selenium/actions)
[![Coverage report](https://sonarcloud.io/api/project_badges/measure?project=springtainer_springtainer-selenium&metric=coverage)](https://sonarcloud.io/dashboard?id=springtainer_springtainer-selenium)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=springtainer_springtainer-selenium&metric=alert_status)](https://sonarcloud.io/dashboard?id=springtainer_springtainer-selenium)
[![Technical dept](https://sonarcloud.io/api/project_badges/measure?project=springtainer_springtainer-selenium&metric=sqale_index)](https://sonarcloud.io/dashboard?id=springtainer_springtainer-selenium)

## Attention
This project will only receive dependency updates so long no code changes needed!

### Dependency
```xml
<dependency>
	<groupId>com.avides.springboot.springtainer</groupId>
	<artifactId>springtainer-selenium</artifactId>
	<version>1.1.0</version>
	<scope>test</scope>
</dependency>
```

### Configuration
Properties consumed (in `bootstrap.properties`):
- `embedded.container.selenium.enabled` (default is `true`)
- `embedded.container.selenium.startup-timeout` (default is `30`)
- `embedded.container.selenium.browser-name` (default is `chrome`)
- `embedded.container.selenium.browser-docker-image-version` (default is `3.141.59`)

## Example
```java
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TestClass
{
    @Autowired
    private RemoteWebDriver embeddedSeleniumRemoteWebDriver;

    @LocalServerPort
    private int port;

    @Test
    public void testSomething()
    {
        String url = "http://" + ApplicationIpDetector.detect() + ":" + Integer.toString(port);
        embeddedSeleniumRemoteWebDriver.get(url);

        // ...
    }
}
```

## Supported Browsers
| Browser Name  | Docker-Image |
| ------------- | ------------- |
| Chrome  | selenium/standalone-chrome-debug  |

## Logging
To reduce logging insert this into the logback-configuration:
```xml
<!-- Springtainer -->
<logger name="com.github.dockerjava.jaxrs" level="WARN" />
<logger name="com.github.dockerjava.core.command" level="WARN" />
<logger name="org.apache.http" level="WARN" />
<logger name="org.openqa.selenium" level="WARN" />
```

## Labels
The container exports multiple labels to analyze running springtainers:
- `SPRINGTAINER_SERVICE=selenium`
- `SPRINGTAINER_IMAGE=${embedded.container.selenium.docker-image}`
- `SPRINGTAINER_STARTED=$currentTimestamp`
