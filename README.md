# springboot-testcontainer-selenium

[![Maven Central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/avides/springboot/testcontainer/springboot-testcontainer-selenium/maven-metadata.xml.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.avides.springboot.testcontainer%22%20AND%20a%3A%22springboot-testcontainer-selenium%22)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4d64ab37576249f694bbb42e7d2cab56)](https://www.codacy.com/app/avides-builds/springboot-testcontainer-selenium?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=springboot-testcontainer/springboot-testcontainer-selenium&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/springboot-testcontainer/springboot-testcontainer-selenium/badge.svg)](https://coveralls.io/r/springboot-testcontainer/springboot-testcontainer-selenium)
[![Build Status](https://travis-ci.org/springboot-testcontainer/springboot-testcontainer-selenium.svg?branch=master)](https://travis-ci.org/springboot-testcontainer/springboot-testcontainer-selenium)

### Dependency
```xml
<dependency>
	<groupId>com.avides.springboot.testcontainer</groupId>
	<artifactId>springboot-testcontainer-selenium</artifactId>
	<version>0.0.1-RC2</version>
	<scope>test</scope>
</dependency>
```

### Configuration
Properties consumed (in `bootstrap.properties`):
- `embedded.container.selenium.enabled` (default is `true`)
- `embedded.container.selenium.startup-timeout` (default is `30`)
- `embedded.container.selenium.browser-name` (default is `chrome`)
- `embedded.container.selenium.browser-docker-image-version` (default is `3.12.0`)

## Example
```java
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.avides.springboot.testcontainer.selenium.EmbeddedSeleniumContainerAutoConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = EmbeddedSeleniumContainerAutoConfiguration.class)
public class TestClass
{
    @LocalServerPort
    private int port;

    @Test
    public void testSomething()
    {
        String url = "http://" + ApplicationIpDetector.detect() + ":" + Integer.toString(port);
        embeddedSeleniumContainer.getRemoteWebDriver().get(url);

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
