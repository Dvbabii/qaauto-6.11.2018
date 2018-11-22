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

        //Assert.assertEquals(Assert.assertEquals(signInButton.isDisplayed(), true);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @Test
    public void successfulLoginTest() {
        //создаются в памяти все переменные, потом выполняется код из конструктора LoginPage
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("truekvazar@gmail.com", "dimon007");

        //HomePage1 = екземпляр какого класса, HomePage2 = екземпляр
        HomePage homePage = new HomePage(webDriver);


        //Assert.assertTrue(webDriver.getTitle().contains("LinkedIn"), "Home page title is wrong");
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");

    }
    @Test
    public void wrongEmailLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("truekvazar", "dimon007");

        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded.");
    }
//    @Test
//    public void blankEmailLoginTest() {
//        LoginPage loginPage = new LoginPage(webDriver);
//        loginPage.login("", "pass");
//
//        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться", "Login page title is wrong");
//    }
//
//    @Test
//    public void blankPassLoginTest() {
//        LoginPage loginPage = new LoginPage(webDriver);
//        loginPage.login("truekvazar@gmail.com", "");
//
//        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться", "Login page title is wrong");
//    }
//
//    @Test
//    public void blankAllLoginTest() {
//        LoginPage loginPage = new LoginPage(webDriver);
//        loginPage.login("", "");
//
//        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться", "Login page title is wrong");
//    }
//

//
//    @Test
//    public void wrongPassLoginTest() {
//        LoginPage loginPage = new LoginPage(webDriver);
//        loginPage.login("truekvazar@gmail.com", "dimon");
//
//        Assert.assertEquals(webDriver.getTitle(), "Войти в LinkedIn", "Login page title is wrong");
//    }
//
//    @Test
//    public void wrongAllLoginTest() {
//        LoginPage loginPage = new LoginPage(webDriver);
//        loginPage.login("xxx", "xxx");
//
//        Assert.assertEquals(webDriver.getTitle(), "Войти в LinkedIn", "Login page title is wrong");
//    }
//
//    @Test
//    public void wrongSymbolsLoginTest() {
//        LoginPage loginPage = new LoginPage(webDriver);
//        loginPage.login("«»‘~!@#$%^&*()?>,./<][ /*<!—", "«${code}»;—>");
//
//        Assert.assertEquals(webDriver.getTitle(), "Войти в LinkedIn", "Login page title is wrong");
//    }
//

    @Test
    public void negativeLeadsToLoginSubmitPage(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("truekvazar@@gmail.com", "dimon007");

        WebElement loginForm = webDriver.findElement(By.xpath("//form[@class='login__form']"));
        Assert.assertTrue(loginForm.isDisplayed(), "Login Submit page is not loaded");

        WebElement userEmailError = webDriver.findElement(By.xpath("//div[@id='error-for-username']"));
        Assert.assertEquals(userEmailError.getText(), "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "userEmail validation message is wrong");

        WebElement userPassError = webDriver.findElement(By.xpath("//div[@id='error-for-password']"));
        Assert.assertEquals(userPassError.getText(), "", "userPass validation message appears");
    }

}
