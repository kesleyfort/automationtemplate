package br.com.template.pages;

import br.com.template.configuration.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class LoginPage extends AbstractPage{

    @FindBy(xpath = "//div[contains(@class, 'modal-title')][contains(text(),'Login')]")
    private WebElement loginPageBox;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[contains(@class,'facebook')]/ancestor::button")
    private WebElement facebookLogin;

    @FindBy(xpath = "//a[contains(text(), 'Criar minha conta')]")
    private WebElement createAccBtn;

    @FindBy(xpath = "//a[contains(text(), 'Esqueci minha senha')]")
    private WebElement forgetPwd;

    public boolean isLoginVisible() {
        return loginPageBox.isDisplayed();
    }

    public void login(String login, String pwd){
        email.sendKeys(login);
        passwordField.sendKeys(pwd);
        loginBtn.click();
    }

    public void createAccountLink() {
        createAccBtn.click();
    }

    public void clickLoginWithFacebook() {
        facebookLogin.click();
    }
}
