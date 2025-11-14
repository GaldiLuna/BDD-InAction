package steps.java;

import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(name="email")
    WebElement email;
    @FindBy(name="password")
    WebElement password;
    private final WebDriver driver; // Declara uma instância WebDriver que o page object pode usar para interagir com o navegador.

    public LoginPage(WebDriver driver) { // Passa a instância WebDriver.
        this.driver = driver;
        PageFactory.initElements(driver, this); // Inicializa os campos email e password.
    }

    public void open() { // Abre a página de login.
        driver.get("http://localhost:8080/#/welcome");
    }

    public void signinWithCredentials(String userEmail, // Entra com credenciais do usuário e faz login na aplicação.
                                      String userPassword) {
        driver.findElement(By.name("email")).sendKeys(userEmail); // Detalhes do seletor de elemento web.
        driver.findElement(By.name("password")).sendKeys(userPassword);
        driver.findElement(By.id("signin")).click();
        email.sendKeys(userEmail); // Busca os elementos web na página
        password.sendKeys(userPassword); // antes de usá-los.
    }

    LoginPage loginPage = new LoginPage(driver);
    loginPage.open(); // Abre a página de login.
    loginPage.signInWithCredentials("jane.smith@acme.com", "s3cr3t"); // Faz login com as credenciais especificadas.
}
