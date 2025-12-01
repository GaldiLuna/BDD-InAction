package steps.java;

public class StatusBoundaries extends ColumnFixture {
    public String initialStatus;
    public int initialStatusPoints;
    public int extraPoints;

    public String finalStatus() {
        Object finalStatus = determineFinalStatus(initialStatus, initialStatusPoints, extraPoints);
        return finalStatus.toString(); // Invoca o código de produção para determinar o valor do status final.
    }

    private Object determineFinalStatus(String initialStatus, int initialStatusPoints, int extraPoints) {
        return null;
    }
}
