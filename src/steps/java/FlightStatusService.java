package steps.java;

import org.springframework.stereotype.Service;
import steps.services.FlightStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FlightStatusService {
    private final Map<String, FlightStatus> statuses = new ConcurrentHashMap<>();

    public FlightStatusUpdater updateStatusForFlight(String flightId) {
        return new FlightStatusUpdater(flightId);
    }

    public FlightStatus getStatus(String flightId) {
        return statuses.get(flightId);
    }

    public class FlightStatusUpdater {
        private final String flightId;

        private FlightStatusUpdater(String flightId) {
            this.flightId = flightId;
        }

        public void to(FlightStatus status) {
            statuses.put(flightId, status);
        }
    }
}
