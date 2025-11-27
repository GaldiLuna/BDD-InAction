package test.groovy

class FindingFlights {
    def "Find flight details by flight number"() {
        given: "I need to know the details of flight number FH-101" // Um texto curto explica o que cada passo está fazendo.
        def flightService = new FrequentFlyerFlightService();
        def flightNumber = "FH-101"
        def airport = "MEL"

        when: "I request the details about this flight"
        def flightDetails = flightService.findFlightByNumber(airport,
                flightNumber);

        then: "I should receive the correct flight details"
        flightDetails.flightNumber == "FH-101" &&
                flightDetails.departure.name == "Melbourne" &&
                flightDetails.destination.name == "Sydney" &&
                flightDetails.time == "06:00"
    }
}
