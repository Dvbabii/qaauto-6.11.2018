import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {

    public static void main(String[] args) {

        System.out.println("Hello world");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.google.ru/");
        WebElement element = webDriver.findElement(By.name("q"));
        element.sendKeys("Selenium");
        element.sendKeys(Keys.ENTER);

        List<WebElement> resultList = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));

        resultList.size();
        System.out.println(resultList.size());

        int res = resultList.size();
        int res1 = 0;
        String searchTerm = " Selenium ";

        while (res > 0){
            String myText = resultList.get(res1).getText();
            System.out.println(myText);
            if (myText.contains(searchTerm)){
                System.out.println("//searchTerm found//");
            } else {
                System.out.println("//searchTerm not found//");
            }
            res--;
            res1++;

        }

    }










}
