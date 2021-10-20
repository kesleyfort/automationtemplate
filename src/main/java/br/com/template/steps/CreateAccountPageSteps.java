package br.com.template.steps;

import br.com.template.pages.CreateAccPage;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountPageSteps extends AbstractSteps {

    @Autowired
    private CreateAccPage createAccPage;

    @Then("I should see the register page")
    public void openLoginPage(){
        Assert.assertTrue(createAccPage.isCreatePageVisible());
    }

    @When("I fill the field $field with $data")
    public void fillField(@Named("field") String field, @Named("data") String data){
        createAccPage.fillField(field, getProperty(data));
    }

    @When("I select the state $state")
    public void fillStateSelectField(@Named("state") String state){
        createAccPage.selectState(getProperty(state));
    }

    @When("I select the bank $bank")
    public void fillBankSelectField(@Named("bank") String bank){
        createAccPage.selectBank(getProperty(bank));
    }

    @When("I select account type $accountType")
    public void selectAccountType(@Named("accountType") String accountType){
        createAccPage.selectAccountType(accountType);
    }

    @When("I agree with the terms and conditions")
    public void agreeTerms(){
        createAccPage.acceptTermsAndConditions();
    }

    @When("I click continue")
    public void clickContinue(){
        createAccPage.clickContinue();
    }
}