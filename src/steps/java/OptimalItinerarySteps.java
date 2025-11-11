package steps.java;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending; // Marcador de pendente
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import java.util.List;
import org.joda.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;

public class OptimalItinerarySteps {
//    String proposedTrainTimes = itineraryService.findNextDepartures(departure, destination, startTime);

    // (B) Um passo Given. JBehave o encontrará através da string no @Given.
    @Given("$line line trains from $lineStart leave $departure for $destination at $departureTimes")
    @Pending // (C) Sinaliza este passo como pendente
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination, List<LocalTime> departureTimes) {
    }

    // (D) Um passo When
    List<LocalTime> proposedTrainTimes; // (Quando) a lista de horários propostos
    @When("I want to travel from $departure to $destination at $time")
    @Pending
    public void whenIWantToTravel(String departure, String destination, LocalTime startTime) {
        ItineraryService itineraryService = new ItineraryService(); // Criando um novo serviço de itinerário
        proposedTrainTimes = itineraryService.findNextDepartures(departure, destination, startTime);
    }

    // (E) Um passo Then
    @Then("I should be told about the trains at: $viableTrainTimes")
    @Pending
    public void shouldFindTheseTrains(List<LocalTime> viableTrainTimes) {
    }

    @Then("I should be told about the trains at: $expectedTrainTimes")
    public void shouldBeInformedAbout(List<LocalTime> expectedTrainTimes) {
        assertThat(proposedTrainTimes).isEqualTo(expectedTrainTimes);
    }

}
