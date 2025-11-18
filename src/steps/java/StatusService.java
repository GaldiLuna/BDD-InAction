package steps.java;

import org.junit.Test;
import steps.services.Status;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatusService {
    protected FrequentFlyer(String frequentFlyerNumber,
                            String firstName,
                            String lastName,
                            Status status,
                            int statusPoints,
                            StatusService statusService) { // Passa um objeto StatusService no construtor.
        // ...
        this.statusService = statusService;
    }

    @Test
    public void should_cumulate_points_with_each_flight() {
        // GIVEN
        StatusService statusService = mock(StatusService.class); // Usa uma versão mock do serviço de status.
        when(statusService.statusLevelFor(300)).thenReturn(Status.Silver); // Retorna "Silver" para um valor de 300.
        FrequentFlyer member = new FrequentFlyer("12345678","Joe", "Bloggs",
                statusService); // Usa o serviço mock para este membro Frequent Flyer.
        // WHEN
        member.earns(100).statusPoints();
        member.earns(200).statusPoints();
        // THEN
        assertThat(member.getStatusPoints(),is(greaterThanOrEqualTo((300))));
        assertThat(member.getStatus(), is(Status.Silver));
    }

    StatusService statusService = new InMemoryStatusService();
    @Test
    public void should_stay_on_bronze_for_zero_points() { // O nível de status para zero pontos deve ser Bronze.
        assertThat(statusService.statusLevelFor(0), is(Status.Bronze));
    }

    @Test
    public void should_stay_on_bronze_up_to_299_points() { // Qual é o nível de status para 299 pontos?
        assertThat(Status.statusLevelFor(299), is(Status.Bronze));
    }

    @Test
    public void should_earn_silver_for_300_points() { // Qual é o nível de status para 300 pontos?
        assertThat(Status.statusLevelFor(300), is(Status.Silver));
    }
}
