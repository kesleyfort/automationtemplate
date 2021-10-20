package br.com.template.steps;

import br.com.template.pages.CpfVerificationPage;
import br.com.template.rest.RestClient;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CpfVerificationSteps extends AbstractSteps {
    @Value("${cpf.url}")
    private String HOME_URL;
    private final CpfVerificationPage cpfVerificationPage;
    private final String voucher;
    public CpfVerificationSteps(CpfVerificationPage cpfVerificationPage, RestClient restClient) {
        this.cpfVerificationPage = cpfVerificationPage;
        voucher = restClient.generateVoucher();
    }
    @Given("I navigate to cpf validation page")
    public void givenINavigateToCpfValidationPage() {
        cpfVerificationPage.navigateTo(HOME_URL);
    }
    @When("I click on Fazer Analise button")
    public void whenIClickOnButton() {
        cpfVerificationPage.waitUntilInvisibility(By.xpath("//div[@class='fade modal show']"));
        cpfVerificationPage.click(cpfVerificationPage.fazerAnaliseButton);
    }
    @Then("I should see the cpf validation forms")
    public void thenIShouldSeeTheCpfValidationForms() {
        cpfVerificationPage.waitUntilVisibility(cpfVerificationPage.informarDadosDiv);
        Assert.assertTrue("Div Informar Dados não está sendo exibido", cpfVerificationPage.informarDadosDiv.isDisplayed());
    }

    @Then("I should see the payment details form")
    public void thenIShouldSeeThePaymentDetailsForm() {
        cpfVerificationPage.waitUntilVisibility(cpfVerificationPage.informarDadosPagamentoDiv);
        Assert.assertTrue("Div Informar Dados de Pagamento não está sendo exibido", cpfVerificationPage.informarDadosPagamentoDiv.isDisplayed());

    }
    @When("I click on Tenho Voucher button")
    public void whenIClickOnTenhoVoucherButton() {
        cpfVerificationPage.scrollIntoView(cpfVerificationPage.tenhoVoucherButtonXpath);
        cpfVerificationPage.waitUntilClickable(cpfVerificationPage.tenhoVoucherButton);
        cpfVerificationPage.click(cpfVerificationPage.tenhoVoucherButton);
    }
    @Then("I should see the voucher input")
    public void thenIShouldSeeTheVoucherInput() {
        cpfVerificationPage.waitUntilVisibility(cpfVerificationPage.voucherInput);
        Assert.assertTrue("Campo de voucher não está sendo exibido", cpfVerificationPage.voucherInput.isDisplayed());

    }
    @When("I fill the voucher input")
    public void whenIFillTheVoucherInput() {
       cpfVerificationPage.setText(cpfVerificationPage.voucherInput, voucher);
    }
    @When("I click Aplicar voucher")
    public void whenIClickAplicarVoucher() {
        cpfVerificationPage.waitUntilClickable(cpfVerificationPage.aplicarVoucherButton);
        cpfVerificationPage.click(cpfVerificationPage.aplicarVoucherButton);
    }
    @When("I click continuar")
    public void whenIClickAContinuar() {
        cpfVerificationPage.scrollIntoView(By.xpath("//button[contains(.,'Continuar')]"));
        cpfVerificationPage.click(cpfVerificationPage.continuarButton);
    }
    @Then("I should see the results panel")
    public void thenIShouldSeeTheResultsPanel() {
        cpfVerificationPage.waitUntilVisibility(cpfVerificationPage.resultadoConsultaDiv);
        Assert.assertTrue("Painel de resultado de consulta não está sendo exibido", cpfVerificationPage.resultadoConsultaDiv.isDisplayed());

    }
    @When("I navigate to cpf validation page")
    public void whenINavigateToCpfValidationPage() {
        cpfVerificationPage.navigateToValidation();
    }
    @Then("I click Fazer Analise button")
    @When("I click Fazer Analise button")
    public void iClickFazerAnaliseButton() {
        cpfVerificationPage.clickFazerAnalise();
    }
    @When("I click Ok button")
    public void iClickOkButton() {
        cpfVerificationPage.clickOkButton();
    }
}
