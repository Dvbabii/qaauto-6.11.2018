import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmitPage {
    private WebDriver webDriver;
    private WebElement signInSubmitButton;


    public LoginSubmitPage(WebDriver webDriver){
        //приравниваю webDriver из этого класса к webDriver из класса LoginTest в его теперешнем состоянии
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements(){
        //опустили инициализацию из класса в метод переменных после присваивания переменной webDriver в конструкторе класса
        signInSubmitButton = webDriver.findElement(By.xpath("//*[@id='app__container']/div[1]/div/form/div[3]/button"));
    }

    public boolean isPageLoaded() {
        return signInSubmitButton.isDisplayed()
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && webDriver.getCurrentUrl().contains("login-submit");
    }
}
