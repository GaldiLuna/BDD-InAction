package steps.java;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

public class ItineraryService {
    private List<LocalTime> getArrivalTimesOnLines(List<Line> lines,
                                                   String station) {
        TreeSet<LocalTime> allArrivalTimes = Sets.newTreeSet();
        for(Line line : lines) {
            allArrivalTimes.addAll(
                    timetableService.findArrivalTimes(line,station)); // (B) Coletar horários de chegada
        }
        return new ArrayList<LocalTime>(allArrivalTimes);
    }

    public List<LocalTime> findNextDepartures(String departure,
                                              String destination,
                                              LocalTime startTime) {
        // (A) Quais linhas passam por estas duas estações?
        List<Line> lines = timetableService.findLinesThrough(departure,
                destination);
        // (B) Que trens chegam aqui após a hora de início?
        List<LocalTime> allArrivalTimes = getArrivalTimesOnLines(lines,
                departure);
        // (C) Quais linhas passam por estas duas estações?
        List<LocalTime> candidateArrivalTimes
                = findArrivalTimesAfter(startTime, allArrivalTimes);

        // (D) Eu só quero saber sobre os primeiros três deles.
        return atMost(3, candidateArrivalTimes);
    }

    private List<LocalTime> atMost(int max, List<LocalTime> times) {
        // (E) Um metodo de conveniência para retornar até três horários de chegada.
        return Lists.partition(times, max).get(0);
    }

    private List<LocalTime> findArrivalTimesAfter(LocalTime startTime,
                                                  List<LocalTime> times) {
        List<LocalTime> viableArrivalTimes = Lists.newArrayList();
        for(LocalTime arrivalTime : times) {
            if (arrivalTime.isAfter(startTime)) {
                viableArrivalTimes.add(arrivalTime);
            }
        }
        return viableArrivalTimes;
    }

    TestDatabaseAPI inTheTestDatabase; // Configurar o banco de dados de teste através de uma API dedicada.

    @Given("the flying distance between $departure and $destination is $distance km") // Que texto deve acionar esta definição de passo
    public void defineTheFlyingDistanceForATrip(String departure,
                                                String destination,
                                                int distance) {
        inTheTestDatabase.theDistanceBetween(departure) // Usar esta API para configurar os dados de teste.
                .and(destination).is(distance); // O código que implementa este passo // Injetar os dados de teste no banco de dados.
    }

    @When("I register for the Frequent Flyer program")
    public void whenIRegisterForTheFrequentFlyerProgram() {
        // ...
    }

    @Given("I am a $status Frequent Flyer member")
    public void useAMemberWithAGivenStatus(Status status) {
        // ...
    }
}
