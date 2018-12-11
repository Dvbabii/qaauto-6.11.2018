package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessfulPasswordResetPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;

    /**
     * Constructor of SuccessfulPasswordResetPage class.
     * @param webDriver - webdriver instance from Test.
     */
    public SuccessfulPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Clicking on home button.
     * @return HomePage object.
     */
    public HomePage clickOnGoToHomeButton() {
        goToHomepageButton.click();
        return new HomePage(webDriver);
    }

    /**
     * Method to confirm load of page.
     * @return true/false
     */
    public boolean isLoaded() {
        return goToHomepageButton.isDisplayed();
    }
}

