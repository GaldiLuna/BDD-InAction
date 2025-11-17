package steps.java;

import net.thucydides.core.model.DataTable;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.json.JSONException;

import static com.google.common.collect.Lists.newArrayList;

public class FlightDetailsSteps {
    String flightNumber; // Anota qual número de voo você está procurando.
    Flight matchingFlight;

    @Given("^I need to know the details of flight number (.*)$")
    public void flight_number(String flightNumber) throws Throwable {
        this.flightNumber = flightNumber;
    }

    @When("^I request the details about this flight$")
    public void request_flight_details() throws Throwable {
        FlightStatusClient client = new FlightStatusClient();
        matchingFlight = client.findByFlightNumber(flightNumber); // (B) Escreve um cliente de serviço web simples para acessar o serviço web.
    }

    @Then("^I should receive the following:$")
    public void verify_details(DataTable flightDetails) throws Throwable {
        flightDetails.diff(newArrayList(matchingFlight)); // (C) Compara os dados esperados com o que o serviço web retornou.
    }

    private final String BASE_URL = "http://localhost:8080/rest/flights";

    public Flight findByFlightNumber(String flightNumber) {
        Client client = ClientBuilder.newClient(); // Cria um novo cliente de serviço web.
        WebTarget webTarget = client.target(BASE_URL).path(flightNumber); // Especifica o caminho do recurso que você está invocando.
        return webTarget.request().buildGet().invoke(Flight.class); // Recupera o resultado e o converte para um objeto Flight.
    }

    String receivedJsonData; // Recupera os resultados da pesquisa como um documento JSON.

    @When("^I request the details about this flight in JSON format$")
    public void request_details_in_json_format() {
        receivedJsonData = client.findByFlightNumberInJson(flightNumber);
    }

    @Then("^I should receive:$")
    public void should_receive_json_data(String expectedJsonData)
            throws JSONException {
        JSONAssert.assertEquals(expectedJsonData, // Compara o JSON recuperado com os dados esperados usando a biblioteca JSONassert.
                receivedJsonData,
                JSONCompareMode.LENIENT);
    }

    public String findByFlightNumberInJson(String flightNumber) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(BASE_URL).path(flightNumber);
        return webTarget.request().buildGet().invoke(String.class); // Retorna o resultado em formato JSON bruto.
    }
}
