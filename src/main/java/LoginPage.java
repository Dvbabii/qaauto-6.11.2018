import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver webDriver;
    //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;

    //конструктор класса
    public LoginPage(WebDriver webDriver){
        //приравниваю webDriver из этого класса к webDriver из класса LoginTest в его теперешнем состоянии
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements(){
        //опустили инициализацию из класса в метод переменных после присваивания переменной webDriver в конструкторе класса
        emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        signInButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));
    }

    public void login (String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
    }

    public boolean isPageLoaded() {
        return signInButton.isDisplayed()
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && webDriver.getCurrentUrl().equals("https://www.linkedin.com/");
    }
}
