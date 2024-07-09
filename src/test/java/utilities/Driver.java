package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver != null) {
            return driver;
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

}
