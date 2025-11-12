# o seguinte cenário é um pouco confuso:
Given that Bill registers for online banking // As pré-condições
And that Bill opens the following accounts:
| account | type    | balance |
| 123456  | savings | 1000    |
| 123457  | current | 100     |
When Bill logs in // A ação sob teste (?)
And Bill goes to the home page
And Bill views his accounts
Then Bill should see a list of his accounts: // O resultado esperado
| account | type    | balance |
| 123456  | savings | 1000    |
| 123457  | current | 100     |

# poderíamos reescrever este cenário da seguinte forma:
Given that Bill is registered for online banking // Colocar estes no tempo passado torna claro que são pré-condições.
And that Bill has opened the following accounts:
| account | type    | balance |
| 123456  | savings | 1000    |
| 123457  | current | 100     |
When Bill views his account summary // Assumir que ele precisa fazer login primeiro.
Then Bill should see all of his accounts // Não há necessidade de repetir a tabela.