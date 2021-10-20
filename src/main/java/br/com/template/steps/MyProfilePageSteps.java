package br.com.template.steps;

import br.com.template.pages.MyProfilePage;
import org.jbehave.core.annotations.*;
import org.junit.Assert;
import org.springframework.stereotype.Component;

@Component
public class MyProfilePageSteps extends AbstractSteps {

    private final MyProfilePage myProfilePage;

    public MyProfilePageSteps(MyProfilePage myProfilePage) {
        this.myProfilePage = myProfilePage;
    }


    @Then("I should see my profile page")
    public void myProfileIsVisible(){
        Assert.assertTrue(myProfilePage.isMyProfileDisplayed());
    }

    @When("I click on my personal data")
    public void clickOnPersonalData(){
        myProfilePage.clickPersonalData();
    }

    @Given("I dismiss all error messages")
    public void dismissErrorMessages(){
        myProfilePage.closeAlertErrorMessages();
    }

    @Given("I dismiss all success messages")
    public void dismissSuccessMessages(){
        myProfilePage.closeAlertSuccessMessages();
    }

    @When("I click on my bank account data")
    public void clickOnBankAccountData(){
        myProfilePage.clickBankAccData();
    }

    @When("I click edit data")
    public void clickEditPersonalData(){
        myProfilePage.clickEditDataButton();
    }

    @When("I click save")
    public void clickSavePersonalData(){
        myProfilePage.clickSaveButton();
    }

    @Then("I should see a success message $successMessage")
    public void iShouldSeeSuccessMessage(@Named("successMessage") String successMessage){
        Assert.assertTrue(myProfilePage.isSuccessMessageDisplayed());
        Assert.assertEquals(successMessage, myProfilePage.getSuccessMessage());
    }

    @Then("I should see a error message $errorMessage")
    public void iShouldSeeErrorMessage(@Named("errorMessage") String errorMessage){
        Assert.assertTrue(myProfilePage.isErrorMessageDisplayed());
        Assert.assertEquals(errorMessage, myProfilePage.getErrorMessage());
    }

    @When("I click on profile menu $menu")
    public void clickOnProfileMenu(@Named("menu") String menu){
        myProfilePage.clickMenu(menu);
    }

    @When("I click send documents")
    public void sendDocuments(){
        myProfilePage.clickSendDocuments();
    }

    @When("I upload the document files")
    public void uploadDocuments(){
        myProfilePage.uploadDocuments();
    }

    @Then("I should see a info message $message")
    public void isMessagePresent(@Named("message") String message){
        Assert.assertEquals(myProfilePage.getInfoMessage(message), message);
    }

}