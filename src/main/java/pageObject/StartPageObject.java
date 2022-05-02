package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class StartPageObject extends BasePageOblect {

    final private String  SEARCH_INPUT = "input[class^='search']";

    public StartPageObject(WebDriver driver){super(driver);}

    public void searchByKeyword(final String keyWord){
        driver.findElement(By.cssSelector(SEARCH_INPUT)).sendKeys(keyWord + Keys.ENTER);
    }
}
