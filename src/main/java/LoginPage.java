import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    //конструктор класса
    public LoginPage(WebDriver webDriver){
        //приравниваю webDriver из этого класса к webDriver из класса LoginTest в его теперешнем состоянии
        this.webDriver = webDriver;
        //паттерн PageFactory
        PageFactory.initElements(webDriver,this);
    }

    public LoginSubmitPage loginToLoginSubmit(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
        //создали обьект LoginSubmitPage и вернули его в "public LoginSubmitPage login"
        return new LoginSubmitPage(webDriver);
    }

    public HomePage loginToHome(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
        //создали обьект LoginSubmitPage и вернули его в "public LoginSubmitPage login"
        return new HomePage(webDriver);
    }

    public LoginPage login(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
        //создали обьект LoginSubmitPage и вернули его в "public LoginSubmitPage login"
        return new LoginPage(webDriver);
    }

    public boolean isPageLoaded() {
        return signInButton.isDisplayed()
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && webDriver.getCurrentUrl().equals("https://www.linkedin.com/");
    }
}
