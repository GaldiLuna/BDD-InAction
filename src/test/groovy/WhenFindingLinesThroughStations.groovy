import steps.services.DefaultTimetableService;
import steps.services.Specification

import java.lang.annotation.Annotation;

class WhenFindingLinesThroughStations implements Specification {
    def timetableService = new DefaultTimetableService()

    def "should find the correct lines between two stations"() {
        when: // Ação sob teste.
        def lines = timetableService.findLinesThrough(departure, destination)

        then: // O serviço de horário deve retornar estas linhas.
        def expectedLine = Line.named(lineName).departingFrom(lineDeparture)
        lines == [expectedLine]

        where: // (B) Usar dados de exemplo desta tabela.
        departure    | destination | lineName  | lineDeparture
        "Parramatta" | "Town Hall" | "Western" | "Emu Plains"
        "Town Hall"  | "Parramatta"| "Western" | "North Richmond"
        "Strathfield"| "Epping"    | "Epping"  | "City"
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