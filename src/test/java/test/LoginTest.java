package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;


public class LoginTest extends BaseTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "link.test.dvb@gmail.com", "Linktest03" },
                { "link.test.dvb@GMAIL.COM", "Linktest03" },
                { " link.test.dvb@gmail.com ", "Linktest03" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPass) {
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
        loginPage.login(userEmail, userPass);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @DataProvider
    public Object[][] negativeLeadsToLoginSubmitPageDataProvider() {
        return new Object[][]{
                { "truekvazar@gmail.com", "linktestdvb", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "" },
                { "truekvazar@gmail..com", "linktestdvb01", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "" },
                { "truekvazar", "linktestdvb01", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "" },
                { "truekvazar@gmail.com", "linktestdvb", "", "Это неверный пароль. Повторите попытку или измените пароль." },
                { "truekvazar@gmail.com", "Linktestdvb01", "", "Это неверный пароль. Повторите попытку или измените пароль." },
                { "truekvazar", "linktestdvb01", "Укажите действительный адрес эл. почты.", "" },
                { "admin", "admin", "Укажите действительный адрес эл. почты.", "" }
        };
    }

    @Test(dataProvider = "negativeLeadsToLoginSubmitPageDataProvider")
    public void negativeLeadsToLoginSubmitPage(String userEmail, String userPass, String emailInvalidMessage, String passInvalidMessage) {
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPass);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded.");
        Assert.assertEquals(loginSubmitPage.getUserEmailError(), emailInvalidMessage, "userEmail validation message is wrong");
        Assert.assertEquals(loginSubmitPage.getUserPassError(), passInvalidMessage, "userPass validation message appears");
    }
}
