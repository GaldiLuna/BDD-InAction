Feature: Elegibilidade para Cartão de Crédito Afiliado

  # Cenário que testa as regras de elegibilidade da calculadora de cartão de crédito,
  # ignorando a interface do usuário para focar puramente na lógica de negócio (não-UI).

  Scenario Outline: Elegibilidade de cartão baseada em renda, status e idade
    Given Joe é um <status> Frequent Flyer
    And sua renda anual é de <renda>
    And sua idade é <idade>
    And seu histórico de emprego é <historico_emprego>
    When Joe se candidata a um Cartão de Crédito Flying High
    Then sua aplicação deve ser <resultado>

    Examples:
      | status | renda  | idade | historico_emprego | resultado   | notas                                         |
      | gold   | 120000 | 45    | empregado         | automatico  | Renda alta + status Gold (Regra Base)         |
      | silver | 100000 | 30    | empregado         | automatico  | Silver + Renda acima do limite Silver         |

      # --- Casos de Borda e Novas Regras ---

      | bronze | 110000 | 25    | empregado         | automatico  | Bronze + Renda no limite (Regra Base)         |
      | bronze | 109999 | 25    | empregado         | manual      | Bronze + Renda abaixo do limite               |

      # --- Teste de Idade ---
      | silver | 80000  | 17    | empregado         | declined    | Idade inferior a 18 (Nova Regra)              |
      | silver | 80000  | 76    | empregado         | manual      | Idade acima de 75 (Requer Avaliação)          |

      # --- Teste de Histórico de Emprego (Risco Alto) ---
      | gold   | 150000 | 35    | desempregado      | manual      | Gold + Renda alta, mas desempregado (Risco)   |
      | silver | 80000  | 35    | desempregado      | declined    | Desempregado e Status não é Gold/Renda Média  |
      | bronze | 80000  | 35    | < 6 meses         | manual      | Recém-empregado requer avaliação manual       |

      # --- Combinação de Risco/Borda ---
      | gold   | 50000  | 20    | empregado         | declined    | Gold, mas Renda muito baixa (Abaixo do mínimo) |