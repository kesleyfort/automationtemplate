package br.com.template.pages;

import br.com.template.configuration.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class CpfVerificationPage extends AbstractPage {
    @FindBy(xpath = "//button[text()='Fazer análise']")
    public WebElement fazerAnaliseButton;
    @FindBy(xpath = "//h5[text()='Informar dados']//parent::div")
    public WebElement informarDadosDiv;
    @FindBy(xpath = "//h5[text()='Pagamento']//parent::div")
    public WebElement informarDadosPagamentoDiv;
    @FindBy(xpath = "//div[contains(@class,'AvaliacaoCpf_containerResultCard')]")
    public WebElement resultadoConsultaDiv;
    @FindBy(name = "voucher")
    public WebElement voucherInput;
    @FindBy(xpath = "//button[contains(.,'Aplicar voucher')]")
    public WebElement aplicarVoucherButton;
    @FindBy(xpath = "//button[contains(.,'Tenho voucher')]")
    public WebElement tenhoVoucherButton;
    public By tenhoVoucherButtonXpath = By.xpath("//button[contains(.,'Tenho voucher')]");
    @FindBy(xpath = "//button[contains(.,'Continuar')]")
    public WebElement continuarButton;
    @FindBy(xpath = "//body")
    public WebElement body;
    @FindBy(xpath = "//div[contains(@class,'dropdown')]//button[contains(text(), 'Mais')]")
    public WebElement maisDropdown;
    @FindBy(xpath = "//div[contains(@class,'dropdown-menu')]//a")
    public WebElement analiseCpfButton;
    @FindBy(xpath = "//button[contains(text(),'OK')]")
    private WebElement okButton;

    public void navigateToValidation() {
        waitForPageLoad(6000);
        click(maisDropdown);
        click(analiseCpfButton);
    }

    public void clickFazerAnalise(){
        waitUntilVisibility(fazerAnaliseButton);
        waitUntilClickable(fazerAnaliseButton);
        click(fazerAnaliseButton);
    }

    public void clickOkButton() {
        click(okButton);
    }
}
