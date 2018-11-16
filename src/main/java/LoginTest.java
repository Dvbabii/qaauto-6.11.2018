import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class LoginTest {

   // @Test
//    public void negativeLoginTest() {
//
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.get("https://www.linkedin.com/");
//
//        WebElement emailField = webDriver.findElements(By.xpath("//*[@id='login-email']"));
//        WebElement passwordField = webDriver.findElements(By.xpath("//*[@id='login-password']"));
//        WebElement signInButton = webDriver.findElements(By.xpath("//*[@id='login-submit']"));
//
//        emailField.sendKeys("a@b.c");
//        passwordField.sendKeys("");
//        signInButton.click();
//
//        //Verify that page title is "LinkedIn: Войти или зарегистрироваться"
//        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться");
//
//        //*[@id="login-email"]
//    }

    @Test
    public void positiveLoginTest() {

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com/");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));
//      WebElement nameSurname = webDriver.findElement(By.xpath("//*[@id='ember216']/span)"));

        emailField.sendKeys("truekvazar@gmail.com");
        passwordField.sendKeys("dimon007");
        signInButton.click();
//      System.out.println(nameSurname.getText());
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn");
//      Assert.assertTrue(nameSurname.isDisplayed());

        //*[@id="login-email"]
    }
}
