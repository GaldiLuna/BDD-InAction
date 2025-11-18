Scenario Outline: Frequent Flyer status is calculated based on points
Given Joe Jones is a <initialStatus> Frequent Flyer member
And he has <initialStatusPoints> status points
When he earns <extraPoints> extra status points // Descreve como a aplicação deve se comportar.
Then he should have a status of <finalStatus> // Descreve como a aplicação deve se comportar.

Examples: Status points required for each level // Ilustra o comportamento com alguns exemplos básicos.
| initialStatus | initialStatusPoints | extraPoints | finalStatus |
| Bronze        | 0                   | 300         | Silver      |
| Bronze        | 100                 | 200         | Silver      |
| Silver        | 0                   | 700         | Gold        |
| Gold          | 0                   | 1500        | Platinum    |