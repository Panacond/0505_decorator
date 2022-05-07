package pageObject.decorElement;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class RewriteWebElementFactory extends DecoratorWebElementFactory {
    final Logger logger = Logger.getLogger(Thread.currentThread().getName());

    public RewriteWebElementFactory(WebElement webElement) {
        super(webElement);
    }

    public void loggerClick(){
        webElement.click();
        logger.info("loggerClickPageFactory!");
    }
}
