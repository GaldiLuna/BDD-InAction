Scenario: Transferir pontos entre membros
Dado a conta de Danielle tem 100000 pontos e 800 pontos de status // Muita duplicação aqui
And a conta de Martin tem 50000 pontos e 50 pontos de status
Quando Martin transfere 40000 pontos para Danielle
Então Martin deve ter 10000 pontos e 50 pontos de status // Duplicação aqui também
And Danielle deve ter 140000 pontos e 800 pontos de status

Cenário: Transferir pontos entre membros existentes
Dado as seguintes contas: // Fornece uma tabela de dados para o passo Given
| owner    | points | status-points |
| Danielle | 100000 | 800           |
| Martin   | 50000  | 50            |
Quando Martin transfere 40000 pontos para Danielle
Então as contas devem ser as seguintes: // Dados para o passo Then
| owner    | points | status-points |
| Danielle | 140000 | 800           |
| Martin   | 10000  | 50            |
Então eu devo poder fazer upgrade para uma das seguintes classes de cabine:
| Premium Economy |
| Business        |