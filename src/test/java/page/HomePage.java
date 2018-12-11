package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    @FindBy(xpath = "//*[@data-control-name='identity_welcome_message']")
    private WebElement welcomeMessage;
    @FindBy(xpath = "//div[@class='nav-search-typeahead']//input[@role='combobox']")
    private WebElement searchField;

    /**
     * Constructor of HomePage class.
     * @param webDriver - webdriver instance from Test.
     */
    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    /**
     * Method to confirm load of page.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return welcomeMessage.isDisplayed()
                && webDriver.getTitle().contains("LinkedIn")
                && webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/");
    }

    /**
     * Make search of searchTerm on HomePage
     * @param searchTerm
     * @return SearchResultsPage object.
     */
    public SearchResultsPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.RETURN);

        return new SearchResultsPage(webDriver);

    }
}
