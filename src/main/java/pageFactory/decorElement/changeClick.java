package pageFactory.decorElement;

import org.openqa.selenium.WebElement;

public class changeClick extends AbstractElement{
    public changeClick(WebElement webElement) {
        super(webElement);
    }
    public void tenClick(){
        for (int i = 0; i < 10; i++) super.click();
    }

}
