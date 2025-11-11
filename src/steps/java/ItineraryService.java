package steps.java;

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
}
