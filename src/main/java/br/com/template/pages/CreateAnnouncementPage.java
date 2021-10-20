package br.com.template.pages;

import br.com.template.configuration.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class CreateAnnouncementPage extends AbstractPage{

    @FindBy(xpath = "//div[contains(@class, 'modal-title')][contains(text(),'Cadastro')]")
    private WebElement createAccPageBox;

    private String houseOptionXpath = "//*[contains(text(), 'Tipo de imóvel')]//following::button[contains(text(), '%s')]";

    private String roomQuantityXpath = "(//*[contains(text(), 'Quartos')]//following::button[contains(text(), '%s')])[1]";

    private String bathroomQuantityXpath = "(//*[contains(text(), 'Banheiros')]//following::button[contains(text(), '%s')])[1]";

    private String garageQuantityXpath = "//*[contains(text(), 'Vagas de garagem')]//following::button[contains(text(), '%s')]";

    @FindBy(xpath = "(//*[contains(text(), 'Política com animais')]//following::button[contains(text(), 'Permitido')])[1]")
    private WebElement animalPolicyTrue;

    @FindBy(xpath = "(//*[contains(text(), 'Política com animais')]//following::button[contains(text(), 'Não é permitido')])[1]")
    private WebElement animalPolicyFalse;

    private String renterTaxPath = "(//*[contains(text(), 'Quem vai pagar o condomínio?')]//following::button)[%s]";

    private String renterAptTaxPath = "(//*[contains(text(), 'Quem vai pagar o IPTU?')]//following::button)[%s]";

    @FindBy(name = "valorAluguel")
    private WebElement rentPrice;

    @FindBy(name = "valorCondominio")
    private WebElement rentTaxPrice;

    @FindBy(name = "valorIptu")
    private WebElement aptTaxPrice;

    @FindBy(xpath = "//*[contains(text(),'Revisar anúncio')]")
    private WebElement reviewAnnuncement;

    @FindBy(xpath = "//div[contains(@class, 'Anunciar_cardImage')]")
    private WebElement imageInput;
    @FindBy(xpath = "//button[contains(.,'Continuar')]")
    private WebElement continueBtn;

    private String warrantyType = "//input[@name='tipoGarantia' and @value='%s']";
    private String securityMonths = "//input[@name='qtdeMesCaucao' and @value='%s']";
    public void selectHouseOption(String option){
        String xpath = String.format(houseOptionXpath, option);
        findElement(By.xpath(xpath)).click();
    }

    public void selectRoomQuantity(String option){
        String xpath = String.format(roomQuantityXpath, option);
        findElement(By.xpath(xpath)).click();
    }

    public void selectBathroomQuantity(String option){
        String xpath = String.format(bathroomQuantityXpath, option);
        findElement(By.xpath(xpath)).click();
    }

    public void selectGarageQuantity(String option){
        String xpath = String.format(garageQuantityXpath, option);
        String label = "//*[contains(text(), 'Banheiros')]";
        scrollIntoView(By.xpath(label));
        WebElement button = findElement(By.xpath(xpath));
        waitUntilClickable(button);
        click(button);
    }

    public void selectAnimalPolicy(boolean option){
        if(option){
            animalPolicyTrue.click();
        }else{
            animalPolicyFalse.click();
        }
    }

    public void fillRentTaxes(){
        rentPrice.sendKeys("600");
        rentTaxPrice.sendKeys("250");
        aptTaxPrice.sendKeys("200");
    }

    public void fillRenterOptions(String option){
        if(option.equalsIgnoreCase("landlord")){
            findElement(By.xpath(String.format(renterAptTaxPath, 1))).click();
            findElement(By.xpath(String.format(renterTaxPath, 1))).click();
        }else{
            findElement(By.xpath(String.format(renterAptTaxPath, 2))).click();
            findElement(By.xpath(String.format(renterTaxPath, 2))).click();
        }
    }

    public void fillRenterWarranty(String option) {
        By warranty = By.xpath(String.format(warrantyType, option));
        click(findElement(warranty));
    }
    public void fillSecurityDepositMonths(String option){
        By warranty = By.xpath("//input[@name='tipoGarantia']");
        scrollIntoView(warranty);
        By security = By.xpath(String.format(securityMonths, option));
        click(findElement(security));
    }
    public void reviewAnnouncement() {
        reviewAnnuncement.click();
    }

    public void addPropertyImage() {
        WebElement propertyImage = findElement(By.xpath("//input"));
        if(env.getProperty("remoteTesting").equals("true")){
            ((RemoteWebElement)propertyImage).setFileDetector(new LocalFileDetector());
        }
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String filePath = classloader.getResource("document.jpg").getPath();
        propertyImage.sendKeys(filePath);
    }

    public boolean isUploadedImageVisible() {
       return imageInput.getAttribute("style").contains("blob:https://dev-app.mellro.com/");
    }

    public void clickContinue() {
        click(continueBtn);
    }
}
