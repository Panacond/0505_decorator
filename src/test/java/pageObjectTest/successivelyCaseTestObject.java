package pageObjectTest;

import org.testng.annotations.Test;
import pageObject.BusinessLogicObject;
import support.PropertiesReader;
import support.TestData;
import support.XmlTestData;


public class successivelyCaseTestObject extends BaseTestObject{
    PropertiesReader properties = new PropertiesReader();
    TestData testData = XmlTestData.ReadXml(properties.getInitialData());

    @Test(description = "run one test from file testData.xml")
    public void checkExpensiveGoods() {
        new BusinessLogicObject()
            .oneTestSearchProduct(super.driver, testData.getProduct());
        new BusinessLogicObject()
            .addExpensiveProductToBucket(super.driver, testData.getBrand());
        new BusinessLogicObject().checkData(super.driver, testData);
    }
}
