package steps.java;

import org.junit.Test;
import steps.services.Status;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertThat;
import static steps.services.Status.*;

@RunWith(Parameterized.class)
public class WhenEarningStatusLevels {
    @Parameterized.Parameters
    public static Collection pointsPerStatus() {
        return Arrays.asList(new Object[][]{ // Os dados de teste vão aqui.
                {Bronze, 0,
                        100, Bronze},
                {Bronze, 0,
                        300, Silver},
                {Bronze, 100, 200, Silver},
                {Silver, 0,
                        700, Gold},
                {Gold,
                        0,
                        1500, Platinum}
        });
    }

    Status initialStatus, finalStatus;
    int initialPoints, earnedPoints; // Os valores dos dados de teste são armazenados nestes campos.

    public WhenEarningStatusLevels(Status initialStatus,
                                   int initialPoints,
                                   int earnedPoints,
                                   Status finalStatus) { // Os dados de teste são passados para o teste unitário via construtor.
        this.initialStatus = initialStatus;
        this.initialPoints = initialPoints;
        this.earnedPoints = earnedPoints;
        this.finalStatus = finalStatus;
    }

    @Test
    public void should_earn_new_status_based_on_point_thresholds() {
        FrequentFlyer member
                = FrequentFlyer.withFrequentFlyerNumber("12345678")
                .named("Joe", "Jones")
                .withStatusPoints(initialPoints)
                .withStatus(initialStatus);

        member.earns(earnedPoints).statusPoints(); // O teste é executado uma vez para cada linha de dados.

        assertThat(member.getStatus()).isEqualTo(finalStatus);
    }
}
