package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchFiltersBar;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    /**
     * Constructor of SearchResultsPage class.
     * @param webDriver - webdriver instance from Test.
     */
    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        waitUntilElementIsVisible(searchFiltersBar, 5);
    }

    /**
     * Method to confirm load of page.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return searchFiltersBar.isDisplayed()
                && webDriver.getTitle().contains("| Поиск | LinkedIn")
                //&& webDriver.getTitle().contains(searchTerm)
                && webDriver.getCurrentUrl().contains("/search/results");
    }

    /**
     * Getting number of results displayed.
     * @return number of results.
     */
    public int getSearchResultsCount() {
        return searchResults.size();
    }

    /**
     * Method to return list of results after scrolling to every result.
     * @return list od displayed results.
     */
    public List<String> getSearchResults() {
        List<String> searchResultsList = new ArrayList<String>();
        for (WebElement searchResult : searchResults) {
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", searchResult);
            String searchResultText = searchResult.getText();
            searchResultsList.add(searchResultText);
        }
        return searchResultsList;

    }

}
