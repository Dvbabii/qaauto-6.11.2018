package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

public class ResetPasswordTest extends BaseTest {

    @Test
    public void successfulResetPasswordTest() throws InterruptedException {
        String newPassword = "Test01!";
        String userEmail = "link.test.dvb@gmail.com";

        Assert.assertTrue(loginPage.isPageLoaded(),
                "page.LoginPage is not loaded.");

        RequestPasswordResetPage requestPasswordResetPage = loginPage.clickOnForgotPasswordLink();
        sleep(3000);
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "page.RequestPasswordResetPage is not loaded.");

        PasswordResetSubmitPage passwordResetSubmitPage = requestPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(passwordResetSubmitPage.isPageLoaded(), "page.PasswordResetSubmitPage is not loaded.");

        SetNewPasswordPage setNewPasswordPage = passwordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(setNewPasswordPage.isLoaded(), "page.SetNewPasswordPage is not loaded.");

//        SuccessfulPasswordResetPage successfulPasswordResetPage = setNewPasswordPage.submitNewPassword(newPassword);
//        sleep(3000);
//        Assert.assertTrue(successfulPasswordResetPage.isLoaded(), "page.SuccessfulPasswordResetPage is not loaded.");
//
//        HomePage homePage = successfulPasswordResetPage.clickOnGoToHomeButton();
//        Assert.assertTrue(homePage.isPageLoaded(), "page.HomePage is not loaded.");
    }
}
