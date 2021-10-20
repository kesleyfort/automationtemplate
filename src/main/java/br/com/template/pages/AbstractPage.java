package br.com.template.pages;

import org.apache.log4j.Logger;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.util.List;

public abstract class AbstractPage {

    protected Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    protected WebDriverProvider webDriverProvider;
    @Autowired
    Environment env;
    public void waitPageLoad() {
        WebDriverWait wait = new WebDriverWait(webDriverProvider.get(), 30);
        wait.until(ExpectedConditions.visibilityOfAllElements(elementsToWait()));
    }

    protected List<WebElement> elementsToWait() {
        return null;
    }

    /**
     * Makes the execution halt until the element is found. Polling for the element every 3 seconds with timeout of 15 seconds.
     *
     * @param by Identificador de tipo de busca (By.xpath, By.id, By.css, etc)
     * @return
     */
    public  WebElement waitForElement(By by) {
        Wait<WebDriver> wait = new FluentWait<>(webDriverProvider.get())
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(webDriver -> {
            if (webDriver == null) throw new AssertionError();
            return webDriver.findElement(by);
        });

    }

    /**
     * Navigates to a given URL
     *
     * @param url
     */
    public  void navigateTo(String url) {
        webDriverProvider.get().get(url);
    }

    /**
     * Clicks on a web element
     *
     * @param webElement
     */
    public  void click(WebElement webElement) {
        webElement.click();
    }

    /**
     * Makes the execution halt until the condition of the element being clickable is met
     *
     * @param webElement
     */
    public  void waitUntilClickable(WebElement webElement) {
        new WebDriverWait(webDriverProvider.get(), Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }
    /**
     * Makes the execution halt until the condition of the element being clickable is met
     *
     * @param webElement
     * @param durationInSeconds
     */
    public  void waitUntilClickable(WebElement webElement, int durationInSeconds) {
        new WebDriverWait(webDriverProvider.get(), Duration.ofSeconds(durationInSeconds))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Makes the execution halt until the condition of the element being visible is met
     * Default timeout: 15 seconds
     */
    public  void waitUntilVisibility(WebElement webElement) {
        new WebDriverWait(webDriverProvider.get(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Makes the execution halt until the condition of the element being invisible is met
     * Default timeout: 15 seconds
     */
    public  void waitUntilInvisibility(WebElement webElement) {
        new WebDriverWait(webDriverProvider.get(), Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOf(webElement));
    }
    /**
     * Makes the execution halt until the condition of the element being invisible is met
     * Default timeout: 15 seconds
     */
    public  void waitUntilInvisibility(By by) {
        WebElement webElement = findElement(by);
        new WebDriverWait(webDriverProvider.get(), Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOf(webElement));
    }

    /**
     * Set text inside a web element
     *
     * @param webElement
     * @param text
     */
    public  void setText(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * 1
     * Gets text from a web element
     *
     * @param webElement
     * @return
     */
    public  String getText(WebElement webElement) {
        return webElement.getText();
    }

    /**
     * Get text from anything that can be identified using By
     *
     * @param by
     * @return
     */
    public  String getText(By by) {
        return webDriverProvider.get().findElement(by).getText();
    }

    /**
     * Gets the value from an input
     *
     * @param webElement
     * @return value of the input
     */
    public  String getTextFromInput(WebElement webElement) {
        return webElement.getAttribute("value").trim();
    }

    public  Boolean validateTextExists(WebElement webElement) {
        return !getText(webElement).isEmpty() && !getText(webElement).isBlank() && getText(webElement) != null;
    }

    /**
     * Makes the test halt for 10 seconds before executing the next step. Useful when explicit, implicit or fluente wait
     * can't be used.
     */
    public  void waitForPageLoad() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Makes the test halt for 10 seconds before executing the next step. Useful when explicit, implicit or fluente wait
     * can't be used.
     */
    public  void waitForPageLoad(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
     /**
     * Evaluates if the web element is selected.
     *
     * @param webElement
     * @return
     */
    public  Boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    /**
     * Clear any text from any type of input/textarea.
     *
     * @param webElement
     */
    public  void clearText(WebElement webElement) {
        click(webElement);
        webElement.sendKeys(Keys.CONTROL + "a");
        webElement.sendKeys(Keys.DELETE);
    }

    public  void scrollIntoView(By by) {
        WebElement element = webDriverProvider.get().findElement(by);
        ((JavascriptExecutor) webDriverProvider.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void waitForVisibilityOfAllElements() {
        WebDriverWait wait = new WebDriverWait(webDriverProvider.get(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(webDriverProvider.get().findElements(By.xpath("//*"))));
    }

    public WebElement findElement(By by){
        return webDriverProvider.get().findElement(by);
    }

    public void setText(String field, String data, WebElement webElement){
        if(field.equals("data_nascimento")){
           webElement.sendKeys(env.getProperty("BORN_DAY"));
           webElement.sendKeys(env.getProperty("BORN_MONTH"));
           webElement.sendKeys(env.getProperty("BORN_YEAR"));
        }
        else {
            webElement.sendKeys(data);
        }
    }

    public void selectOption(WebElement webElement, String option){
        String xpath = "//option[@label='" + option + "']";
        webElement.click();
        webDriverProvider.get().findElement(By.xpath(xpath)).click();
    }
}