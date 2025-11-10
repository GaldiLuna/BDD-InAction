import org.joda.time.LocalTime;
//import spock.lang.Specification;
import org.apache.tools.ant.taskdefs.optional.extension.Specification;

class WhenFindingArrivalTimes extends Specification {

    TimetableService timetableService = new TimetableService()

    // Função utilitária para criar LocalTime (copiada de 2.4.4)
    def at(int hour, int minute) {
        new LocalTime(hour, minute)
    }

    def "should return all arrival times for a station on a given line"() {
        given: "A Western line that passes through Parramatta"
        // Simulamos uma linha de trem com seus horários de chegada em Parramatta
        def westernLine = Line.named("Western")
                .departingFrom("Emu Plains")
        // A implementação real da classe Line deve armazenar esses horários internamente.
        // Aqui, para o teste unitário, precisamos configurar a linha com os horários que ela "conhece".
                .withArrivalTimesAt("Parramatta",
                        at(7, 58), at(8, 0), at(8, 2), at(8, 11), at(8, 14), at(8, 21)
                )

        when: "I ask for the arrival times at Parramatta on this line"
        def arrivalTimes = timetableService.findArrivalTimes(westernLine, "Parramatta")

        then: "I should get the correct list of times"
        arrivalTimes == [
                at(7, 58),
                at(8, 0),
                at(8, 2),
                at(8, 11),
                at(8, 14),
                at(8, 21)
        ]
    }

    def "should return an empty list if the target station is not on the line"() {
        given: "A Western line that does not pass through Town Hall"
        def westernLine = Line.named("Western")
                .departingFrom("Emu Plains")
                .withArrivalTimesAt("Parramatta", at(8, 0)) // Tem tempos para Parramatta

        def targetStation = "Town Hall" // Mas procuramos por Town Hall

        when: "I ask for the arrival times at Town Hall on this line"
        def arrivalTimes = timetableService.findArrivalTimes(westernLine, targetStation)

        then: "I should get an empty list"
        arrivalTimes.empty
    }

    def "should calculate the correct arrival time"() {
        given:
        def westernLineFromEmuPlains =
                Line.named("Western").departingFrom("Emu Plains")
        timetableService.findLinesThrough("Parramatta","Town Hall") >>
                [westernLineFromEmuPlains]
        timetableService
                .findArrivalTimes(westernLineFromEmuPlains, "Parramatta") >>
                [at(7,58), at(8,00), at(8,02), at(8,11), at(8,14),
                 at(8,21), at(8,31), at(8,36)] // O serviço fingido agora retorna mais viagens.

        when:
        def proposedTrainTimes = itineraryService.
                findNextDepartures("Parramatta", "Town Hall", at(8,00));
        then:
        proposedTrainTimes == [at(8,02), at(8,11),
                               at(8,14), at(8,21)] // Você agora espera que o serviço de itinerário retorne quatro horários.
    }
}