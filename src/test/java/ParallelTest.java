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

public class ParallelTest extends BasePageThread {

    @DataProvider(parallel = true)
    public Object[][] selectItems() {
        PropertiesReader properties = new PropertiesReader();
        String path = properties.getInitialListData();
        ListTestData listTestData =  new ListTestData(ReadXml(path));
        return  listTestData.getListTestData()
                .stream()
                .map(a -> new Object[]{a.getProduct(),a.getBrand(),a.getMinPrice()})
                .toArray(Object[][]::new);
    }


    @Test(dataProvider = "selectItems" )
    public void testFlow(String product, String brand, Integer minPrice) throws InterruptedException {
        StartPage startPage = getStartPage();
        startPage.searchByKeyword(product);
        TestData testData = new TestData(product, brand, minPrice);
        SearchPage searchPage = getSearchPage();
        new BusinessLogic().addToBucketExpensiveElement(searchPage, brand);
        BucketPage bucketPage = getBucketPage();
        new BusinessLogic().CheckProduct(bucketPage, testData);
    }

    @DataProvider(parallel = true)
    public Object[][] data(){
        return new Object[][] {
                {"ноутбук", "MSI", 5000}
                ,{"стиральная машина", "Samsung", 14000}
                ,{"посудомоечная машина", "Bosch", 50000}
                ,{"смартфон", "Xiaomi", 17000}
        };
    }

    @Test(dataProvider = "data", description = "run successively test")
    public void testFlowThree(String product, String brand, Integer minPrice) throws InterruptedException {
        StartPage startPage = getStartPage();
        startPage.searchByKeyword(product);
        TestData testData = new TestData(product, brand, minPrice);
        SearchPage searchPage = getSearchPage();
        new BusinessLogic().addToBucketExpensiveElement(searchPage, brand);
        BucketPage bucketPage = getBucketPage();
        new BusinessLogic().CheckProduct(bucketPage, testData);
    }

}
