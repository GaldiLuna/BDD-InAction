# Feature: Força da Senha
# A fim de proteger as contas dos membros Frequent Flyer,
# Como um membro,
# Eu quero ser forçado a criar uma senha forte quando me registro.

Feature: Força da Senha

  # Cenário 1: Testando os requisitos mínimos de validação
  # Este Esquema do Cenário testa os três critérios (tamanho, dígito, pontuação) de uma só vez.

  Scenario Outline: Uma senha deve atender a critérios mínimos de força
    Given que eu estou na página de registro
    When eu insiro "<senha>" no campo de senha
    And eu submeto o formulário de registro
    Then eu deveria ver uma mensagem de erro contendo "<mensagem_erro>"

    Examples: Teste de Senhas Fracas
      | senha         | mensagem_erro                                 | Criterio Falhado |
      | secret1       | A senha deve ter pelo menos 8 caracteres.     | Tamanho          |
      | MinhaSenha!   | A senha deve conter pelo menos 1 digito.      | Digito Faltante  |
      | MinhaSenha1   | A senha deve conter pelo menos 1 pontuacao.   | Pontuacao Faltante |
      | 123456789!    | (Nenhuma - O teste deve falhar na acao)       | Acao/Bloqueio    |

  # Cenário 2: Senha Válida (Happy Path)
  # Este cenário verifica o comportamento de sucesso.

  Scenario: Criar uma senha forte e válida
    Given que eu estou na página de registro
    When eu insiro "MyStrongPassword!123" no campo de senha
    And eu submeto o formulário de registro
    Then eu deveria ser levado para a página de confirmação do registro
    And minha conta deveria estar marcada como "ativa"

  # Cenário 3: Mensagem de Erro Específica para Senhas Muito Curtas
  # Usado para garantir que a mensagem de erro específica seja exibida.

  Scenario: Exibir mensagem de erro específica para senha muito curta
    Given que eu estou na página de registro
    When eu insiro "curta!1" no campo de senha
    And eu submeto o formulário de registro
    Then eu deveria ver uma mensagem de erro contendo "A senha deve ter pelo menos 8 caracteres."