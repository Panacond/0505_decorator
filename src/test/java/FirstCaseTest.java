import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageFactory.BucketPage;
import pageFactory.SearchPage;
import pageFactory.StartPage;
import support.PropertiesReader;
import support.TestData;
import support.XmlTestData;

import java.util.List;

import static support.XmlTestData.WriteXml;

@Listeners(ConvertTestListener.class)
public class FirstCaseTest extends BaseTest {

    final static Logger logger = Logger.getLogger(FirstCaseTest.class);
    PropertiesReader properties = new PropertiesReader();
    TestData testData = XmlTestData.ReadXml(properties.getInitialData());

    @Test (description = "run one test from file testData.xml")
    public void checkExpensiveGoods() {
        StartPage startPage = getStartPage();
        startPage.searchByKeyword(testData.getProduct());
        SearchPage searchPage = getSearchPage();
        searchPage.clickCheckBoxMsi(testData.getBrand());
        searchPage.waitForPageLoadComplete(10000000);
        searchPage.clickPopUp();
        searchPage.clickPopUpExpensive();
        List<WebElement> listAddToBucket =  searchPage.getListAddToBucket();
        searchPage.waitVisibilityOfElement(100000, searchPage.getAllCatalog());
        searchPage.waitForPageLoadComplete(1000000);
        listAddToBucket.get(0).click();
        searchPage.implicitWait(5);
        searchPage.clickGoToBucket();
        BucketPage bucketPage = getBucketPage();
        Integer price = bucketPage.getStringPrice();
        testData.setRealPrice(price);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(price > testData.getMinPrice(), "Price in page low data price");
        softAssert.assertAll();
        WriteXml(testData, properties.getResultData());
        logger.info("Test work!");
    }

    @Test (description = "input hard coding data")
    public void checkExpensiveGood() {
        String[] data = {"Ноутбук", "MSI" };
        int priceAssert = 5000;
        testInsentData(data, priceAssert);
    }

    private void testInsentData(String[] data, int priceAssert) {
        StartPage startPage = getStartPage();
        startPage.searchByKeyword(data[0]);
        SearchPage searchPage = getSearchPage();
        searchPage.clickCheckBoxMsi(data[1]);
        searchPage.waitForPageLoadComplete(10000000);
        searchPage.clickPopUp();
        searchPage.clickPopUpExpensive();
        List<WebElement> listAddToBucket =  getSearchPage().getListAddToBucket();
        searchPage.waitVisibilityOfElement(100000, searchPage.getAllCatalog());
        searchPage.waitForPageLoadComplete(1000000);
        listAddToBucket.get(0).click();
        searchPage.implicitWait(5);
        searchPage.clickGoToBucket();
        BucketPage bucketPage = getBucketPage();
        Integer price = bucketPage.getStringPrice();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(price > priceAssert, "Price in page low data price");
        softAssert.assertAll();
        logger.info("Test work!");
    }

    @Test (description = "test is ignored because test work data", enabled = false)
    public void checkExpensiveGood2() {
        String[] data = {"стиральная машина", "Samsung" };
        int priceAssert = 14000;
        testInsentData(data, priceAssert);
    }
}
