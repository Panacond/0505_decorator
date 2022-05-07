package pageFactory.Decorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class RewriteWebElement extends DecoratorWebElement{
    final Logger logger = Logger.getLogger(Thread.currentThread().getName());


    public RewriteWebElement(WebElement webElement) {
        super(webElement);
    }

    public void loggerClick(){
        webElement.click();
        logger.info("loggerClickPageObject!");
    }
}
