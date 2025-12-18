package qa.guru.owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.guru.owner.config.LaunchModeConfig;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("launch_mode")
public class LaunchModeTest {
    private static final String LOCAL_MODE = "local";
    private static final String REMOTE_MODE = "remote";

    @Test
    public void testLaunchLocal() {

        if (LOCAL_MODE.equals(System.getProperty("launchMode"))) {
            System.setProperty("launch", LOCAL_MODE);

            LaunchModeConfig config = ConfigFactory.create(LaunchModeConfig.class, System.getProperties());

            assertThat(config.getBrowserName()).isEqualTo("CHROME");
            assertThat(config.getBrowserVersion()).isEqualTo("143.0"); // версия для локального запуска
            assertThat(config.getUrl()).isEqualTo("https://arcadia.spb.ru");
            assertThat(config.getRemote()).isEqualTo("http://localhost:4444");
            System.out.println("Launch mode is: " + System.getProperty("launchMode"));

        } else if (REMOTE_MODE.equals(System.getProperty("launchMode"))) {
            System.setProperty("launch", REMOTE_MODE);

            LaunchModeConfig config = ConfigFactory.create(LaunchModeConfig.class, System.getProperties());

            assertThat(config.getBrowserName()).isEqualTo("CHROME");
            assertThat(config.getBrowserVersion()).isEqualTo("128.0"); // версия для удаленного запуска
            assertThat(config.getUrl()).isEqualTo("https://arcadia.spb.ru");
            assertThat(config.getRemote()).isEqualTo("https://user1:1234@selenoid.autotests.cloud/wd/hub");
        } else {
            throw new IllegalArgumentException("Unknown launch mode: " + System.getProperty("launchMode")); // обработка ошибочных ситуаций
        }
    }
}