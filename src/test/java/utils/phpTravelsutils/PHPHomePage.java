package utils.phpTravelsutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PHPHomePage {
    Actions mouseDriver;
    private Select number;

    @FindBy(xpath = "//*[@id=\"s2id_autogen10\"]/a/span[1]")
    private WebElement clickPlaceSpan;
    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement searchBox;
    @FindBy(xpath = "//*[@id=\"dpd2\"]")
    private WebElement clickDateSpan;
    @FindBy(xpath = "//*[@id=\"dpd2\"]/input")
    private WebElement searchEndDate;
    @FindBy(xpath = "//*[@id=\"adults\"]")
    private WebElement noOfAdults;
    @FindBy(xpath = "//*[@id=\"HOTELS\"]/form/div[3]/div[3]/button")
    private WebElement submitButton;

    public void setSearchBox(String text) {
        clickPlaceSpan.click();
        searchBox.sendKeys(text);
        submitButton.click();
    }
    public void setSearchEndDate(String two) throws Exception {
        searchEndDate.clear();
        searchEndDate.sendKeys(two);

    }
    public void setNoOfAdults(int value){
        number = new Select(noOfAdults);
        number.selectByValue(Integer.toString(value));
    }
}
