Recurso: Transferir dinheiro entre contas
Para gerenciar meu dinheiro de forma mais eficiente
Como um cliente bancário
Eu quero transferir fundos entre minhas contas sempre que precisar

Cenário: Transferindo dinheiro para uma conta poupança
Dado minha Conta Corrente tem um saldo de 1000.00
E minha Conta Poupança tem um saldo de 2000.00
Quando eu transfiro 500.00 da minha Conta Corrente para minha Conta Poupança
Então eu devo ter 500.00 na minha Conta Corrente
E eu devo ter 2500.00 na minha Conta Poupança

Cenário: Transferindo com fundos insuficientes
Dado minha Conta Corrente tem um saldo de 1000.00
E minha Conta Poupança tem um saldo de 2000.00
Quando eu transfiro 1500.00 da minha Conta Corrente para minha Conta Poupança
Então eu devo receber um erro de 'fundos insuficientes'
Então eu devo ter 1000.00 na minha Conta Corrente
E eu devo ter 2000.00 na minha Conta Poupança

Esquema do Cenário: Ganhando juros
Dado que eu tenho uma conta do tipo <tipo-conta> com um saldo de <saldo-inicial>
Quando o juro mensal é calculado
Então eu devo ter ganho a uma taxa de juros anual de <taxa-juros>
E eu devo ter um novo saldo de <novo-saldo>

Exemplos:
| saldo-inicial | tipo-conta | taxa-juros | novo-saldo |
| 10000         | corrente   | 1          | 10008.33   |
| 10000         | poupança   | 3          | 10025      |
| 10000         | supersaver | 5          | 10041.67   |

Cenário: Transferindo dinheiro entre contas dentro do banco
Dado que eu tenho as seguintes contas:
| conta   | saldo |
| corrente| 1000  |
| poupança| 2000  |
Quando eu transfiro 500.00 da corrente para a poupança
Então minhas contas devem se parecer com isto:
| conta   | saldo |
| corrente| 500   |
| poupança| 2500  |