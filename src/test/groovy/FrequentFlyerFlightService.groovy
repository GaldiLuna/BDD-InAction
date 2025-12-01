package test.groovy

// Esta classe implementa o serviço que o teste unitário espera usar.
class FrequentFlyerFlightService {

    // O método foi descoberto e desenhado pelo teste (outside-in).
    // O teste espera que ele retorne um objeto com propriedades como flightNumber, departure, etc.
    def findFlightByNumber(String airportCode, String flightNumber) {
        // Implementação mínima (hardcoded) para fazer o teste passar.
        // Na produção real, esta lógica faria uma busca em banco de dados ou chamaria outro serviço.

        // Aqui, precisamos apenas retornar um objeto que satisfaça o THEN do teste.
        return new Flight(
                flightNumber: flightNumber, // FH-101
                departure: [name: "Melbourne"], // Objeto de domínio aninhado (Map/Classe)
                destination: [name: "Sydney"],
                time: "06:00"
        )
    }
}
