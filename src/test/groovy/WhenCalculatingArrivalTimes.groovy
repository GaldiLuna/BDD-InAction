import org.joda.time.LocalTime;
//import spock.lang.Specification;
import org.apache.tools.ant.taskdefs.optional.extension.Specification;

class WhenCalculatingArrivalTimes extends Specification {
    // Spock tests must extend the Specification class
    ItineraryService itineraryService;

    // O requisito que você está verificando
    def "should calculate the correct arrival time"() {
        given: "Given I have an itinerary service”
        itineraryService = new ItineraryService();

        when: "When I find the next departures”
        def proposedTrainTimes =
                itineraryService.findNextDepartures("Parramatta",
                        "Town Hall",
                        at('8:00')); // (B)

        then: "Then I should get these proposed times”
        proposedTrainTimes == [at('8:02'), at('8:11'), at('8:14' )] // (C)

    }

    // (D) Uma função utilitária para tornar os valores de tempo mais fáceis de inicializar
    def at(String time) {
        def hour = Integer.valueOf(time.split(':')[0])
        def minute = Integer.valueOf(time.split(':')[1])
        new LocalTime(hour.toInteger(), minute.toInteger())
    }
}