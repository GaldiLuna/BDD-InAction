[Page(UrlRegex = "localhost:8080/#/welcome")] // O UrlRegex é usado para verificar se um teste está olhando para a página certa.
public class LoginPage : Page // Os page objects do WatiN estendem a classe base Page.
{
    public TextField Email // TextFields, Buttons, e assim por diante são usados para encapsular os elementos web.
    {
        get { return Document.TextField(Find.ByName("email")); }
    }
    public TextField Password
    {
        get { return Document.TextField(Find.ByName("password")); }
    }
    public Button SigninButton
    {
        get { return Document.Button(
            Find.ByCSSSelector(".btn[value='Sign in']")); }
    }
    public void SignInAs(String userEmail, String userPassword) {
        Email.TypeText(userEmail);
        Password.TypeText(userPassword);
        SignInButton.Click();
    }

    // ---> TESTE

    using (var browser = new IE("http://localhost:8080")) // Configura o navegador.
    {
        var loginPage = browser.Page<LoginPage>(); // Cria os page objects.
        loginPage.SignInAs("jane@acme.com", "s3cr3t"); // Especifica a ação sob teste.

        var homePage = browser.Page<HomePage>();
        var message = homePage.WelcomeMessage.Text;
        Assert.That(message, Is.EqualTo("Welcome Jane")); // Verifica os resultados do teste.
    }
}