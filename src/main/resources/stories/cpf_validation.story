Scenario: User validates tenant cpf score
Description: As a user i want to validate the presence of user cpf validation form
Meta:
@smoke
Given access the initial page
When I click login button
When I fill the field email with EMAIL_REGISTER
And I fill the field password with PWORD_REGISTER
And I click continue
When I navigate to cpf validation page
And I click Fazer Analise button
Then I should see the cpf validation forms
When I fill the field full_name with TENANT_NAME
And I fill the field cpf with TENANT_CPF
And I click continuar
When I click Ok button
Then I should see the payment details form
When I click on Tenho Voucher button
Then I should see the voucher input
When I fill the voucher input
And I click Aplicar voucher
Then I should see the results panel