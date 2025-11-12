# um cenário não deve depender de um cenário anterior em execução
Scenario: Adicionar um item ao carrinho
Given I select 'BDD in Action'
When I add it to the cart
Then my cart should contain one copy of 'BDD in Action' // (B) Adicionando algo ao carrinho

Scenario: Comprar itens no meu carrinho
Given I am ready to order
When I pay for the items in my cart // (C) Finalizando a compra
Then I should be sent a copy of 'BDD in Action'

# seu requisito real pode ser capturado de uma forma mais precisa
Cenário: Comprando um item
Given I am looking for a good book to read
When I purchase a copy of 'BDD in Action'
Then I should be sent my copy by specially trained environmentally friendly courier pigeon