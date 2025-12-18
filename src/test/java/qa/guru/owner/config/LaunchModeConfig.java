package qa.guru.owner.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${launch}.properties",
        "classpath:local.properties"

})
public interface LaunchModeConfig extends Config {

    @Key("webdriver.browser")
    String getBrowserName();

    @Key("webdriver.browserVersion")
    String getBrowserVersion();

    @Key("webdriver.baseUrl")
    String getUrl();

    @Key("webdriver.remote")
    String getRemote();
}
