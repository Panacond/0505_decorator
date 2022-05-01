package pageFactory.decorElement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class CheckBox extends AbstractElement{
    static Logger logger = LogManager.getLogger(CheckBox.class);
    public CheckBox(WebElement webElement) {
        super(webElement);
    }
    public void setChecked(){
        if(!webElement.isSelected()){
            super.click();
        } else{
            logger.info("Element is selected" + webElement.isSelected() + webElement.getTagName());
        }
    }
}
