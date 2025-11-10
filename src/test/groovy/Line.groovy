import org.joda.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class Line {

    private final String name;
    private final String lineStart;
    // Armazena os horários de chegada por estação
    private Map<String, List<LocalTime>> arrivalTimesByStation = new HashMap<>();

    // Construtores auxiliares e estáticos (omitidos por brevidade, mas necessários)
    public Line(String name, String lineStart) {
        this.name = name;
        this.lineStart = lineStart;
    }

    public static Line named(String name) {
        return new Line(name, "");
    }

    public Line departingFrom(String lineStart) {
        // Retorna uma nova instância ou modifica esta (depende da convenção do projeto)
        return new Line(this.name, lineStart);
    }
    // FIM dos Construtores

    // **MÉTODO AUXILIAR PARA SETUP DE TESTE (NECESSÁRIO PARA O TESTE SPOCK)**
    public Line withArrivalTimesAt(String station, LocalTime... times) {
        // Garante que o teste possa popular os horários internos da linha
        List<LocalTime> timesList = new ArrayList<>();
        Collections.addAll(timesList, times);
        this.arrivalTimesByStation.put(station, timesList);
        return this;
    }

    // **MÉTODO DE DOMÍNIO (NECESSÁRIO PARA O TimetableService)**
    public List<LocalTime> getArrivalTimesAt(String station) {
        // Retorna os horários ou uma lista vazia se a estação não tiver horários definidos
        return arrivalTimesByStation.getOrDefault(station, Collections.emptyList());
    }
}