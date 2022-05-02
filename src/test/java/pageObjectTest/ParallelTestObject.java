package pageObjectTest;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.BucketPageObject;
import pageObject.SearchPageObject;
import pageObject.StartPageObject;
import support.ListTestData;
import support.PropertiesReader;
import support.TestData;

import java.util.List;

import static support.XmlTestData.ReadXml;
import static support.XmlTestData.WriteXml;

public class ParallelTestObject extends BaseTestObject{

    PropertiesReader properties = new PropertiesReader();

    private static Object[][] selectItems(int start, int finish) {
        PropertiesReader properties = new PropertiesReader();
        String path = properties.getInitialListData();
        ListTestData listTestData = new ListTestData(ReadXml(path));
        int row = listTestData.getListTestData().size();
        if (row < finish)
            finish = row;
        Object[][] data = new Object[finish - start][3];
        int number = 0;
        for (int i = 0; i < row; i++) {
            if (i >= start && finish > i) {
                TestData item = listTestData.getListTestData().get(i);
                data[number][0] = item.getProduct();
                data[number][1] = item.getBrand();
                data[number][2] = item.getMinPrice();
                number = number +1;
            }
        }
        return data;
    }

    @DataProvider()
    public Object[][] getDataRead1() {
        return selectItems(0,1);
    }

    @Test(dataProvider = "getDataRead1", description = "run successively test")
    public void checkFlowData1(String product, String brand, Integer minPrice) throws InterruptedException {
        testFlow(product, brand, minPrice);
    }

    @DataProvider()
    public Object[][] getDataRead2() {
        return selectItems(1,2);
    }

    @Test(dataProvider = "getDataRead2", description = "run successively test")
    public void checkFlowData2(String product, String brand, Integer minPrice) throws InterruptedException {
        testFlow(product, brand, minPrice);
    }

    @DataProvider()
    public Object[][] getDataRead3() {
        return selectItems(2,6);
    }

    @Test(dataProvider = "getDataRead3", description = "run successively test")
    public void checkFlowData3(String product, String brand, Integer minPrice)  {
        testFlow(product, brand, minPrice);
    }

    private void testFlow(String product, String brand, Integer minPrice) {
        StartPageObject startPage = new StartPageObject(super.driver);
        startPage.searchByKeyword(product);
        TestData testData = new TestData(product, brand, minPrice);
        SearchPageObject searchPage = new SearchPageObject(super.driver);
        searchPage.clickCheckBoxMsi(brand);
        searchPage.clickPopUp();
        searchPage.clickPopUpExpensive();
        searchPage.waitAllCatalog();
        List<WebElement> listAddToBucket =  searchPage.getListAddToBucket();
        WebElement element = listAddToBucket.get(0);
        element.click();
        searchPage.clickGoToBucket();
        BucketPageObject bucketPage = new BucketPageObject(super.driver);
        Integer price = bucketPage.getStringPrice();
        testData.setRealPrice(price);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(price > testData.getMinPrice(), "Price in page low data price");
        softAssert.assertAll();
        WriteXml(testData, properties.getResultListData());
    }
}
