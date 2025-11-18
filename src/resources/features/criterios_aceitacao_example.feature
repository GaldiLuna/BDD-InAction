Feature: Frequent Flyer status is calculated based on points // Relembra os objetivos de negócio por trás deste requisito.
  As a Frequent Flyer member
  I want my status to be upgraded as soon as I earn enough points
  So that I can benefit from my higher status sooner

  Scenario: New members should start out as BRONZE members // Este cenário descreve qual status os membros devem obter quando começam.
    Given Jill Smith is not a Frequent Flyer member // Background e contexto
    When she registers on the Frequent Flyer program // O comportamento sob teste
    Then she should have a status of BRONZE // O resultado esperado

  Scenario Outline: Frequent Flyer status is calculated based on points
    Given Joe Jones is a <initialStatus> Frequent Flyer member
    And he has <initialStatusPoints> status points
    When he earns <extraPoints> extra status points
    Then he should have a status of <finalStatus> // Este cenário descreve o processo real de acúmulo de status.

    Examples: Status points required for each level // Ilustra o número de pontos necessários para cada nível.
      | initialStatus | initialStatusPoints | extraPoints | finalStatus |
      | Bronze        | 0                   | 300         | Silver      |
      | Bronze        | 100                 | 200         | Silver      |
      | Silver        | 0                   | 700         | Gold        |
      | Gold          | 0                   | 1500        | Platinum    |