package steps.java;

import java.time.LocalTime;
import java.util.Date;

public class TripSteps {
    public Trip lookupTrip(String departure, String destination, LocalTime time, Date date) {
        return new Trip(departure, destination, time, date);
    }
}
