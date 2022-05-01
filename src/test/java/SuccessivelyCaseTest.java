import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageFactory.BucketPage;
import pageFactory.SearchPage;
import pageFactory.StartPage;
import support.ListTestData;
import support.PropertiesReader;
import support.TestData;

import java.util.List;

import static support.XmlTestData.ReadXml;
import static support.XmlTestData.WriteXml;

public class SuccessivelyCaseTest extends BaseTest{

    PropertiesReader properties = new PropertiesReader();

    @DataProvider
    public Object[][] getDataRead(){
        PropertiesReader properties = new PropertiesReader();
        String path = properties.getInitialListData();
        ListTestData listTestData =  new ListTestData(ReadXml(path));
        int row = listTestData.getListTestData().size();
        Object[][] data = new Object[row][3];
        for (int i = 0; i < row; i++) {
            TestData item = listTestData.getListTestData().get(i);
            data[i][0] = item.getProduct();
            data[i][1] = item.getBrand();
            data[i][2] = item.getMinPrice();
        }
        return data;
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][] {
                {"ноутбук", "MSI", 5000}
                ,{"стиральная машина", "Samsung", 14000}
                ,{"посудомоечная машина", "Bosch", 50000}
        };
    }

    @Test(dataProvider = "getDataRead", description = "run successively test")
    public void checkFlowData(String product, String brand, Integer minPrice) {
        testFlow(product, brand, minPrice);
    }

    private void testFlow(String product, String brand, Integer minPrice) {
        StartPage startPage = getStartPage();
        startPage.searchByKeyword(product);
        TestData testData = new TestData(product, brand, minPrice);
        SearchPage searchPage = getSearchPage();
        searchPage.clickCheckBoxMsi(brand);
        searchPage.waitForPageLoadComplete(10^8);
        searchPage.clickPopUp();
        searchPage.clickPopUpExpensive();
        List<WebElement> listAddToBucket =  searchPage.getListAddToBucket();
        searchPage.waitVisibilityOfElement(10^6, searchPage.getAllCatalog());
        searchPage.waitForPageLoadComplete(10^8);
        WebElement element = listAddToBucket.get(0);
        element.click();
        searchPage.implicitWait(5);
        searchPage.clickGoToBucket();
        BucketPage bucketPage = getBucketPage();
        Integer price = bucketPage.getStringPrice();
        testData.setRealPrice(price);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(price > testData.getMinPrice(), "Price in page low data price");
        softAssert.assertAll();
        WriteXml(testData, properties.getResultListData());
    }

}
