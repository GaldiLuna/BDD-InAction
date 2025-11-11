//import com.bddinaction.chapter2.model.Line

//import spock.lang.Specification;
import org.apache.tools.ant.taskdefs.optional.extension.Specification;

class WhenFindingLinesThroughStations extends Specification {
    def timetableService = new steps.services.TimetableService()

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
}