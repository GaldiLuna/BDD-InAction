Feature: Logging on to the 'My Flying High' website
  Frequent Flyer members can register on the 'My Flying High' website
  using their Frequent Flyer number and a password that they provide

  # para evitar ter que repetir passos, usamos a palavra-chave Background
  Background: Martin is registered on the site
    Given Martin is a Frequent Flyer member // Estes passos serão executados antes de cada cenário.
    And Martin has registered online with a password of 'secret'

  Scenario: Logging on successfully
    When Martin logs on with password 'secret'
    Then he should be given access to the site

  Scenario: Logging on with an incorrect password
    When Martin logs on with password 'wrong'
    Then he should be informed that his password was incorrect // Note os passos duplicados

  Scenario: Logging on with an expired account
    But the account has expired
    When Martin logs on with password 'secret'
    Then he should be informed that his account has expired
    And he should be invited to renew his account