Feature: Calculando Bônus de Pontos por Tipo de Cabine

  # Este esquema de cenário demonstra como os pontos bônus são adicionados
  # ao valor base do voo dependendo da classe de cabine.

  Scenario Outline: Membros ganham bônus de pontos ao voar em cabines premium
    Given que um voo tem um valor base de <base_pontos> pontos
    And que eu sou um membro Frequent Flyer padrão
    When eu voo na classe <cabine>
    Then eu deveria ganhar um bônus de cabine de <bonus_cabine> pontos
    And eu deveria acumular um total de <total_pontos> pontos

    Examples:
      | cabine            | base_pontos | bonus_cabine | total_pontos | taxa_bonus |
      | Economy           | 439         | 0            | 439          | 0%         |
      | Premium Economy   | 439         | 110          | 549          | 25%        |
      | Business          | 439         | 220          | 659          | 50%        |
      | First             | 439         | 439          | 878          | 100%       |

      # Exemplo 2 (Para garantir que não está fixo em 439)
      | Business          | 800         | 400          | 1200         | 50%        |