package pageObject.decorElement;

import org.openqa.selenium.WebElement;

public class changeClick extends AbstractElement{
    public changeClick(WebElement webElement) {
        super(webElement);
    }

    public void newClick(){
        super.click();
    }
}
