package steps.java;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class BaggageRegistrationSteps {
    String message;
    BaggageRegistration baggageRegistration;
    RegistrationService service;

    @Given("^a baggage registration message:$")
    public void a_baggage_registration_message(String messageText)
            throws Throwable {
        this.message = messageText;
    }

    @When("^the baggage registration is processed$")
    public void the_baggage_registration_is_processed() throws Throwable {
        service = new RegistrationService(); // (B) Cria uma nova instância de serviço.
        baggageRegistration = service.registerBaggage(message); // (C) Processa a mensagem de registro.
    }

    @Then("^the registration details should be:$")
    public void registration_details_should_be(
            List<BaggageRegistration> expectedDetails)
            throws Throwable {
        BaggageRegistration expected = expectedDetails.get(0);
        assertThat(baggageRegistration). isEqualTo(expected); // (D) Verifica os resultados.
    }
}
