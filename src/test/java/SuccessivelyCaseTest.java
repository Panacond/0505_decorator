import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageFactory.BucketPage;
import pageFactory.BusinessLogic;
import pageFactory.SearchPage;
import pageFactory.StartPage;
import support.ListTestData;
import support.PropertiesReader;
import support.TestData;

import static support.XmlTestData.ReadXml;
public class SuccessivelyCaseTest extends BaseTest{

    final static Logger logger = Logger.getLogger(FirstCaseTest.class);

    @DataProvider
    public Object[][] getDataRead(){
        PropertiesReader properties = new PropertiesReader();
        String path = properties.getInitialListData();
        ListTestData listTestData =  new ListTestData(ReadXml(path));
        return  listTestData.getListTestData()
                .stream()
                .map(a -> new Object[]{a.getProduct(),a.getBrand(),a.getMinPrice()})
                .toArray(Object[][]::new);
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

    @Test(dataProvider = "getData", description = "run successively test")
    public void checkHardData(String product, String brand, Integer minPrice) {
        testFlow(product, brand, minPrice);
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

}
