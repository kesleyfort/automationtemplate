package br.com.template.pages;

import br.com.template.configuration.PageObject;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class MyProfilePage extends AbstractPage {
    @Autowired
    protected WebDriverProvider webDriverProvider;
    @FindBy(xpath = "//*[contains(@class,'Editar_container' )]")
    private WebElement myProfileBox;

    @FindBy(xpath = "//*[contains(text(), 'Dados pessoais')]")
    private WebElement myPersonalDataLink;

    @FindBy(xpath = "//*[contains(text(), 'Dados bancários')]")
    private WebElement myBankAccDataLink;

    @FindBy(xpath = "//*[contains(text(), 'Editar dados')]")
    private WebElement editDataButton;

    @FindBy(xpath = "//button[contains(.,'Salvar')]")
    private WebElement saveButton;

    @FindBy(xpath = "//button[contains(., 'Enviar Documentos')]")
    private WebElement sendDocumentsBtn;

    @FindBy(xpath = "//*[contains(text(), 'Anexar e continuar')]")
    private WebElement addAndContinueBtn;

    @FindBy(xpath = "//div[contains(@role, 'alert')][contains(@class, 'alert-success')]")
    private WebElement alertSuccessMessage;

    @FindBy(xpath = "//div[contains(@role, 'alert')][contains(@class, 'alert-danger')]")
    private WebElement alertErrorMessage;

    @FindBy(xpath = "//div[contains(@role, 'alert')][contains(@class, 'alert-danger')]//button")
    private WebElement closeAlertErrorMessage;

    @FindBy(xpath = "//div[contains(@role, 'alert')][contains(@class, 'alert-success')]//button")
    private WebElement closeAlertSuccessMessage;


    public boolean isMyProfileDisplayed(){
        waitUntilVisibility(myProfileBox);
        return myProfileBox.isDisplayed();
    }

    public void clickPersonalData() {
        myPersonalDataLink.click();
    }

    public void clickBankAccData() {
        myBankAccDataLink.click();
    }

    public void clickEditDataButton() {
        waitUntilVisibility(editDataButton);
        click(editDataButton);
    }

    public void clickSaveButton(){
        waitUntilClickable(saveButton);
        saveButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        waitUntilVisibility(alertSuccessMessage);
        return alertSuccessMessage.isDisplayed();
    }

    public String getSuccessMessage(){
        return alertSuccessMessage.getText().replace("×\nClose alert", "").trim();
    }

    public String getInfoMessage(String infoMessage){
        waitForPageLoad();
        return webDriverProvider.get().findElement(By.xpath(String.format("//*[contains(text(), '%s')]", infoMessage))).getText().trim();
    }


    public boolean isErrorMessageDisplayed() {
        waitUntilVisibility(alertErrorMessage);
        return alertErrorMessage.isDisplayed();
    }

    public String getErrorMessage() {
        return alertErrorMessage.getText().replace("×\nClose alert", "").trim();
    }

    public void closeAlertErrorMessages(){
        if(isErrorMessageDisplayed()) {
            waitUntilClickable(closeAlertErrorMessage);
            closeAlertErrorMessage.click();
        }
    }

    public void closeAlertSuccessMessages(){
        if(isSuccessMessageDisplayed())
            closeAlertSuccessMessage.click();
    }


    public void clickMenu(String menu) {
        String xpath = String.format("//*[contains(text(), '%s')]",menu);
        webDriverProvider.get().findElement(By.xpath(xpath)).click();
    }


    public void clickSendDocuments() {
        sendDocumentsBtn.click();
    }

    public void uploadDocuments(){
        removeAttributeFromInput();
        WebElement documentFront = findElement(By.id("document_front"));
        WebElement documentBack = findElement(By.id("document_back"));
        WebElement documentSelfie = findElement(By.id("selfie"));
        ((RemoteWebElement)documentFront).setFileDetector(new LocalFileDetector());
        ((RemoteWebElement)documentBack).setFileDetector(new LocalFileDetector());
        ((RemoteWebElement)documentSelfie).setFileDetector(new LocalFileDetector());
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String filePath = classloader.getResource("document.jpg").getPath();
        documentFront.sendKeys(filePath);
        documentBack.sendKeys(filePath);
        documentSelfie.sendKeys(filePath);
        addDocuments();
    }

    private void removeAttributeFromInput(){
        JavascriptExecutor executor = (JavascriptExecutor)webDriverProvider.get();
        executor.executeScript("document.getElementById('document_front').style.display = 'block';");
        executor.executeScript("document.getElementById('document_back').style.display = 'block';");
        executor.executeScript("document.getElementById('selfie').style.display = 'block';");
    }

    private void addDocuments(){
        webDriverProvider.get().findElement(By.xpath("(//*[contains(text(), 'Adicionar')])[1]")).click();
        addAndContinueBtn.click();
        addAndContinueBtn.click();
        addAndContinueBtn.click();
    }
}
