# springboot-testcontainer-selenium

[![Maven Central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/avides/springboot/testcontainer/springboot-testcontainer-selenium/maven-metadata.xml.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.avides.springboot.testcontainer%22%20AND%20a%3A%22springboot-testcontainer-selenium%22)
[![Build](https://github.com/springboot-testcontainer/springboot-testcontainer-selenium/workflows/release/badge.svg)](https://github.com/springboot-testcontainer/springboot-testcontainer-selenium/actions)
[![Nightly build](https://github.com/springboot-testcontainer/springboot-testcontainer-selenium/workflows/nightly/badge.svg)](https://github.com/springboot-testcontainer/springboot-testcontainer-selenium/actions)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=springboot-testcontainer_springboot-testcontainer-selenium&metric=alert_status)](https://sonarcloud.io/dashboard?id=springboot-testcontainer_springboot-testcontainer-selenium)

### Dependency
```xml
<dependency>
	<groupId>com.avides.springboot.testcontainer</groupId>
	<artifactId>springboot-testcontainer-selenium</artifactId>
	<version>1.0.0-RC1</version>
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
<!-- Testcontainers -->
<logger name="com.github.dockerjava.jaxrs" level="WARN" />
<logger name="com.github.dockerjava.core.command" level="WARN" />
<logger name="org.apache.http" level="WARN" />
<logger name="org.openqa.selenium" level="WARN" />
```

## Labels
The container exports multiple labels to analyze running testcontainers:
- `TESTCONTAINER_SERVICE=selenium`
- `TESTCONTAINER_IMAGE=${embedded.container.selenium.docker-image}`
- `TESTCONTAINER_STARTED=$currentTimestamp`
