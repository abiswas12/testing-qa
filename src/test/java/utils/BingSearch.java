package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BingSearch {
    @FindBy(name = "q")
    private WebElement searchBox;

    public void setSearchBox(String text){
        searchBox.sendKeys(text);
        searchBox.submit();
    }
}
