package steps.java;

import io.cucumber.java.en.Given;

public class TransferSteps {
    private final FrequentFlyerHelper frequentFlyerHelper;

    public TransferSteps(FrequentFlyerHelper frequentFlyerHelper) { // (A) O Cucumber injetará a classe auxiliar.
        this.frequentFlyerHelper = frequentFlyerHelper;
    }

    @Given("I am a Frequent Flyer member")
    public void aFrequentFlyerMember(String name) {
        FrequentFlyer member = FrequentFlyer.called(name);
        frequentFlyerHelper.setFrequentFlyer(member); // (B) Você pode então usar esta classe para compartilhar dados entre os passos.
    }
}
