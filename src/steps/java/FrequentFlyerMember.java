package steps.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrequentFlyerMember {
    private String name;
    private String status;
    private final List<FlightRecord> flights = new ArrayList<>();

    public FrequentFlyerMember withStatus(String status) {
        this.status = status;
        return this;
    }

    public int getPointsFor(Trip trip) {
        // lógica simples: 100 pontos por viagem; ajuste conforme necessário
        if (trip == null) return 0;
        return 100;
    }

    public FlightAttendance flewOnFlight(Flight flight) {
        return new FlightAttendance(this, flight);
    }

    void recordFlight(Flight flight, Date date) {
        flights.add(new FlightRecord(flight, date));
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    private static class FlightRecord {
        final Flight flight;
        final Date date;
        FlightRecord(Flight flight, Date date) { this.flight = flight; this.date = date; }
    }

    public static class FlightAttendance {
        private final FrequentFlyerMember member;
        private final Flight flight;
        public FlightAttendance(FrequentFlyerMember member, Flight flight) {
            this.member = member;
            this.flight = flight;
        }
        public void on(Date date) {
            member.recordFlight(flight, date);
        }
    }
}
