import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver webDriver;
    WebElement homeButton;

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements(){
        //опустили инициализацию из класса в метод переменных после присваивания переменной webDriver в конструкторе класса
        homeButton = webDriver.findElement(By.xpath("//*[@id='feed-nav-item']/a"));
    }

    public boolean isPageLoaded() {
        return homeButton.isDisplayed()
                && webDriver.getTitle().equals("LinkedIn")
                && webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/");
    }
}
