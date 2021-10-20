package br.com.template.steps;

import br.com.template.pages.HomePage;
import br.com.template.pages.LoginPage;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPageSteps extends AbstractSteps {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private HomePage homePage;

//    @AfterScenario
//    public void logout() {
//        logoutFromSystem(homePage, usuario);
//    }

    @AfterStory
    public void cleanup() {

    }

    @When("I click create account link")
    public void createAccount(){
        loginPage.createAccountLink();
    }

    @Given("I am at login page")
    public void loginPage(){
        if(!loginPage.isLoginVisible()){
            homePage.clickLogin();
        }
    }

    @When("I click login with facebook")
    public void loginWithFacebook(){
        loginPage.clickLoginWithFacebook();
    }
}