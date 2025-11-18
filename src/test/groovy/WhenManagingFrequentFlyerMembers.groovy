package test.groovy

class WhenManagingFrequentFlyerMembers extends Specification { // Todas as especificações Spock estendem a classe Specification.
    def "a new frequent flyer should have Bronze status"() {
        given: // (B) Given
    def member = FrequentFlyer.withFrequentFlyerNumber("12345678").
            named("Joe","Bloggs")

    when: // (C) When
    def status = member.status

    then: // (D) Then
    status == FrequentFlyerStatus.BRONZE
    }

    def "should upgrade status when enough status points are acquired"() {
        given: "a frequent flyer member with some points"
        def member = FrequentFlyer.withFrequentFlyerNumber("12345678").
                named("Joe", "Bloggs").
                withStatusPoints(initialPoints).
                withStatus(initialStatus) // (B) Precondition

        when: "he earns some extra points on a flight" // (C) Action
        member.earns(extraPoints).statusPoints()

        then: "he may or may not be upgraded to a new status" // (D) Expected outcome
        member.getStatus() == expectedStatus

        where: // (E) Examples
        initialStatus | initialPoints | extraPoints | expectedStatus
        BRONZE        | 0             | 299         | BRONZE
        BRONZE        | 0             | 300         | SILVER
        SILVER        | 0             | 699         | SILVER
        SILVER        | 0             | 700         | GOLD
        GOLD          | 0             | 1499        | GOLD
        GOLD          | 0             | 1500        | PLATINUM // Os dados de teste usados nos passos anteriores vêm daqui.
    }
}
