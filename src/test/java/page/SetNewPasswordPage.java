package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetNewPasswordPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;
    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    /**
     * Constructor of SetNewPasswordPage class.
     * @param webDriver - webdriver instance from Test.
     */
    public SetNewPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Entering new password two times.
     * @param newUserPassword - String with new user password.
     * @return SuccessfulPasswordResetPage object.
     */
    public SuccessfulPasswordResetPage submitNewPassword(String newUserPassword) {
        newPasswordField.sendKeys(newUserPassword);
        confirmPasswordField.sendKeys(newUserPassword);
        submitButton.click();
        return new SuccessfulPasswordResetPage(webDriver);
    }

    /**
     * Method to confirm load of page.
     * @return true/false
     */
    public boolean isLoaded() {
        return newPasswordField.isDisplayed();
    }


}
