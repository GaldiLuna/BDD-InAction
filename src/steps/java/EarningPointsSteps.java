package steps.java;

import com.sun.jna.platform.win32.Advapi32Util;
//import Advapi32Util.Account;

import cucumber.api.DataTable;
import jxl.write.DateTime;
import net.thucydides.core.Thucydides;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import java.lang.reflect.Member;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class EarningPointsSteps {
    // Bibliotecas específicas da aplicação.
    TripSteps trips;
    Members members;

    // Objetos de domínio usados para armazenar o estado entre os passos.
    FrequentFlyerMember member;
    Trip trip;

    @Given("I am a $status Frequent Flyer member")
    public void defineAMemberWithStatus(String status) {
        // Encontra ou cria um membro com um dado status.
        member = members.getMember().withStatus(status);
    }

    @When("I fly from $departure to $destination on $date at $time")
    public void whenIFlyFrom(String departure, String destination,
                             DateTime date, LocalTime time) {
        // Pesquisa a viagem correspondente.
        trip = trips.lookupTrip(departure, destination, time, date);
    }

    @Then("I should earn $points points")
    public void thenIShouldEarn(int expectedPoints) {
        // Determina quantos pontos são ganhos para esta viagem.
        int earnedPoints = member.getPointsFor(trip);
        assertThat(earnedPoints).isEqualTo(expectedPoints);
    }

    @Given("the following accounts: $accounts")
    public void givenTheFollowingAccounts(ExamplesTable accounts) {
        // Itera sobre cada linha na tabela de exemplo
        for(Parameters account : accounts.getRowsAsParameters()) {
            // Extrai os valores dos campos para a linha
            String owner = account.valueAs("owner", String.class);
            int points = account.valueAs("points", Integer.class);
            int statusPoints = account.valueAs("statusPoints", Integer.class);

            // Cria novo registro de conta usando estes valores
            inTheTestDatabase.addAccount(Account.forMember(owner)
                    .withPointBalance(points)
                    .withStatusPoints(status));
        }
    }

    @Then("^the accounts should be the following:$")
    public void the_accounts_should_be_the_following(DataTable expectedAccounts)
            throws Throwable {
        // Carrega as contas relevantes do banco de dados.
        List<Account> actualAccounts = loadCurrentAccountsFor(fromMember, toMember);
        // Compara os valores dos campos com os valores na tabela esperada.
        expectedAccounts.diff(actualAccounts);
    }

    @Given("I am a $status Frequent Flyer member")
    public void defineAMemberWithStatus(String status) {
        member = members.getMember().withStatus(status); // Cria um novo membro Frequent Flyer com um dado status.
    }

    @Given("the flying distance between $departure and $destination is $distance km") // O texto da definição de passo, completo com variáveis.
    public void defineTheFlyingDistanceForATrip(String departure, // O método de definição de passo com parâmetros correspondentes.
                                                String destination,
                                                int distance) {
        // TODO: Configure o banco de dados de teste com esta viagem
    }

    @Given("a frequent flyer member called $name")
    public void givenAFrequentFlyerMember(String name) {
        Member member = Members.findByName(name); // Busca este membro do banco de dados.
        Thucydides.getCurrentSession().put("member", member); // Armazena-o para referência posterior.
    }

    @When("$name books a flight")
    public void booksFlight(String name) {
        Member member = Thucydides.getCurrentSession().get("member"); // Reutiliza o membro que você armazenou anteriormente.
        // ...
    }

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); // Um formatador de data usado para analisar a coluna de data.
    @Given("I have travelled on the following flights: $flights")
    public void travelled_on_flights(ExamplesTable flights) // Passa a tabela como um ExamplesTable.
            throws ParseException {
        for(Map<String,String> flightDetails : flights.getRows()) { // Itera sobre as linhas na tabela.
            Flight flight = Flight.number(flightDetails.get("flight")) // Extrai os valores dos campos da linha.
                    .from(flightDetails.get("from"))
                    .to(flightDetails.get("to"));
            Date date = formatter.parse(flightDetails.get("date"));
            member.flewOnFlight(flight).on(date); // Atualiza o banco de dados de teste com os novos dados.
        }
    }

    @When("I fly from $departure to $destination")
    @Alias("I travel from <departure> to <destination>")
    // se as variações forem muito próximas, você também pode usar o formato mais compacto abaixo
    @When("I {fly|travel} from $departure to $destination on $date at $time")
    public void whenIFlyFrom(String departure,
                             String destination,
                             DateTime date,
                             LocalTime time) {
        // TODO: adicionar este voo à conta do membro
    }

    @Then("I should earn $points points")
    public void thenIShouldEarn(int points) {
        assertThat(points).isEqualTo(earnedPoints); // Você ganhou o número certo de pontos?
    }
// A variável earnedPoints é atribuída em um passo anterior.
}
