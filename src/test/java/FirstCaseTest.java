import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageFactory.BucketPage;
import pageFactory.BusinessLogic;
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
        testFlow(testData.getProduct(), testData.getBrand(), testData.getMinPrice());
    }

    @Test (description = "input hard coding data")
    public void checkExpensiveGood() {
        String[] data = {"Ноутбук", "MSI" };
        int priceAssert = 5000;
//        testInputData(data, priceAssert);
        testFlow(data[0], data[1], priceAssert);
    }

    private void testFlow(String product, String brand, Integer minPrice) {
        StartPage startPage = getStartPage();
        startPage.searchByKeyword(product);
        TestData testData = new TestData(product, brand, minPrice);
        SearchPage searchPage = getSearchPage();
        new BusinessLogic().addToBucketExpensiveElementNoThreadSleep(searchPage, brand);
        BucketPage bucketPage = getBucketPage();
        new BusinessLogic().CheckProduct(bucketPage, testData);
        logger.info("Test work!");
    }

    @Test (description = "test is ignored because test work data", enabled = false)
    public void checkExpensiveGood2() {
        String[] data = {"стиральная машина", "Samsung" };
        int priceAssert = 14000;
        testFlow(data[0], data[1], priceAssert);
    }
}
