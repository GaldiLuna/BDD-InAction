package steps.java;

import java.time.LocalTime;
import java.util.Date;

public class Trip {
    private final String departure;
    private final String destination;
    private final LocalTime time;
    private final Date date;

    public Trip(String departure, String destination, LocalTime time, Date date) {
        this.departure = departure;
        this.destination = destination;
        this.time = time;
        this.date = date;
    }

    // getters se necessário
}
