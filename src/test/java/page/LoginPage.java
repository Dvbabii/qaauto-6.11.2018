package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * PageObject class for LoginPage.
 */
public class LoginPage extends BasePage{

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;
    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Constructor of LoginPage class.
     * @param webDriver - webdriver instance from Test.
     */
    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    /**
     * Method to login with specific credentials and check what page returned
     * @param userEmail - String with userEmail.
     * @param userPass - String with userPassword
     * @param <T> - Generic type of returned pageObject
     * @return One of listed pageObjects
     */
    public <T> T login(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(webDriver.getCurrentUrl().contains("/feed")){
            return (T) new HomePage(webDriver);
        }
        if(webDriver.getCurrentUrl().contains("/login-submit")){
            return (T) new LoginSubmitPage(webDriver);
        }
        else {
            return (T) new LoginPage(webDriver);
        }
    }

    /**
     * Method that click on 'Forgot password' link.
     * @return RequestPasswordResetPage object.
     */
    public RequestPasswordResetPage clickOnForgotPasswordLink() {
//        try {
//            sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        forgotPasswordLink.click();
        return new RequestPasswordResetPage(webDriver);
    }

    /**
     * Method to confirm load of page.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return signInButton.isDisplayed()
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && webDriver.getCurrentUrl().equals("https://www.linkedin.com/");
    }
}
