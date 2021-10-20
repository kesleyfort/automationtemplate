package br.com.template.configuration;

import org.jbehave.web.selenium.DelegatingWebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Locale;

public class WebDriverFactory extends DelegatingWebDriverProvider {

    public WebDriverFactory(){
    }

    public void initialize() {
        WebDriverFactory.Browser browser =
                Browser.valueOf(Browser.class,
                System.getProperty("browser", "firefox").toUpperCase(this.usingLocale()));
        this.delegate.set(this.createDriver(browser));
    }

    private WebDriver createDriver(WebDriverFactory.Browser browser) {
        switch(browser) {
            case CHROME:
            default:
                return this.createChromeDriver();
            case FIREFOX:
                return this.createFirefoxDriver();
            case IE:
                return this.createInternetExplorerDriver();
        }
    }

    protected ChromeDriver createChromeDriver() {
        return new ChromeDriver(getDefaultChromeOptions());
    }

    protected FirefoxDriver createFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("permissions.default.geo", 1);
        return new FirefoxDriver(firefoxOptions);
    }

    protected InternetExplorerDriver createInternetExplorerDriver( ) {
        return new InternetExplorerDriver();
    }

    protected Locale usingLocale() {
        return Locale.getDefault();
    }

    public enum Browser {
        CHROME,
        FIREFOX,
        IE;

        Browser() {
        }
    }

    private ChromeOptions getDefaultChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito", "--disable-extensions");
//                options.addArguments("--headless", "--incognito", "--disable-extensions");
        return options;
    }
}
