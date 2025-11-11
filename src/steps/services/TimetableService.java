package steps.services;

//import com.bddinaction.chapter2.model.Line;
import org.joda.time.LocalTime;
import java.util.List;


public interface TimetableService {

    // Este é o metodo que o ItineraryService estava "mockando"
    public List<LocalTime> findArrivalTimes(Line line, String targetStation) {

        // Chamamos um metodo no objeto Line para obter os horários de chegada.
        // O objeto Line é o responsável por saber se ele atende aquela estação e quais são os horários.
        return line.getArrivalTimesAt(targetStation);
    }

    // Assinatura (mantida por completude, embora não implementada neste exercício)
    public List<Line> findLinesThrough(String departure, String destination) {
        // ...
        return Collections.emptyList(); // Apenas um placeholder
    }
}