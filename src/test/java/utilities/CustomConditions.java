package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {
    public static ExpectedCondition<Boolean> textToBeNot(WebElement element, String text) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return !element.getText().equals(text);
            }

            @Override
            public String toString() {
                return String.format("text to be not '%s'", text);
            }
        };
    }
}