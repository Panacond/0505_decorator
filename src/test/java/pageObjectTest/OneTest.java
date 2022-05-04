package pageObjectTest;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.BucketPageObject;
import pageObject.SearchPageObject;
import pageObject.StartPageObject;
import pageObject.decorElement.ChangeClick;
import support.PropertiesReader;
import support.TestData;
import support.XmlTestData;

import java.util.List;

public class OneTest extends BaseTestObject {
    PropertiesReader properties = new PropertiesReader();
    TestData testData = XmlTestData.ReadXml(properties.getInitialData());

    @Test(description = "run one test from file testData.xml")
    public void checkExpensiveGoods() {
        StartPageObject startPage = new StartPageObject(super.driver);
        startPage.searchByKeyword(testData.getProduct());
        SearchPageObject searchPage = new SearchPageObject(super.driver);
        searchPage.clickCheckBoxMsi(testData.getBrand());
        searchPage.clickPopUp();
        searchPage.clickPopUpExpensive();
        searchPage.waitAllCatalog();
        List<ChangeClick> listAddToBucket = searchPage.getNewListAddToBucket();
        listAddToBucket.get(0).newClick();
        searchPage.implicitWait(5);
        searchPage.clickGoToBucket();
        BucketPageObject bucketPage = new BucketPageObject(super.driver);
        Integer price = bucketPage.getStringPrice();
        testData.setRealPrice(price);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(price > testData.getMinPrice(), "Price in page low data price");
        softAssert.assertAll();
    }
}
