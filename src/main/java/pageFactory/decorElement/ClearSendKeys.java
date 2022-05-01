package pageFactory.decorElement;

import org.openqa.selenium.WebElement;

public class ClearSendKeys extends AbstractElement {

    public ClearSendKeys(WebElement webElement){
        super(webElement);
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        webElement.clear();
        super.sendKeys(keysToSend);
    }



}
