# Scenario Outline: Earning points on flights by Frequent Flyer status // Palavra-chave Scenario Outline marca isso como um cenário orientado por tabela.
Scenario: Earning extra points on flights by Frequent Flyer status
Given I am a <status> Frequent Flyer member // Expressa a regra em termos mais genéricos.
When I fly on a flight that is worth <base> base points // Dados da tabela de exemplo são passados para os passos.
Then I should earn a status bonus of <bonus>
And I should have guaranteed minimum earned points per trip of <minimum>
And I should earn <total> points in all

Examples:
| status   | base | bonus | minimum | total | notes           | // O cenário será verificado para cada linha de dados na tabela.
| Standard | 439  | 0     | 0       | 439   |                 | // Resume os diferentes casos usando alguns exemplos bem escolhidos.
| Silver   | 148  | 74    | 500     | 500   | minimum points  |
| Silver   | 439  | 220   | 500     | 659   | 50% bonus       |
| Gold     | 439  | 329   | 1000    | 1000  | minimum points  |
| Gold     | 2041 | 1531  | 1000    | 3572  | 75% bonus       |