package steps.java;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class FlightDetailsSteps {
    String flightNumber; // Anota qual número de voo você está procurando.
    Flight matchingFlight;
    private final FlightStatusClient client = new FlightStatusClient();

    @Given("^I need to know the details of flight number (.*)$")
    public void flight_number(String flightNumber) throws Throwable {
        this.flightNumber = flightNumber;
    }

    @When("^I request the details about this flight$")
    public void request_flight_details() throws Throwable {
        matchingFlight = client.findByFlightNumber(flightNumber); // (B) Escreve um cliente de serviço web simples para acessar o serviço web.
    }

    @Then("^I should receive the following:$")
    public void verify_details(ExamplesTable flightDetails) throws Throwable {
        // Usa apenas a primeira linha da tabela esperada para comparação
        if (flightDetails.getRowCount() > 0) {
            var expectedRow = flightDetails.getRows().get(0);
            String expectedNumber = expectedRow.get("number");
            String expectedFrom = expectedRow.get("from");
            String expectedTo = expectedRow.get("to");

            assertEquals(expectedNumber, matchingFlight != null ? matchingFlight.getNumber() : null);
            assertEquals(expectedFrom, matchingFlight != null ? matchingFlight.getFrom() : null);
            assertEquals(expectedTo, matchingFlight != null ? matchingFlight.getTo() : null);
        }
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

}
