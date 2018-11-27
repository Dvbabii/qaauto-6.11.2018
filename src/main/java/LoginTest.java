import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("a@b.c", "");

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "truekvazar@gmail.com", "dimon007" },
                { "truekvazar@GMAIL.COM", "dimon007" },
                { " truekvazar@gmail.com ", "dimon007" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPass) {
        LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = loginPage.login(userEmail, userPass);

        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");
    }

    @Test
    public void negativeLeadsToLoginSubmitPage(){
        LoginPage loginPage = new LoginPage(webDriver);
        LoginSubmitPage loginSubmitPage = loginPage.login("truekvazar@@gmail.com", "dimon007");

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded.");
        Assert.assertEquals(loginSubmitPage.getUserEmailError(), "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "userEmail validation message is wrong");
        Assert.assertEquals(loginSubmitPage.getUserPassError(), "", "userPass validation message appears");
    }

    @Test
    public void wrongEmailLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        LoginSubmitPage loginSubmitPage = loginPage.login("truekvazar", "dimon007");

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded.");
        Assert.assertEquals(loginSubmitPage.getUserEmailError(), "Укажите действительный адрес эл. почты.", "userEmail validation message is wrong");
        Assert.assertEquals(loginSubmitPage.getUserPassError(), "", "userPass validation message appears");
    }

    @Test
    public void blankEmailLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("", "pass");

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @Test
    public void blankAllLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @Test
    public void wrongPassLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        LoginSubmitPage loginSubmitPage = loginPage.login("truekvazar@gmail.com", "dimon");

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded.");
        Assert.assertEquals(loginSubmitPage.getUserEmailError(), "", "userEmail validation message is wrong");
        Assert.assertEquals(loginSubmitPage.getUserPassError(), "Это неверный пароль. Повторите попытку или измените пароль.", "userPass validation message appears");
    }

}
