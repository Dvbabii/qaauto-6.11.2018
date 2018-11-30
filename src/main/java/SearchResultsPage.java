import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchFiltersBar;

    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    boolean isPageLoaded() {
        return searchFiltersBar.isDisplayed()
                && webDriver.getTitle().contains("| Поиск | LinkedIn")
                //&& webDriver.getTitle().contains(searchTerm)
                && webDriver.getCurrentUrl().equals("/search/results");
    }

}
