package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetSubmitPage extends BasePage{

    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    /**
     * Constructor of PasswordResetSubmitPage class.
     * @param webDriver - webdriver instance from Test.
     */
    public PasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method to confirm load of page.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return resendLinkButton.isDisplayed()
        //&& webDriver.getTitle().contains("Проверьте, получили ли вы сообщение эл. почты со ссылкой для изменения пароля. | LinkedIn")
        && webDriver.getCurrentUrl().contains("request-password-reset-submit");
    }

    /**
     * Login to GMailService, receive text of email with resetPasswordLink, get link and navigate to it.
     * @return SetNewPasswordPage object.
     */
    public SetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "Dmytro, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "link.test.dvb@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        String resetPasswordLink = StringUtils.substringBetween(message, "line-height:1.25;\"><a href=\"", "\"").replace("amp;", "");
        System.out.println(resetPasswordLink);
        webDriver.get(resetPasswordLink);

        return new SetNewPasswordPage(webDriver);
    }
}
