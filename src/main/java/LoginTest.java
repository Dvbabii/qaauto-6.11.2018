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

    @DataProvider
    public Object[][] negativeDataProvider() {
        return new Object[][]{
                { "", "" },
                { "", "dimon007" },
                { "truekvazar@gmail.com", "" }
        };
    }

    @Test(dataProvider = "negativeDataProvider")
    public void negativeLoginTest(String userEmail, String userPass) {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(userEmail, userPass);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @DataProvider
    public Object[][] negativeLeadsToLoginSubmitPageDataProvider() {
        return new Object[][]{
                { "truekvazar@gmail.com", "dimon", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "" },
                { "truekvazar@gmail..com", "dimon007", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "" },
                { "truekvazar", "dimon007", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "" },
                { "truekvazar@gmail.com", "dimon", "", "Это неверный пароль. Повторите попытку или измените пароль." },
                { "truekvazar@gmail.com", "Dimon007", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "" },
                { "truekvazar", "dimon007", "Укажите действительный адрес эл. почты.", "" },
                { "admin", "admin", "Укажите действительный адрес эл. почты.", "" }
        };
    }

    @Test(dataProvider = "negativeLeadsToLoginSubmitPageDataProvider")
    public void negativeLeadsToLoginSubmitPage(String userEmail, String userPass, String emailInvalidMessage, String passInvalidMessage) {
        LoginPage loginPage = new LoginPage(webDriver);
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPass);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded.");
        Assert.assertEquals(loginSubmitPage.getUserEmailError(), emailInvalidMessage, "userEmail validation message is wrong");
        Assert.assertEquals(loginSubmitPage.getUserPassError(), passInvalidMessage, "userPass validation message appears");
    }
}
