import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class LoginTest {
    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();

    }

    @Test
    public void negativeLoginTest() {
        //создаются в памяти все переменные, потом выполняется код из конструктора LoginPage
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("a@b.c", "");

        //Verify that page title is "LinkedIn: Войти или зарегистрироваться"
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться", "Login page title is wrong");
        //Assert.assertEquals(signInButton.isDisplayed(), true);
        Assert.assertTrue(loginPage.signInButton.isDisplayed(), "SignIn button is not displayed");
    }

    @Test
    public void successfulLoginTest() {
        //создаются в памяти все переменные, потом выполняется код из конструктора LoginPage
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("truekvazar@gmail.com", "dimon007");

        WebElement welcomeMessage = webDriver.findElement(By.xpath("//a[@data-control-name='identity_welcome_message']"));
        Assert.assertTrue(webDriver.getTitle().contains("LinkedIn"), "Home page title is wrong");
        Assert.assertTrue(welcomeMessage.isDisplayed(), "Welcome message is not displayed");

    }
}
