### Get the code

Git:

    git clone https://github.com/serenity-bdd/serenity-cucumber4-starter.git
    cd serenity-cucumber4-starter


Or simply [download a zip](https://github.com/serenity-bdd/serenity-cucumber4-starter/archive/master.zip) file.


### Executing the tests
To run the sample project, you can either just run the `CucumberTestSuite` test runner class, or run either `mvn verify` or `gradle test` from the command line.

By default, the tests will run using Chrome. You can run them in Firefox by overriding the `driver` system property, e.g.
```json
$ mvn clean verify -Dwebdriver.driver=firefox
```
Run a particular feature tag from command line
```json
$ mvn clean verify -Dcucumber.options="--tags @maven"
```
Or 
```json
$ gradle clean test -Pwebdriver.=firefox
```

The test results will be recorded in the `target/site/serenity` directory.

### Simplified WebDriver configuration and other Serenity extras
The sample projects both use some Serenity features which make configuring the tests easier. In particular, Serenity uses the `serenity.conf` file in the `src/test/resources` directory to configure test execution options.

### Webdriver configuration
The WebDriver configuration is managed entirely from this file, as illustrated below:
```java
webdriver {
    driver = chrome
}
headless.mode = true

chrome.switches="""--start-maximized;--test-type;--no-sandbox;--ignore-certificate-errors;
                   --disable-popup-blocking;--disable-default-apps;--disable-extensions-file-access-check;
                   --incognito;--disable-infobars,--disable-gpu"""

```

The project also bundles some of the WebDriver binaries that you need to run Selenium tests in the `src/test/resources/webdriver` directories. These binaries are configured in the `drivers` section of the `serenity.conf` config file:
```json
drivers {
  windows {
    webdriver.chrome.driver = "src/test/resources/webdriver/windows/chromedriver.exe"
    webdriver.gecko.driver = "src/test/resources/webdriver/windows/geckodriver.exe"
  }
  mac {
    webdriver.chrome.driver = "src/test/resources/webdriver/mac/chromedriver"
    webdriver.gecko.driver = "src/test/resources/webdriver/mac/geckodriver"
  }
  linux {
    webdriver.chrome.driver = "src/test/resources/webdriver/linux/chromedriver"
    webdriver.gecko.driver = "src/test/resources/webdriver/linux/geckodriver"
  }
}
```
This configuration means that development machines and build servers do not need to have a particular version of the WebDriver drivers installed for the tests to run correctly.

### Environment-specific configurations
We can also configure environment-specific properties and options, so that the tests can be run in different environments. Here, we configure three environments, __dev__, _staging_ and _prod_, with different starting URLs for each:
```json
environments {
  default {
    webdriver.base.url = "https://duckduckgo.com"
  }
  dev {
    webdriver.base.url = "https://duckduckgo.com/dev"
  }
  staging {
    webdriver.base.url = "https://duckduckgo.com/staging"
  }
  prod {
    webdriver.base.url = "https://duckduckgo.com/prod"
  }
}
```
  
You use the `environment` system property to determine which environment to run against. For example to run the tests in the staging environment, you could run:
```json
$ mvn clean verify -Denvironment=staging
```
### Running Serenity on Zalenium

    Install docker and follow below steps

    # Pull docker-selenium
    docker pull elgalu/selenium

    # Pull Zalenium
    docker pull dosel/zalenium

    # Run it!
    docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos -e WAIT_FOR_AVAILABLE_NODES=false \
      --privileged dosel/zalenium start --desiredContainers 4

    You can see if the Zalenium server is running correctly by checking out the http://localhost:4444/grid/console


### Configuring your Serenity tests
To run your tests on the Selenium grid, you now just need to use the remote driver instead of the usual one. For example, in your serenity.conf file, you can configure your tests to run on the Zalenium server using Chrome like this:


```json
webdriver {
  driver = remote
  remote {
      url="http://localhost:4444/wd/hub"
      driver=chrome
  }
}

zalenium {
    screenResolution = "1920x1080"
    idleTimeout = 150
}

# With above configuration changes run test via Zalenium

mvn clean verify -Dcucumber.options="--tags @maven"

Tests will now show up on Zalenium dashboard http://localhost:4444/dashboard/#
```

If you are using the serenity.properties file, the configuration would look like this:

```json
webdriver.driver = remote
webdriver.remote.url = "http://localhost:4444/wd/hub"
webdriver.remote.driver = chrome
```
