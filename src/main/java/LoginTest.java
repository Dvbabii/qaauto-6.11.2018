import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginTest {

    @Test
    public void negativeLoginTest() {

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com/");

        WebElement emailField = webDriver.findElements(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElements(By.xpath("//*[@id='login-password']"));
        WebElement signInButton = webDriver.findElements(By.xpath("//*[@id='login-submit']"));

        emailField.sendKeys("a@b.c");
        passwordField.sendKeys("");
        signInButton.click();

        //Verify that page title is "LinkedIn: Войти или зарегистрироваться"
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться");

        //*[@id="login-email"]
    }

    public void positiveLoginTest() {

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com/");

        WebElement emailField = webDriver.findElements(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElements(By.xpath("//*[@id='login-password']"));
        WebElement signInButton = webDriver.findElements(By.xpath("//*[@id='login-submit']"));
        WebElement nameSurname = webDriver.findElements(By.xpath("//*[@id='ember216']/span)"));

        emailField.sendKeys("truekvazar@gmail.com");
        passwordField.sendKeys("dimon007");
        signInButton.click();

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn");

        //*[@id="login-email"]
    }
}
