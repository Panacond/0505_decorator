package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.BucketPage;

import java.time.Duration;

public class BucketPageObject extends BasePageOblect{
    final static Logger logger = Logger.getLogger(BucketPage.class);

    private final String GET_PRICE = "div[class$='sum-price']>span";

    public BucketPageObject(WebDriver driver){super(driver);}

    public Integer getStringPrice() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector(GET_PRICE)));
            WebElement getPrice = driver.findElement(By.cssSelector(GET_PRICE));
            return Integer.parseInt(getPrice.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            logger.info("ERROR: Not get price element!");
            return 0;
        }
    }
}
