package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import support.TestData;

import java.util.List;

public class BusinessLogicObject {

    public void oneTestSearchProduct(WebDriver driver, String product){
        StartPageObject startPage = new StartPageObject(driver);
        startPage.searchByKeyword(product);
    }

    public  void addExpensiveProductToBucket(WebDriver driver, String brand){
        SearchPageObject searchPage = new SearchPageObject(driver);
        searchPage.clickCheckBoxMsi(brand);
        searchPage.clickPopUp();
        searchPage.clickPopUpExpensive();
        searchPage.waitAllCatalog();
        List<WebElement> listAddToBucket =  searchPage.getListAddToBucket();
        listAddToBucket.get(0).click();
        searchPage.implicitWait(5);
        searchPage.clickGoToBucket();
    }

    public void checkData(WebDriver driver, TestData testData){
        int minPrice = testData.getMinPrice();
        checkDataPrice(driver, minPrice);
    }

    public void checkDataPrice(WebDriver driver, int minPrice){
        BucketPageObject bucketPage = new BucketPageObject(driver);
        Integer price = bucketPage.getStringPrice();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(price > minPrice, "Price in page low data price");
        softAssert.assertAll();
    }

}
