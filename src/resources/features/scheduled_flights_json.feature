Scenario: Should return scheduled flights in JSON form
Given the following flights have been scheduled: // Configura os dados de teste.
| flightNumber | Departure | Destination | time  |
| FH-101       | SYD       | MEL         | 06:15 |
| FH-102       | MEL       | SYD         | 06:30 |
| FH-223       | SYD       | LAX         | 06:00 |
| FH-305       | MEL       | SFO         | 07:15 |
| FH-234       | SYD       | LHR         | 09:25 |
| FH-403       | SYD       | DBX         | 14:05 |
When I request the International flights in JSON form // Chama o serviço web.
Then I should receive the following flights:
"""
[
{"flightNumber":"FH-223", "departure":"SYD","destination":"LAX","time":"06:00"},
{"flightNumber":"FH-305", "departure":"MEL","destination":"SFO","time":"07:15"}, // Resultados de pesquisa esperados.
{"flightNumber":"FH-234", "departure":"SYD","destination":"LHR","time":"09:25"},
{"flightNumber":"FH-403", "departure":"SYD","destination":"DBX","time":"14:05"}
]
"""