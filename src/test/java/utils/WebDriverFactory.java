package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {
    public static WebDriver getWebDriver(String browser) {
        if ("chrome".equalsIgnoreCase(browser)) {
            return new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            return new FirefoxDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            return new EdgeDriver();
        } else if ("ie".equalsIgnoreCase(browser)) {
            return new InternetExplorerDriver();
        } else {
            return new ChromeDriver();
        }
    }
}
