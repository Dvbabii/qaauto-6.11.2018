package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage extends BasePage{

    @FindBy(xpath = "//*[@id='app__container']/div[1]/div/form/div[3]/button")
    private WebElement signInSubmitButton;
    @FindBy(xpath = "//form[@class='login__form']")
    private WebElement loginForm;
    @FindBy(xpath = "//div[@id='error-for-username']")
    private WebElement userEmailError;
    @FindBy(xpath = "//div[@id='error-for-password']")
    private WebElement userPassError;

    /**
     * Constructor of LoginSubmitPage class.
     * @param webDriver - webdriver instance from Test.
     */
    public LoginSubmitPage(WebDriver webDriver){
        //приравниваю webDriver из этого класса к webDriver из класса test.LoginTest в его теперешнем состоянии
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    /**
     * Method to confirm load of page.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return signInSubmitButton.isDisplayed()
                && loginForm.isDisplayed()
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && webDriver.getCurrentUrl().contains("/login-submit");
    }

    /**
     * Getting text of email validation error.
     * @return text of error.
     */
    public String getUserEmailError(){
        return userEmailError.getText();
    }

    /**
     * Getting text of password validation error.
     * @return text of error.
     */
    public String getUserPassError(){
        return userPassError.getText();
    }
}
