package utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHandler {
    public static String handleAlert(WebDriver driver, Duration timeout) {

        WebDriverWait wait = new WebDriverWait(driver, timeout);

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        String alertMessage = alert.getText();

        System.out.println("Result message: " + alertMessage);

        alert.accept();

        return alertMessage;
    }
}
