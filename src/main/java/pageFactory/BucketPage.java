package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BucketPage extends BasePage{

    final static Logger logger = Logger.getLogger(BucketPage.class);

    @FindBy(css = "div[class$='sum-price']>span")
    private WebElement getPrice;

    public BucketPage(WebDriver driver){super(driver);}

    public Integer getStringPrice() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(40)).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(getPrice));
//            waitVisibilityOfElement(5, getPrice);
            return Integer.parseInt(getPrice.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            logger.info("ERROR: Not get price element!");
            return 0;
        }
    }
}
