package steps.java;

import java.util.Objects;

public class BaggageRegistration {
    private final String message;

    public BaggageRegistration(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaggageRegistration)) return false;
        BaggageRegistration that = (BaggageRegistration) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "BaggageRegistration{message='" + message + "'}";
    }
}
