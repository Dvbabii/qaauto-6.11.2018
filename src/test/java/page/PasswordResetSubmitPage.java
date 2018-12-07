package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import static java.lang.Thread.sleep;

public class PasswordResetSubmitPage extends BasePage{

    private WebDriver webDriver;

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public PasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return resendLinkButton.isDisplayed()
        //&& webDriver.getTitle().contains("Проверьте, получили ли вы сообщение эл. почты со ссылкой для изменения пароля. | LinkedIn")
        && webDriver.getCurrentUrl().contains("request-password-reset-submit");
    }

    public SetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "Dmytro, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "link.test.dvb@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        return new SetNewPasswordPage(webDriver);
    }
}
