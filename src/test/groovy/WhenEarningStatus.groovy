package test.groovy

import steps.services.Specification

import java.lang.annotation.Annotation

class WhenEarningStatus implements Specification {
    def "should earn status based on the number of points earned"() {
        given:
        def member = FrequentFlyer.withFrequentFlyerNumber("12345678")
                .named("Joe", "Jones")
                .withStatusPoints(initialPoints)
                .withStatus(initialStatus); // Cria um novo membro Frequent Flyer.

        when: "he earns some extra points on a flight"
        member.earns(earnedPoints).statusPoints() // O membro ganha alguns pontos.

        then:
        member.status == finalStatus // Verifica o status.

        where: // Os dados de teste usados nos passos anteriores vêm daqui.
        initialStatus | initialPoints | earnedPoints | finalStatus
        BRONZE        | 0             | 100          | BRONZE
        BRONZE        | 0             | 300          | SILVER
        BRONZE        | 100           | 200          | SILVER
        SILVER        | 0             | 700          | GOLD
        GOLD          | 0             | 1500         | PLATINUM
    }

    @Override
    boolean equals(Object obj) {
        return false
    }

    @Override
    int hashCode() {
        return 0
    }

    @Override
    String toString() {
        return null
    }

    @Override
    Class<? extends Annotation> annotationType() {
        return null
    }
}
