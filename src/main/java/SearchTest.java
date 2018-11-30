import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {

    WebDriver webDriver;
    HomePage homePage;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        homePage = loginPage.login("truekvazar@gmail.com","dimon007");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        webDriver.quit();

    }

    /**
     * Preconditions:
     * - Open browser
     * - Navigate to Linkedin.com
     * - Login with valid credentials
     * Scenario:
     * - Verify Home Page is loaded
     * - Enter "HR" in search field
     * - Press "Enter"
     * - Verify SearchResults Page is loaded
     * - Verify result list contains 10 items
     * - Verify each item contains searchTerm
     * Postcondition:
     * - Close browser
     */
    @Test
    public void basicSearchTest(){
        String searchTerm = "HR";
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");

        SearchResultsPage searchResultsPage = homePage.search(searchTerm);
        Assert.assertTrue(searchResultsPage.isPageLoaded(), "Result page is not loaded.");


    }

}
