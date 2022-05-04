package pageFactory;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import support.PropertiesReader;
import support.TestData;

import java.util.List;

import static support.XmlTestData.WriteXml;

public class BusinessLogic {
    PropertiesReader properties = new PropertiesReader();

    public void addToBucketExpensiveElement(SearchPage searchPage, String brand) throws InterruptedException {
        searchPage.clickCheckBoxMsi(brand);
        Thread.sleep(2000);
        searchPage.clickPopUp();
        searchPage.clickPopUpExpensive();
        Thread.sleep(2000);
        List<WebElement> listAddToBucket =  searchPage.getListAddToBucket();
        Thread.sleep(2000);
        listAddToBucket.get(0).click();
        searchPage.clickGoToBucket();
    }

    public void addToBucketExpensiveElementNoThreadSleep(SearchPage searchPage, String brand) {
        searchPage.clickCheckBoxMsi(brand);
        searchPage.clickPopUp();
        searchPage.clickPopUpExpensive();
        List<WebElement> listAddToBucket =  searchPage.getListAddToBucket();
        listAddToBucket.get(0).click();
        searchPage.clickGoToBucket();
    }

    public void CheckProduct(BucketPage bucketPage, TestData testData){
        Integer price = bucketPage.getStringPrice();
        testData.setRealPrice(price);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(price > testData.getMinPrice(), "Price in page low data price" + price + ">" + testData.getMinPrice() + "???");
        softAssert.assertAll();
        WriteXml(testData, properties.getResultListData());
    }
}
