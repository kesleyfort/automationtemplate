package br.com.template.configuration;

import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.RemoteWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;


@Configuration
@ComponentScan({"br.com.template"})
@PropertySource("classpath:configs/${env}/env.properties")
public class ProjectConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public WebDriverProvider webDriverProvider() {
        if(System.getProperty("remoteTesting")==null || System.getProperty("remoteTesting").equals("false")){
            WebDriverProvider webDriverProvider = new WebDriverFactory();
            if(System.getProperty("browser")==null){
                System.setProperty("browser", "chrome");
                System.setProperty("webdriver.chrome.driver", System.getProperty("webdriver"));
            }
            else if(System.getProperty("browser").equals("firefox")){
                System.setProperty("webdriver.gecko.driver", System.getProperty("webdriver"));
            }

            return webDriverProvider;
        }
        else{
            DesiredCapabilities capabilities = new DesiredCapabilities();
            if(System.getProperty("browser")==null){
                capabilities.setBrowserName("chrome");
            }
            else{
                capabilities.setBrowserName(System.getProperty("browser"));
            }
            if(System.getProperty("REMOTE_WEBDRIVER_URL")==null){
                String gridUrl = "https://selenium-hub.domain.com/wd/hub";
                System.setProperty("REMOTE_WEBDRIVER_URL", gridUrl);
            }
            return new RemoteWebDriverProvider(capabilities);
        }
    }

    @Bean
    public WebDriverScreenshotOnFailure screenshotOnFailureDriver() {
        return new WebDriverScreenshotOnFailure(webDriverProvider(), new StoryReporterBuilder(), "test-results/jbehave/view/screenshots/failed-scenario-{1}.png");
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
