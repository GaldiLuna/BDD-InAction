package steps.java;

public class Flight {
    private final String number;
    private String from;
    private String to;

    private Flight(String number) { this.number = number; }

    public static Flight number(String number) {
        return new Flight(number);
    }

    public Flight from(String from) { this.from = from; return this; }
    public Flight to(String to) { this.to = to; return this; }

    public String getNumber() { return number; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
}
