package steps.java;

import net.thucydides.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;

public class BookingPage {
    @FindBy(css = ".typeahead li")
    private List<WebElement> typeaheadEntries; // Entradas type-ahead serão armazenadas aqui.

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
    // ...
}
