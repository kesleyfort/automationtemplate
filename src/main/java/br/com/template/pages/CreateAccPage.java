package br.com.template.pages;

import br.com.template.configuration.PageObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

@Component
@PageObject
public class CreateAccPage extends AbstractPage{

    @FindBy(xpath = "//div[contains(@class, 'modal-title')][contains(text(),'Cadastro')]")
    public WebElement createAccPageBox;

    @FindBy(xpath = "//input[@type='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginBtn;

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement termsAndConditions;

    @FindBy(xpath = "//*[contains(text(), 'Continuar')]")
    public WebElement continueBtn;

    @FindBy(id = "mui-component-select-estado")
    public WebElement selectState;

    @FindBy(id = "mui-component-select-banco")
    public WebElement selectBank;
    @Autowired
    Environment env;
    public boolean isCreatePageVisible() {
        return createAccPageBox.isDisplayed();
    }

    public void fillField(String field, String data) {
        waitForElement(By.name(field));
        setText(findElement(By.name(field)), data);
    }

    public void selectState(String state){
        click(selectState);
        click(findElement(By.xpath(String.format("//li[contains(.,'%s')]", state))));
    }

    public void selectBank(String bank) {
        click(selectBank);
        bank = StringUtils.toEncodedString(bank.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        click(findElement(By.xpath(String.format("//li[contains(.,'%s')]", bank))));
    }

    public void acceptTermsAndConditions() {
        if(!termsAndConditions.isSelected()){
            termsAndConditions.click();
        }
    }

    public void clickContinue() {
        waitUntilClickable(continueBtn);
        continueBtn.click();
    }


    public void selectAccountType(String accountType) {
        String xpath = String.format("//button[contains(text(), '%s')]", accountType);
        WebElement contaElement = findElement(By.xpath(xpath));
        if(!contaElement.getAttribute("class").contains("ContaSelected"))
            contaElement.click();
    }
}
