package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchResultsPage;

import java.util.List;

public class SearchTest extends BaseTest {

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
     **/
    @Test
    public void basicSearchTest() {
        HomePage homePage= loginPage.login("link.test.dvb@gmail.com","Linktest03");
        String searchTerm = "HR";
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");

        SearchResultsPage searchResultsPage = homePage.search(searchTerm);
        Assert.assertTrue(searchResultsPage.isPageLoaded(), "Result page is not loaded.");
        Assert.assertEquals(searchResultsPage.getSearchResultsCount(), 10, "Search results count is wrong.");
        List<String> searchResultList = searchResultsPage.getSearchResults();

        for (String searchResult : searchResultList) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()), "SearchTerm" + searchTerm + " not found in: \n" + searchResult);
        }
    }

}
