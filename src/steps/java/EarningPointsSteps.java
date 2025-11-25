package steps.java;

import net.thucydides.core.Thucydides;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;
//import org.jbehave.core.model.Parameters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class EarningPointsSteps {

    TripSteps trips = new TripSteps();
    Members members = new Members();

    FrequentFlyerMember member;
    Trip trip;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    @Given("I am a $status Frequent Flyer member")
    public void defineAMemberWithStatus(String status) {
        member = members.getMember().withStatus(status);
    }

    @When("I fly from $departure to $destination on $date at $time")
    @Alias("I travel from <departure> to <destination> on <date> at <time>")
    public void whenIFlyFrom(String departure, String destination,
                             String date, LocalTime time) throws ParseException {
        Date parsedDate = formatter.parse(date);
        trip = trips.lookupTrip(departure, destination, time, parsedDate);
    }

    @Then("I should earn $points points")
    public void thenIShouldEarn(int expectedPoints) {
        int earnedPoints = member.getPointsFor(trip);
        assertThat(earnedPoints).isEqualTo(expectedPoints);
    }

    @Given("the following accounts: $accounts")
    public void givenTheFollowingAccounts(ExamplesTable accounts) {
        for (Parameters account : accounts.getRowsAsParameters()) {
            String owner = account.valueAs("owner", String.class);
            int points = account.valueAs("points", Integer.class);
            int statusPoints = account.valueAs("statusPoints", Integer.class);

            TestDatabase.IN.addAccount(
                    Account.forMember(owner)
                            .withPointBalance(points)
                            .withStatusPoints(statusPoints)
            );
        }
    }

    @Then("^the accounts should be the following:$")
    public void the_accounts_should_be_the_following(ExamplesTable expectedAccounts) {
        List<Account> actualAccounts = TestDatabase.IN.getAccounts();
        List<Map<String,String>> expectedRows = expectedAccounts.getRows();

        assertThat(actualAccounts.size()).isEqualTo(expectedRows.size());

        for (int i = 0; i < expectedRows.size(); i++) {
            Map<String,String> expected = expectedRows.get(i);
            Account actual = actualAccounts.get(i);

            assertThat(actual.getOwner()).isEqualTo(expected.get("owner"));
            assertThat(actual.getPointBalance()).isEqualTo(Integer.parseInt(expected.get("points")));
            assertThat(actual.getStatusPoints()).isEqualTo(Integer.parseInt(expected.get("statusPoints")));
        }
    }

    @Given("a frequent flyer member called $name")
    public void givenAFrequentFlyerMember(String name) {
        FrequentFlyerMember m = Members.findByName(name);
        Thucydides.getCurrentSession().put("member", m);
    }

    @When("$name books a flight")
    public void booksFlight(String name) {
        Object o = Thucydides.getCurrentSession().get("member");
        if (o instanceof FrequentFlyerMember) {
            FrequentFlyerMember m = (FrequentFlyerMember) o;
            // lógica de reserva (stub)
        }
    }

    @Given("I have travelled on the following flights: $flights")
    public void travelled_on_flights(ExamplesTable flights) throws ParseException {
        for (Map<String, String> flightDetails : flights.getRows()) {
            Flight flight = Flight.number(flightDetails.get("flight"))
                    .from(flightDetails.get("from"))
                    .to(flightDetails.get("to"));
            Date date = formatter.parse(flightDetails.get("date"));
            member.flewOnFlight(flight).on(date);
        }
    }
}
