package steps.java;

public class StatusBoundaries extends ColumnFixture {
    public String initialStatus;
    public int initialStatusPoints;
    public int extraPoints;

    public String finalStatus() {
        finalStatus = ...
        return finalStatus; // Invoca o código de produção para determinar o valor do status final.
    }
}
