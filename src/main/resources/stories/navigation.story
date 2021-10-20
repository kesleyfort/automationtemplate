Narrative:
I access the main page
as an User
I want to create an account and edit the information

Scenario: check login
Meta:
@smoke
Given access the initial page
When I click login button
Then I should see login page

Scenario: create account
Meta:
@smoke
Given I am at login page
When I click create account link
Then I should see the register page
When I fill the field name with COMPLETE_NAME
And I fill the field email with EMAIL_REGISTER
And I fill the field email_confirm with EMAIL_REGISTER
And I agree with the terms and conditions
And I click continue
And I fill the field password with PWORD_REGISTER
And I fill the field password_confirm with PWORD_REGISTER
And I click continue
And I fill the field phone with PHONE_REGISTER
And I click continue

Scenario:Login
Meta:
@smoke
Given access the initial page
When I click login button
Then I should see login page
When I fill the field email with EMAIL_REGISTER
And I fill the field password with PWORD_REGISTER
And I click continue
Then I should see my avatar logged as FIRST_NAME

Scenario: Navigate profile page
Meta:
@smoke
When I click on my avatar link
When I click my profile button
Then I should see my profile page

Scenario: Change my personal data
Meta:
@smoke
When I click on my personal data
!-- Given I dismiss all error messages
When I click edit data
And I fill the field cep with CEP_REGISTER
And I fill the field data_nascimento with BORN_REGISTER
And I select the state STATE_REGISTER
And I fill the field cidade with CITY_REGISTER
And I fill the field neighbourhood with NEIGHBOURHOOD
And I fill the field endereco with ADDRESS_REGISTER
And I fill the field numero with HOUSE_NUMBER_REGISTER
And I fill the field complemento with ADDRESS2_REGISTER
And I click save
Then I should see a success message Seu perfil foi atualizado com sucesso.
Given I dismiss all success messages


!-- Scenario: Validate my personal data
!-- When I click send documents
!-- And I click continue
!-- When I fill the field cpf with CPF_REGISTER
!-- When I fill the field birthdate with BORN_REGISTER
!-- When I fill the field mother_name with MOTHER_NAME
!-- When I upload the document files
!-- And I click continue
!-- Then I should see a info message Seu cadastro está em análise
!-- When I click continue

Scenario: Fill bank account
Meta:
@smoke
When I click on my bank account data
And I click edit data
And I select the bank BANK_REGISTER
And I fill the field agencia with BANK_AGENCY
And I fill the field numero_conta with ACCOUNT_NUMBER
And I select account type Corrente
And I click save
Then I should see a success message Dados bancários alterados.
Given I dismiss all success messages

Scenario: Change PWD
When I click on profile menu Alterar senha
And I fill the field old_password with PWORD_REGISTER
And I fill the field new_password with PWORD_REGISTER
And I fill the field new_password_confirm with PWORD_REGISTER
And I click save
Then I should see a error message A nova senha não pode ser igual a senha anterior.
Given I dismiss all error messages

Scenario: Change PWD
When I click on profile menu Alterar senha
And I fill the field old_password with PWORD_REGISTER
And I fill the field new_password with PWORD_REGISTER_2
And I fill the field new_password_confirm with PWORD_REGISTER_2
And I click save
Then I should see a success message Senha alterada com sucesso.
Given I dismiss all success messages

Scenario: Logout
Meta:
@smoke
When I click on my avatar link
When I click logout button
Then I should see the properties search page

Scenario: Forgot PWD
Meta:
@smoke
When I click login button
And I click I forgot password
And I fill the field email with EMAIL_REGISTER
And I click continue
Then I should see a success message Confira sua caixa de email para redefinir sua senha.
Given I dismiss all success messages
