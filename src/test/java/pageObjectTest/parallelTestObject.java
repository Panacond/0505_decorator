package pageObjectTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.BusinessLogicObject;
import support.ListTestData;
import support.PropertiesReader;

import static support.XmlTestData.ReadXml;

public class parallelTestObject extends BaseTestObject{


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
        return selectItems(0,1);
    }

    @Test(dataProvider = "getDataRead1", description = "run successively test")
    public void checkFlowData1(String product, String brand, Integer minPrice) {
        testFlow(product, brand, minPrice);
    }

    @DataProvider()
    public Object[][] getDataRead2() {
        return selectItems(1,2);
    }

    @Test(dataProvider = "getDataRead2", description = "run successively test")
    public void checkFlowData2(String product, String brand, Integer minPrice) {
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
        new BusinessLogicObject()
                .oneTestSearchProduct(super.driver, product);
        new BusinessLogicObject()
                .addExpensiveProductToBucket(super.driver, brand);
        new BusinessLogicObject().checkDataPrice(super.driver, minPrice);
    }
}
