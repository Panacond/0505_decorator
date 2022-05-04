package pageObject.decorElement;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;


public class ChangeClick extends AbstractElement{

    WebElement webElement;
    final static Logger logger = Logger.getLogger(ChangeClick.class);
    public ChangeClick(WebElement webElement) {
        super(webElement);
    }

    public ChangeClick() {
        super();
    }

    public void newClick(){
        super.click();
        logger.info("Ckick work!");

    }
}
