package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class RequestPasswordResetPage extends BasePage{
    private WebDriver webDriver;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userEmailField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    /**
     * Constructor of RequestPasswordResetPage class.
     * @param webDriver - webdriver instance from Test.
     */
    public RequestPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method to confirm load of page.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return findAccountButton.isDisplayed()
        && webDriver.getTitle().equals("Изменить пароль | LinkedIn")
        && webDriver.getCurrentUrl().contains("uas/request-password-reset");
    }

    /**
     * Initiation of GMailService and sending an email.
     * @param userEmail - String with userEmail.
     * @return PasswordResetSubmitPage object.
     */
    public PasswordResetSubmitPage findAccount(String userEmail) {
        gMailService = new GMailService();
        gMailService.connect();

        userEmailField.sendKeys(userEmail);
        findAccountButton.click();
        return new PasswordResetSubmitPage(webDriver);
    }
}
