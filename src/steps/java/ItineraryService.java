package steps.java;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import io.cucumber.messages.internal.com.google.common.collect.Lists;
import io.cucumber.messages.internal.com.google.common.collect.Sets;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import steps.services.Status;

import javax.sound.sampled.Line;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class ItineraryService {
    private List<LocalTime> getArrivalTimesOnLines(List<Line> lines,
                                                   String station) {
        TreeSet<LocalTime> allArrivalTimes = Sets.newTreeSet();
        for(Line line : lines) {
            allArrivalTimes.addAll(
                    timetableService.findArrivalTimes(line,station)); // (B) Coletar horários de chegada
        }
        return new ArrayList<LocalTime>(allArrivalTimes);
    }

    public List<LocalTime> findNextDepartures(String departure,
                                              String destination,
                                              LocalTime startTime) {
        // (A) Quais linhas passam por estas duas estações?
        List<Line> lines = timetableService.findLinesThrough(departure,
                destination);
        // (B) Que trens chegam aqui após a hora de início?
        List<LocalTime> allArrivalTimes = getArrivalTimesOnLines(lines,
                departure);
        // (C) Quais linhas passam por estas duas estações?
        List<LocalTime> candidateArrivalTimes
                = findArrivalTimesAfter(startTime, allArrivalTimes);

        // (D) Eu só quero saber sobre os primeiros três deles.
        return atMost(3, candidateArrivalTimes);
    }

    private List<LocalTime> atMost(int max, List<LocalTime> times) {
        // (E) Um metodo de conveniência para retornar até três horários de chegada.
        return Lists.partition(times, max).get(0);
    }

    private List<LocalTime> findArrivalTimesAfter(LocalTime startTime,
                                                  List<LocalTime> times) {
        List<LocalTime> viableArrivalTimes = Lists.newArrayList();
        for(LocalTime arrivalTime : times) {
            if (arrivalTime.isAfter(startTime)) {
                viableArrivalTimes.add(arrivalTime);
            }
        }
        return viableArrivalTimes;
    }

    TestDatabaseAPI inTheTestDatabase; // Configurar o banco de dados de teste através de uma API dedicada.

    @Given("the flying distance between $departure and $destination is $distance km") // Que texto deve acionar esta definição de passo
    public void defineTheFlyingDistanceForATrip(String departure,
                                                String destination,
                                                int distance) {
        inTheTestDatabase.theDistanceBetween(departure) // Usar esta API para configurar os dados de teste.
                .and(destination).is(distance); // O código que implementa este passo // Injetar os dados de teste no banco de dados.
    }

    @When("I register for the Frequent Flyer program")
    public void whenIRegisterForTheFrequentFlyerProgram() {
        // ...
    }

    // A expressão regular (.*) será passada para o parâmetro status.
    // Expressões regulares aparecem entre parênteses.
    // O símbolo ^ indica o início de uma linha, e o símbolo $ indica o final de uma linha.
    @Given("^I am a (.*) Frequent Flyer member$")
    public void useAMemberWithAGivenStatus(String status) {
        member = members.getMember().withStatus(status);
    }
    // A expressão regular corresponderá aos passos em uma tabela ou em um cenário convencional.

    // Note que as anotações Cucumber vêm de um pacote diferente das do JBehave.
    @Given("I am a Gold Frequent Flyer member")
    public void useAMemberWithGoldStatus() {
        // TODO: use a member with a Gold status
    }

    @When("^I (?:fly|travel) from (.*) to (.*) on (.*)$") // A primeira expressão regular será correspondida, mas não capturada.
    public void I_fly_from(String departure, String destination, Date date){
        // TODO // Agora não há parâmetros suficientes para todas as expressões regulares.
    }

    @Given("^the following accounts:$")
    public void the_following_accounts(List<Account> accounts) {
        InTestDatabase.createAccounts(accounts);
    }

    @Given("the {flying|travelling} distance between (.*) and (.*) is (\\d+) km")
    public void define_flying_distance(String flightMode,
                                       String departure,
                                       String destination,
                                       int distance) {

        throw new PendingException("Not finished yet!");
    }
}
