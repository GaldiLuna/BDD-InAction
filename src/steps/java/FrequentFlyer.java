package steps.java;

import steps.services.Status;

import static org.jboss.netty.handler.codec.spdy.SpdyHeaders.setStatus;

public class FrequentFlyer {
    private String frequentFlyerNumber;
    private String firstName;
    private String lastName;
    private Status status = Status.Bronze; // Status inicial padronizado.
    private int statusPoints;
    // Getters omitidos por brevidade

    // Construtor privado
    protected FrequentFlyer(String frequentFlyerNumber,
                            String firstName,
                            String lastName, StatusService statusService) { }

    public static FrequentFlyer called(String name) {
        return null;
    }

    public Status getStatus() {
        return status;
    }

    public static FFBuilder withFrequentFlyerNumber(String number) { // Usando um padrão builder
        return new FFBuilder(number);
    }

    public Object getFirstName() {
        return firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public Object getFrequentFlyerNumber() {
        return frequentFlyerNumber;
    }

    public FrequentFlyerMember withStatusPoints(int initialPoints) {
        return null;
    }

    public Object earns(int earnedPoints) {
        return null;
    }

    public Integer getStatusPoints() {
        return 0;
    }

    public static class FFBuilder {
        private String frequentFlyerNumber;

        public void FrequentFlyerBuilder(String frequentFlyerNumber) {
            this.frequentFlyerNumber = frequentFlyerNumber;
        }

        public FFBuilder(String number) {
        }

        public FrequentFlyer named(String firstName, String lastName) {
            return new FrequentFlyer(frequentFlyerNumber,
                    firstName,
                    lastName, statusService);
        }
    }

    static StatusService statusService;
    public void setStatusPoints(int statusPoints) {
        this.statusPoints = statusPoints;
        updateStatusLevel(); // Atualiza o nível de status para o nível apropriado.
    }

    private void updateStatusLevel() {
        setStatus(Status.statusLevelFor(statusPoints)); // Você precisa de um serviço para dizer qual status pode ser obtido para um dado número de pontos.
    }

    private void setStatus(Object statusPoints) {
    }
}
