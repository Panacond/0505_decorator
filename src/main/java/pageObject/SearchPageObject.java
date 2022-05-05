package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.SearchPage;


import java.time.Duration;
import java.util.List;

public class SearchPageObject extends BasePageOblect {

    final private String SEARCH_TITLE = "input[class^='sidebar-search']";

    final private String LIST_CHECK_BOX_TITLE = "a.checkbox-filter__link";

    final private String SELECT_POP_UP = "select[class^='select']";


    final private String SELECT_EXPENSIVE = "option[value='2: expensive']";

    final private String LIST_ADD_BUCKET = "button[class^='buy-button']";

    final private String GO_TO_BUCKET = "button[class^='header__button ng-star-inserted header']";

    final private String ALL_ELEMENT_CATALOG = "ul[class^='catalog-grid']";

    final static Logger logger = Logger.getLogger(SearchPage.class);

    public SearchPageObject(WebDriver driver){super(driver);}

    public void searchByTitle(final String keyWord){
        driver.findElement(By.cssSelector(SEARCH_TITLE)).sendKeys(keyWord + Keys.ENTER);
    }

    public void clickCheckBoxMsi(String brand){
        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(LIST_CHECK_BOX_TITLE)));
        List<WebElement> listWebElements =   driver.findElements(By.cssSelector(LIST_CHECK_BOX_TITLE));
        for (WebElement i: listWebElements ) {
            String attributeText =  i.getAttribute("data-id");
            if (attributeText.equals(brand)) {
                i.click();
                break;
            }
        }
        logger.info("Not search item");
    }


    public void clickPopUp(){
        WebElement selectPopUp = new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECT_POP_UP)));
        selectPopUp.click();
    }


    public void clickPopUpExpensive(){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECT_EXPENSIVE)));
        driver.findElement(By.cssSelector(SELECT_EXPENSIVE)).click();
    }

    public List<WebElement> getListAddToBucket(){
        new WebDriverWait(driver, Duration.ofSeconds(40)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(LIST_ADD_BUCKET)));
        return driver.findElements(By.cssSelector(LIST_ADD_BUCKET));
    }


    public void waitAllCatalog(){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(ALL_ELEMENT_CATALOG)));
    }

    public void clickGoToBucket(){
        new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(GO_TO_BUCKET)));
        driver.findElement(By.cssSelector(GO_TO_BUCKET)).click();
    }
}
