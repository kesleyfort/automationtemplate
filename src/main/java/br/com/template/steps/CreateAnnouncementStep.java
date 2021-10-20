package br.com.template.steps;



import br.com.template.pages.CreateAnnouncementPage;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAnnouncementStep extends AbstractSteps {


    @Autowired
    private CreateAnnouncementPage createAnnouncementPage;

    @When("I select land type $type")
    public void fillLandType(@Named("type") String type){
        createAnnouncementPage.selectHouseOption(type);
    }

    @When("I fill the rent and apartment values/taxes")
    public void fillLandRentAndTaxes(){
        createAnnouncementPage.fillRentTaxes();
    }

    @When("I select the $option to afford apartment taxes")
    public void fillLandRentAndTaxesAfford(@Named("option") String option){
        createAnnouncementPage.fillRenterOptions(option);
    }

    @When("I click review announcement")
    public void reviewAnnouncementBtn(){
        createAnnouncementPage.reviewAnnouncement();
    }

    @When("I select guarantee type $warrantyType")
    public void warrantyType(@Named("warrantyType") String type){
        createAnnouncementPage.fillRenterWarranty(type);
    }
    @When("I select $numberOfMonths months security deposit")
    public void iSelectNumberOfMonthsSecurityDeposit(@Named("numberOfMonths") String months){
        createAnnouncementPage.fillSecurityDepositMonths(months);
    }

    @When("I select room quantity equal to $quantity")
    public void fillRoomQuantity(@Named("quantity") String quantity){
        createAnnouncementPage.selectRoomQuantity(quantity);
    }

    @When("I select bathroom quantity equal to $quantity")
    public void fillBathroomQuantity(@Named("quantity") String quantity){
        createAnnouncementPage.selectBathroomQuantity(quantity);
    }

    @When("I select garage quantity equal to $quantity")
    public void fillGarageQuantity(@Named("quantity") String quantity){
        createAnnouncementPage.selectGarageQuantity(quantity);
    }

    @When("I select animal policy permited")
    public void fillAnimalPolicyTrue(){
        createAnnouncementPage.selectAnimalPolicy(true);
    }

    @When("I select animal policy to not permited")
    public void fillAnimalPolicyFalse(){
        createAnnouncementPage.selectAnimalPolicy(false);
    }

    @When("I upload a property image")
    public void whenIUploadAPropertyImage() {
        createAnnouncementPage.addPropertyImage();
    }
    @Then("I should see the uploaded image")
    public void thenIShouldSeeTheUploadedImage() {
        Assert.assertTrue(createAnnouncementPage.isUploadedImageVisible());
    }
    @Then("I click continue")
    public void thenIClickContinue() {
        createAnnouncementPage.clickContinue();
    }
}
