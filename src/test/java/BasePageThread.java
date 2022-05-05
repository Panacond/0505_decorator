import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pageFactory.BucketPage;
import pageFactory.SearchPage;
import pageFactory.StartPage;
import pageFactory.WebDriverFactoryStaticThreadLocal;
import support.PropertiesReader;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BasePageThread {

    PropertiesReader properties = new PropertiesReader();
    @BeforeTest
    public void profileSetUp(){chromedriver().setup();}

    @BeforeMethod
    public void testSetUp(){
        WebDriverFactoryStaticThreadLocal.setDriver();
        WebDriverFactoryStaticThreadLocal.getDriver().manage().window().maximize();
        WebDriverFactoryStaticThreadLocal.getDriver().get(properties.getUrl());
    }

    @AfterMethod
    public void tearDown(){WebDriverFactoryStaticThreadLocal.closeBrowser();}

    public WebDriver getDriver(){return WebDriverFactoryStaticThreadLocal.getDriver();}

    public StartPage getStartPage(){return new StartPage(getDriver());}

    public SearchPage getSearchPage(){return new SearchPage(getDriver());}

    public BucketPage getBucketPage(){return new BucketPage(getDriver());}
}
