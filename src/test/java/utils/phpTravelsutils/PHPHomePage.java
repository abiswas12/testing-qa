package utils.phpTravelsutils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;

public class PHPHomePage {
    Actions mouseDriver;
    private Select number;

    @FindBy(xpath = "//*[@id=\"s2id_autogen10\"]/a/span[1]")
    private WebElement clickSpan;
    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement searchBox;
    @FindBy(xpath = "//*[@id=\"dpd2\"]/input")
    private WebElement searchEndDate;
    @FindBy(xpath = "//*[@id=\"adults\"]")
    private WebElement noOfAdults;

    public void setSearchBox(String text) {
        clickSpan.click();
        searchBox.sendKeys(text);
    }
    public void setSearchEndDate(String two) throws Exception {
        searchEndDate.clear();
        mouseDriver.moveToElement(searchEndDate).click();
        searchEndDate.sendKeys(two);
        searchEndDate.submit();
    }
    public void setNoOfAdults(int value){
        number = new Select(noOfAdults);
        number.selectByValue(Integer.toString(value));
    }
}
