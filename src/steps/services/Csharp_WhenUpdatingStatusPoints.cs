[TestFixture]
public class WhenUpdatingStatusPoints : nspec
{
    [Test]
    public void ShouldBeAbleToAddStatusPointsEarnedFromAFlight()
    {...}
    [Test]
    public void ShouldUpdateStatusWhenEnoughStatusPointsAreEarned()
    {...}


    FrequentFlyer member;

    void before_each() // Executado antes de cada especificação, como o beforeEach do Jasmine.
    {
        member = new FrequentFlyer();
    }

    void earning_status_points() // Um contexto de especificação
    {
        context["When the frequent flyer account is created"] = () => // Contextos aninhados
        {
            it["should have BRONZE status"] = () => // Especificações
                member.getStatus().should_be(Status.Bronze); // Asserções no estilo "should"

            it["should have 0 status points"] = () =>
                member.getStatusPoints().should_be(0);
        };

        context["When cumulating Frequent Flyer points"] = () =>
        {
            it["should earn points for each flight"] = () =>
            {
                member.earnStatusPoints(100);
                member.earnStatusPoints(50);
                member.getStatusPoints().should_be(150);
            };
            it["should get upgrade when enough points are earned"] = () =>
            {
                member.earnStatusPoints(300);
                member.getStatus().should_be(Status.Silver);
            };
        };
    }
}