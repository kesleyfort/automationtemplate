Narrative:
I access the main page
as an User
I want to create a new announcement

Scenario:Login
Meta:
@smoke
Given access the initial page
When I click login button
When I fill the field email with EMAIL_REGISTER
And I fill the field password with PWORD_REGISTER
And I click continue

Scenario: Create announcement
Meta:
@smoke
Given I click announcement link
When I fill the field cep with CEP_REGISTER
And I fill the field bairro with NEIGHBOURHOOD
And I select the state STATE_REGISTER
And I fill the field cidade with CITY_REGISTER
And I fill the field endereco with ADDRESS_REGISTER
And I fill the field numero with HOUSE_NUMBER_REGISTER
And I fill the field complemento with ADDRESS2_REGISTER
And I click continue
When I select land type Apartamento
And I select room quantity equal to 2
And I select bathroom quantity equal to 1
And I select garage quantity equal to 0
And I select animal policy permited
And I fill the field metragem with METRIC_REGISTER
And I click continue
When I upload a property image
Then I should see the uploaded image
And I click continue
When I select the renter to afford apartment taxes
When I fill the rent and apartment values/taxes
And I select guarantee type SECURITY_DEPOSIT
And I select 3 months security deposit
And I click continue
And I click review announcement
And I click continue