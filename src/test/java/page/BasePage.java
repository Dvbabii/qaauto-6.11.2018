package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

public abstract class BasePage {
    protected static GMailService gMailService;
    protected WebDriver webDriver;

    protected void waitUntilElementIsVisible(WebElement elementToWait){
        waitUntilElementIsVisible(elementToWait, 5);
    }

    protected void waitUntilElementIsVisible(WebElement elementToWait, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(elementToWait));
    }

    /**
     * Method to confirm load of page.
     * @return true/false
     */
   abstract public boolean isPageLoaded();
}
