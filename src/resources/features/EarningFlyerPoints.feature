# Esta linha de comentário no Gherkin começa com #
# This feature is really important for the Marketing team
Feature: Earning Frequent Flyer points from flights // O título do recurso.
Narrative: // A palavra-chave Narrative introduz uma descrição opcional.
In order to encourage travellers to book with Flying High Airlines
more frequently
As the Flying High sales manager
I want travellers to earn Frequent Flyer points when they fly with us // Uma breve descrição do recurso segue o título.

# I don’t know how to access the distances service yet // É frequentemente usado para deixar uma nota técnica para outros desenvolvedores.
Scenario: Earning standard points from an Economy flight
Normal flights earn 1 point every 2 kilometers // Qualquer coisa após o título do cenário e antes do primeiro Given é considerada uma descrição.
# Given the flying distance between Sydney and Melbourne is 878 km // Pode ser usado para comentar qualquer linha (ex: comentar passos).
Given the flying distance between Sydney and Melbourne is 878 km
!-- Given the flying distance between Sydney and Melbourne is 878 km // Comentários JBehave começam com !--
And I am a standard Frequent Flyer member // And é equivalente a Given
When I fly from Sydney to Melbourne
Then I should earn 439 points // Um ou mais cenários se seguem.

Scenario: Earning extra points in Business class
Given the flying distance between Sydney and Melbourne is 878 km
And I am a standard Frequent Flyer member
When I fly from Sydney to Melbourne in Business class
Then I should earn 878 points // Um ou mais cenários se seguem.

Scenario: Earning extra points in a bonus flyer period
Given I am a standard Frequent Flyer member // Este passo pode ser reutilizado.
And I am flying in 'Bonus Flyer' period
When I fly from Sydney to Melbourne
Then I should earn 439 points // Este também pode.
And I should earn a special bonus of 400 points