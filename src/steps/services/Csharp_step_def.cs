[Given(@"the flying distance between (.*) and (.*) is (.*) km")] // (B) O símbolo '@' desabilita sequências de escape.
public void GivenTheFlyingDistance(string departure,
                                   string destination,
                                   int distance)
{
// ...
}

[When(@"I go from (.*) to (.*)")]
[When(@"I travel from (.*) to (.*)")]
public void GivenTheFlyingDistance(string departure, string destination)
{
// ...
}

[Given]
public void I_am_a_STATUS_Frequent_Flyer_member(string status)
{
// ...
}

[Given(@"a frequent flyer member called {name}")]
public void GivenAFrequentFlyerMember(string name)
{
    var member = MemberHelper.FindByName(name); // Busca membro do banco de dados
    ScenarioContext.Current[name] = member; // Armazena membro para referência posterior
}

[When(@"{name} books a flight")]
public void BooksAFlight(string name)
{
    var member = ScenarioContext.Current[name]; // Reutiliza o membro que você armazenou anteriormente.
// ...
}

[Given(@"the following accounts:")]
public void givenTheFollowingAccounts(Table accounts)
{
    foreach (var row in accounts.Rows)
    {
        var owner = row["owner"];
        var points = row["points"];
        var statusPoints = row["statusPoints "];
    // ...
    }
}

[Given(@"the following accounts:")]
public void givenTheFollowingAccounts(Table accounts)
{
    var accounts = table.CreateSet<Account>(); // Converte cada linha da tabela em um objeto Account.
}

[Then(@"the accounts should be the following ")]
public void givenTheFollowingAccounts(Table expectedAccounts)
{
    actualAccounts = AccountDatabaseHelper.GetRelevantAccounts(); // Recupera as contas reais do banco de dados.
    expectedAccounts.CompareToSet<Account>(actualAccounts); // Compara as contas esperadas com o que você realmente obteve.
}