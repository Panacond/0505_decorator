import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.BucketPage;
import pageFactory.BusinessLogic;
import pageFactory.SearchPage;
import pageFactory.StartPage;
import support.ListTestData;
import support.PropertiesReader;
import support.TestData;
import static support.XmlTestData.ReadXml;

public class ParallelCaseTest extends BaseTest {

    private static Object[][] selectItems(int start, int finish) {
        PropertiesReader properties = new PropertiesReader();
        String path = properties.getInitialListData();
        ListTestData listTestData =  new ListTestData(ReadXml(path));
        return listTestData.getListTestData()
                .stream()
                .skip(start).limit(finish-start)
                 .map(a -> new Object[]{a.getProduct(),a.getBrand(),a.getMinPrice()})
                .toArray(Object[][]::new);
    }

    @DataProvider()
    public Object[][] getDataRead1() {
        return selectItems(0,2);
    }

    @Test(dataProvider = "getDataRead1", description = "run successively test")
    public void checkFlowData1(String product, String brand, Integer minPrice) throws InterruptedException {
        testFlow(product, brand, minPrice);
    }

    @DataProvider()
    public Object[][] getDataRead2() {
        return selectItems(2,4);
    }

    @Test(dataProvider = "getDataRead2", description = "run successively test")
    public void checkFlowData2(String product, String brand, Integer minPrice) throws InterruptedException {
        testFlow(product, brand, minPrice);
    }

    @DataProvider()
    public Object[][] getDataRead3() {
        return selectItems(4,5);
    }

    @Parameters({ "product", "brand", "minPrise" })
    @Test(dataProvider = "getDataRead3", description = "run successively test")
    public void checkFlowData3(String product, String brand, Integer minPrice) throws InterruptedException {
        testFlow(product, brand, minPrice);
    }

    private void testFlow(String product, String brand, Integer minPrice) throws InterruptedException {
        StartPage startPage = getStartPage();
        startPage.searchByKeyword(product);
        TestData testData = new TestData(product, brand, minPrice);
        SearchPage searchPage = getSearchPage();
        new BusinessLogic().addToBucketExpensiveElement(searchPage, brand);
        BucketPage bucketPage = getBucketPage();
        new BusinessLogic().CheckProduct(bucketPage, testData);
    }

}
