package steps.java;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import steps.services.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import steps.services.WebAppConfiguration;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static java.nio.file.Paths.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class FlightStatusSteps {
    @Autowired // Injeta um serviço configurado pelo Spring.
    private FlightStatusService flightStatusService;
    @Autowired
    private MockMvc mockMvc; // Usa uma classe auxiliar do Spring para ajudar a testar a camada controller.

    private String flightId;
    private ResultActions resultActions;

    @Given("^that flight (.*) has no reported delays$")
    public void no_reported_delays_for_flight(String flightId)
            throws Throwable {
        this.flightId = flightId;
        flightStatusService.updateStatusForFlight(flightId) // Prepara os dados de teste.
                .to(FlightStatus.ON_TIME);
    }

    @When("^I check the flight status$")
    public void I_check_the_flight_status() throws Throwable {
        resultActions = mockMvc.perform(get("/flights/{flightId}", flightId)).andExpect(status().isOk());
    }

    @Then("^I should see that it is (.*)$")
    public void I_should_see_that_it_is(FlightStatus expectedStatus)
            throws Throwable {
        resultActions.andExpect(view().name("flightstatus")) // A view correta é usada para exibir os resultados?
                .andExpect(model().attribute("flightId",
                        is(flightId)))
                .andExpect(model().attribute("flightStatus", // Os dados do modelo recuperados estão corretos?
                        is(expectedStatus.toString())));
    }

    private Object is(String flightId) {
        return null;
    }

    @Then("^I should see its scheduled arrival time of (.*)$")
    public void expect_scheduled_arrival_time_of(String arrivalTime)
            throws Throwable {
        resultActions.andExpect(model().attribute("eta", // O horário de chegada está correto?
                is(arrivalTime)));
    }
}
