Scenario: Should return flight details in JSON form
Given I need to know the details of flight number FH-102 // Um voo conhecido
When I request the details about this flight in JSON format
Then I should receive:
"""
{
"flightNumber":"FH-102", // O resultado JSON esperado
"departure":"SYD",
"destination":"MEL",
"time":"06:15"
}
"""