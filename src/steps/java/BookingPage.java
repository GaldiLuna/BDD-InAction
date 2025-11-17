package steps.java;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.assertThat;

public class BookingPage extends PageObject {
    WebElement search; // Representa o botão Search.

    public void searchButtonShouldBeEnabled() {
        assertThat(search.isEnabled(), is(true)); // Isso falhará se o botão Search não estiver habilitado.
    }

    public boolean searchButtonIsEnabled() { // Apenas retorna o estado do botão Search.
        return search.isEnabled();
        // TESTE: assertThat(bookingPage.searchButtonIsEnabled(), is(true));
    }

    public WebElementState searchButton() {
        return $(search); // O método $() retorna informações sobre o estado atual do elemento web.
    }

    @FindBy(css = ".typeahead li")
    private List<WebElement> typeaheadEntries; // Entradas type-ahead serão armazenadas aqui.

    @FindBy(css = ".featured .featured-destination")
    private List<WebElement> featuredDestinations;

    public BookingPage(WebDriver driver) {
        // Se um elemento não for encontrado, consulta a página web por até cinco segundos.
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public List<String> getTypeaheadEntries() {
        List<String> entries = new ArrayList<String>();
        for(WebElement typeaheadElement : typeaheadEntries) {
            entries.add(typeaheadElement.getText()); // Extrai o conteúdo de texto dos elementos web type-ahead e os retorna como uma lista de Strings.
        }
        return entries;
    }

    private DestinationDeal destinationDealFrom(WebElement destinationEntry) {
        String destinationCity = destinationEntry.findElement(
                By.className("destination-title")).getText(); // (B) Recupera o título do destino.
        String priceValue = destinationEntry.findElement(
                By.className("destination-price")).getText(); // (C) Recupera o preço do destino.

        // Converte o preço para um inteiro.
        int price = Integer.parseInt(priceValue.substring(1));

        return new DestinationDeal(destinationCity, price); // (D) Cria um novo DestinationDeal usando estes valores.
    }

    public List<DestinationDeal> getFeaturedDestinations() {
        List<DestinationDeal> deals = Lists.newArrayList(); // Cria uma nova lista vazia.
        for(WebElement destinationEntry : featuredDestinations) {
            deals.add(destinationDealFrom(destinationEntry)); // Popula a lista convertendo os elementos web para DestinationDeals.
        }
        return deals; // Retorna uma lista de DestinationDeals.
    }
}
