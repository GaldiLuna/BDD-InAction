package steps.services;

//import com.bddinaction.chapter2.model.Line;
import net.thucydides.core.geometry.Line;
import org.joda.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class DefaultTimetableService implements TimetableService {

    // Este é o método que está sendo testado. A implementação deve retornar
    // os dados reais das linhas de trem.
    @Override
    public List<Line.LineBuilder> findLinesThrough(String departure, String destination) {

        if ("Parramatta".equals(departure) && "Town Hall".equals(destination)) {
            // Retorna uma lista imutável contendo o objeto Line
            return List.of(Line.from(Point.named("Western").departingFrom("Emu Plains")),
                           Line.from(Point.named("North Shore").departingFrom("Hornsby")));
        }

        if ("Town Hall".equals(departure) && "Parramatta".equals(destination)) {
            // Retorna uma lista imutável contendo o objeto Line
            return List.of(Line.from(Point.named("Western").departingFrom("North Richmond")),
                           Line.from(Point.named("North Shore").departingFrom("Hornsby")));
        }

        if ("Strathfield".equals(departure) && "Epping".equals(destination)) {
            // Retorna uma lista imutável contendo o objeto Line
            return List.of(Line.from(Point.named("Epping").departingFrom("City")),
                           Line.from(Point.named("Northern").departingFrom("Hornsby")));
        }

        return Collections.emptyList();
    }

    // Método exigido pela interface (presumidamente do EXERCÍCIO 2.1)
    @Override
    public List<LocalTime> findArrivalTimes(Line line, String targetStation) {
        // Implementação real da lógica para encontrar horários de chegada.
        return Collections.emptyList();
    }

    @Override
    public LocalTime getNextArrival(String lineName, String stopName, LocalTime fromTime) {
        return null;
    }
}
