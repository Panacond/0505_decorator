package pageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage extends BasePage{

    @FindBy(css = "input[class^='search']")
    private WebElement searchInput;

    public StartPage(WebDriver driver){super(driver);}

    public void searchByKeyword(final String keyWord){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(searchInput));
        searchInput.sendKeys(keyWord + Keys.ENTER);
    }
}
