package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(css = "input[class^='sidebar-search']")
    private WebElement searchTitle;

    @FindBy(css = "a.checkbox-filter__link")
    private List<WebElement> listCheckBoxTitle;

    @FindBy(css = "select[class^='select']")
    private WebElement selectPopUp;

    @FindBy(css = "option[value='2: expensive']")
    private WebElement selectExpensive;

    @FindBy(css = "div[class^='goods-tile ng-star']")
    private List<WebElement> listGoods;

    @FindBy(css = "button[class^='buy-button']")
    private List<WebElement> listAddBucket;

    @FindBy(css = "button[class^='buy-button']")
    private WebElement oneAddBucket;

    @FindBy(css = "button[class^='header__button ng-star-inserted header']")
    private WebElement goToBucket;

    @FindBy(css = "ul[class^='catalog-grid']")
    private WebElement allElementCatalog;

    final static Logger logger = Logger.getLogger(SearchPage.class);

    public SearchPage(WebDriver driver){super(driver);}

    public void clickCheckBoxMsi(String brand){
        for (WebElement i: listCheckBoxTitle) {
            String attributeText =  i.getAttribute("data-id");
            if (attributeText.equals(brand)) {
                i.click();
                break;
            }
        }
        logger.info("Not search item");
    }

    public void clickPopUp(){
        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.elementToBeClickable(selectPopUp));
        selectPopUp.click();
    }

    public void clickPopUpExpensive(){
        selectExpensive.click();
    }

    public List<WebElement> getListAddToBucket(){
        new WebDriverWait(driver, Duration.ofSeconds(40)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(oneAddBucket));
        return listAddBucket;
    }

    public void clickGoToBucket(){
        new WebDriverWait(driver, Duration.ofSeconds(40)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(goToBucket));
        goToBucket.click();
    }
}
