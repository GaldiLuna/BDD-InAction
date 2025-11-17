Scenario: Registered baggage should be queued according to its itinerary type
Given a baggage registration message:
"""
245243879870 01 023 29 457
FH 101 # (2) O número do voo
SYD LAX SFO 2013-12-04-19:45
""" # (3) Detalhes do itinerário
When the baggage registration is processed
Then the registration details should be: # (4) Extrair esses detalhes da mensagem de registro
| flight | depart | destination | via | workflow |
| FH-101 | SYD    | SFO       | LAX | international-transfer |

Scenario Outline: Baggage is processed according to its itinerary type
Given a baggage registration message <message>
When the baggage registrations are processed
Then the bags should be placed in the <workflow> workflow