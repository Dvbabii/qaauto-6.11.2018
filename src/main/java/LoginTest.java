import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


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
        HomePage homePage = loginPage.loginToHome("truekvazar@gmail.com", "dimon007");

        //HomePage1 = екземпляр какого класса, HomePage2 = екземпляр
        //HomePage homePage = new HomePage(webDriver);
        //Assert.assertTrue(webDriver.getTitle().contains("LinkedIn"), "Home page title is wrong");
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");
    }
    @Test
    public void wrongEmailLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginToLoginSubmit("truekvazar", "dimon007");

        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded.");
    }

    @Test
    public void negativeLeadsToLoginSubmitPage(){
        LoginPage loginPage = new LoginPage(webDriver);

        //LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver); =↓
        LoginSubmitPage loginSubmitPage = loginPage.loginToLoginSubmit("truekvazar@@gmail.com", "dimon007");

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded.");
        Assert.assertEquals(loginSubmitPage.getUserEmailError(), "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "userEmail validation message is wrong");
        Assert.assertEquals(loginSubmitPage.getUserPassError(), "", "userPass validation message appears");
    }

//    @Test
//    public void blankEmailLoginTest() {
//        LoginPage loginPage = new LoginPage(webDriver);
//        loginPage.login("", "pass");
//
//        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);
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



}
