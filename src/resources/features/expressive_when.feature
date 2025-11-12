# o comprimento do cenário também o torna mais difícil de ler, o que reduz seu uso como meio de comunicação:
Scenario: Register for online banking
Given that Bill wants to register for online banking
When he goes to the registration page
And he enters 'Bill' in the first name field
And he enters 'Smith' in the surname field
And he enters 'bill@smith.com" in the email field
And he enters '01/01/1980' in the date of birth field // (B) Um relato detalhado do processo de aplicação
And he enters '1 George street' in the street field
And he enters 'Sydney' in the city field
And he enters '2000' in the post code field
And he clicks on submit
Then his application should be created in a pending state // Os resultados esperados
And he should be sent a PDF contract to sign by email

# poderíamos ocultar esses detalhes em um único passo de nível superior, conforme mostrado aqui:
Scenario: Register for online banking
Given that Bill wants to register for online banking with the following
details:
| first-name | surname | email          | dob        | street | city   | postcode | // (B) Os valores dos campos são agora armazenados em uma tabela.
| Bill       | Smith   | bill@smith.com | 01/01/1980 | ...    | Sydney | 2000     |
When he submits his application online // (C) Uma visão de nível superior do processo de submissão.
Then his application should be created in a pending state // (D) Os resultados esperados.
And he should be sent a PDF contract to sign by email

# equipes novas em BDD escrevem cenários que se parecem com isto:
Scenario: Updating my account address
Given I am logged in as a bank client
Then I should see the home page
When I select the 'accounts' menu
Then I should see the 'Accounts' page
And I should see a list of accounts
When I click on 'Edit'
Then I should see 'Account Details'
When I type '100 Main Street' into the street
And I type 'Armidale' into the city
And I type 'Update'
Then I should see the updated account details on the account summary page