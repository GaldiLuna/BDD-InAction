Feature: Earning Frequent Flyer points from flights // (B) No Gherkin, use a palavra-chave Feature para indicar o título de um recurso.
  In order to encourage travellers to book with Flying High Airlines
  more frequently
  As the Flying High sales manager
  I want travellers to earn Frequent Flyer points when they fly with us // (C) Uma breve descrição do recurso segue o título.

Scenario: Earning standard points from an Economy flight
Normal flights earn 1 point every 2 kilometers // Qualquer coisa após o título do cenário e antes do primeiro Given é considerada uma descrição.
  Given the flying distance between Sydney and Melbourne is 878 km
  And I am a standard Frequent Flyer member // And é equivalente a Given
  When I fly from Sydney to Melbourne
  Then I should earn 439 points

Scenario: Earning extra points in Business class
  Given the flying distance between Sydney and Melbourne is 878 km
  And I am a standard Frequent Flyer member
  When I fly from Sydney to Melbourne in Business class
  Then I should earn 878 points // Um ou mais cenários se seguem.

Scenario: Earning extra points in a bonus flyer period
  Given I am a standard Frequent Flyer member // (B) Este passo pode ser reutilizado.
  And I am flying in 'Bonus Flyer' period
  When I fly from Sydney to Melbourne
  Then I should earn 439 points // (C) Este também pode.
  And I should earn a special bonus of 400 points