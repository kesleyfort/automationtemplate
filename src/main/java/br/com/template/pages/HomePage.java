package br.com.template.pages;

import br.com.template.configuration.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class HomePage extends AbstractPage {

    @FindBy(xpath = "//*[contains(@alt, 'Logo')]")
    private WebElement home;

    @FindBy(xpath = "//a[contains(@class,'Header_buttonLogin')]")
    private WebElement login;

    @FindBy(xpath = "//*[contains(text(), 'Esqueci minha senha')]")
    private WebElement forgetPassword;

    @FindBy(xpath= "//*[contains(text(), 'Meus Imóveis')]//following::a[2]")
    private WebElement avatarButton;

    @FindBy(xpath= "//*[contains(text(), 'Perfil')]")
    private WebElement myProfileBtn;

    @FindBy(xpath= "//a[contains(text(), 'Anunciar')]")
    private WebElement announcementMenu;

    @FindBy(xpath= "//*[contains(text(), 'Sair')]")
    private WebElement logoutBtn;

    public void clickLogin(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(login);
    }

    public boolean isLoginDisplayed(){
        return login.isDisplayed();
    }

    public boolean userLogged(String user){
        String xpath = String.format("(//p[contains(text(), '%s')])[2]", user);
        waitForPageLoad(3000);
        return waitForElement(By.xpath(xpath)).isDisplayed();
    }

    public void clickAvatarLink() {
       waitForPageLoad(6000);
        avatarButton.click();
    }

    public void clickMyProfileLink() {
        myProfileBtn.click();
    }

    public void clickLogoutLink() {
        logoutBtn.click();
    }

    public void clickForgetPassword() {
        forgetPassword.click();
    }

    public void clickAnnouncementMenu() {
        waitForPageLoad(1500);
        announcementMenu.click();
    }
}

