import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    WebDriver webDriver;

    //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    WebElement emailField;
    WebElement passwordField;
    WebElement signInButton;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        initElements();
    }

    public void initElements(){
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
}
