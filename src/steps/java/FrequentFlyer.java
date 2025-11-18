package steps.java;

import steps.services.Status;

public class FrequentFlyer {
    private String frequentFlyerNumber;
    private String firstName;
    private String lastName;
    private Status status = Status.Bronze; // Status inicial padronizado.
    // Getters omitidos por brevidade

    // Construtor privado
    protected FrequentFlyer(String frequentFlyerNumber,
                            String firstName,
                            String lastName) {...}

    public Status getStatus() {
        return status;
    }

    public static FFBuilder withFrequentFlyerNumber(String number) { // Usando um padrão builder
        return new FFBuilder(number);
    }

    public static class FFBuilder {
        private String frequentFlyerNumber;

        public FrequentFlyerBuilder(String frequentFlyerNumber) {
            this.frequentFlyerNumber = frequentFlyerNumber;
        }

        public FrequentFlyer named(String firstName, String lastName) {
            return new FrequentFlyer(frequentFlyerNumber,
                    firstName,
                    lastName);
        }
    }
}
