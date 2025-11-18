package test.groovy

class WhenCheckingMinimumStatusPoints extends Specification {
    def "should know the minimum points required for each status level"() {
        expect: // O expect do Spock pode ser usado para asserções de uma linha.
        FrequentFlyerStatus.statusLevelFor(points) == expectedStatus // O nível de status obtido com um dado número de pontos.

        where: // (Examples)
        points | expectedStatus // O status esperado para uma gama de valores de pontos.
        0      | BRONZE
        299    | BRONZE
        300    | SILVER
        699    | SILVER
        700    | GOLD
        1499   | GOLD
        1500   | PLATINUM
    }

    def lastPlaneOut = new Flight(from: "Sydney",
            to:"Hong Kong",
            number:"FH-525")
}
