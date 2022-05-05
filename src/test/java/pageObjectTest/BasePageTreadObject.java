package pageObjectTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import support.PropertiesReader;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BasePageTreadObject {
    protected WebDriver driver;
    PropertiesReader properties = new PropertiesReader();

    @BeforeTest
    public void profileSetUp(){chromedriver().setup();}

    @BeforeMethod
    public void testSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(properties.getUrl());
    }

    @AfterMethod
    public void tearDown(){driver.quit();}
}
