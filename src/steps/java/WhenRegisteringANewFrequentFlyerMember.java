package steps.java;

import org.junit.Test;

import static org.junit.Assert.assertThat;

public class WhenRegisteringANewFrequentFlyerMember { // Identifica qual requisito ou recurso técnico detalhado você está ilustrando.

    // Especifica em qual requisito de negócio você está trabalhando.
    @Test
    public void should_be_able_to_create_a_new_member() {
        FrequentFlyer member
                = FrequentFlyer.withFrequentFlyerNumber("123456789") // Cria um novo membro Frequent Flyer.
                .named("Jill", "Smith");

        assertThat(member.getFirstName()).isEqualTo("Jill"); // Verifica os resultados esperados.
        assertThat(member.getLastName()).isEqualTo("Smith");
        assertThat(member.getFrequentFlyerNumber()).isEqualTo("123456789");
    }
}
