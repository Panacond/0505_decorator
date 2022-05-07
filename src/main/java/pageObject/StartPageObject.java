package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPageObject extends BasePageOblect {

    final private String  SEARCH_INPUT = "input[class^='search']";

    public StartPageObject(WebDriver driver){super(driver);}

    public void searchByKeyword(final String keyWord){
        new WebDriverWait(driver, Duration.ofSeconds(40)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(SEARCH_INPUT)));
        driver.findElement(By.cssSelector(SEARCH_INPUT)).sendKeys(keyWord + Keys.ENTER);
    }
}
