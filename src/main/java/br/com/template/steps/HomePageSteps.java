package br.com.template.steps;

import br.com.template.pages.HomePage;
import br.com.template.pages.LoginPage;
import br.com.template.rest.RestClient;
import org.jbehave.core.annotations.*;
import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class HomePageSteps extends AbstractSteps {

    private final HomePage homePage;

    private final LoginPage loginPage;

    private final WebDriverProvider webDriverProvider;
    @Value("${home.url}")
    private String HOME_URL;

    final
    RestClient client;

    public HomePageSteps(HomePage homePage, LoginPage loginPage, RestClient client, WebDriverProvider webDriverProvider) {
        this.homePage = homePage;
        this.loginPage = loginPage;
        this.client = client;
        this.webDriverProvider = webDriverProvider;
    }

    @BeforeStory
    public void setup() {
        client.resetPassword();
    }
    @AfterStories
    public void cleanup() {
        client.deleteTestUser();
    }

    @Given("access the initial page")
    public void accessApp() {
        webDriverProvider.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage.navigateTo(HOME_URL);
    }

    @When("I click login button")
    public void clickLoginApp() {
        homePage.clickLogin();
    }

    @When("I click I forgot password")
    public void clickForgetPassword() {
        homePage.clickForgetPassword();
    }

    @Then("I should see login button")
    public void isLoginDisplayed() {
        Assert.assertTrue(homePage.isLoginDisplayed());
    }

    @Then("I should see login page")
    public void loginIsVisible(){
        Assert.assertTrue(loginPage.isLoginVisible());
    }

    @Then("I should see my avatar logged as $user")
    public void shouldBeLoggedAs(@Named("user") String user){
        Assert.assertTrue(homePage.userLogged(getProperty(user)));
    }

    @When("I click on my avatar link")
    public void clickAvatarLink(){
        homePage.clickAvatarLink();
    }

    @When("I click my profile button")
    public void clickMyProfileLink(){
        homePage.clickMyProfileLink();
    }

    @Given("I click announcement link")
    public void clickAnnuncementLink(){
        homePage.clickAnnouncementMenu();
    }

    @When("I click logout button")
    public void clickLogout(){
        homePage.clickLogoutLink();
    }
    @Then("I should see the properties search page")
    public void thenIShouldSeeThePropertiesPage() {

    }

}