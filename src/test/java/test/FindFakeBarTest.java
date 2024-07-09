package test;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FindFakeBarChallengePage;
import utilities.AlertHandler;
import utilities.CustomConditions;
import utilities.Driver;

import java.time.Duration;

public class FindFakeBarTest {

    public static void main(String[] args) {
        WebDriver driver = Driver.getDriver();
        driver.get("http://sdetchallenge.fetch.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        FindFakeBarChallengePage fakeBarChallengePage = new FindFakeBarChallengePage();

        performWeighing(fakeBarChallengePage, wait, new int[]{0, 1, 2}, new int[]{3, 4, 5});
        handleWeighingResult(fakeBarChallengePage, wait, driver);

        driver.quit();
    }

    private static void performWeighing(FindFakeBarChallengePage page, WebDriverWait wait, int[] leftBars, int[] rightBars) {
        page.clickResetButton();

        for (int i = 0; i < leftBars.length; i++) {
            page.getLeftBowlCells().get(i).sendKeys(String.valueOf(leftBars[i]));
        }
        for (int i = 0; i < rightBars.length; i++) {
            page.getRightBowlCells().get(i).sendKeys(String.valueOf(rightBars[i]));
        }

        page.clickWeighButton();
        wait.until(ExpectedConditions.visibilityOf(page.getResult()));
        wait.until(CustomConditions.textToBeNot(page.getResult(), "?"));
    }

    private static void handleWeighingResult(FindFakeBarChallengePage page, WebDriverWait wait, WebDriver driver) {
        String result = page.getResult().getText();

        switch (result) {
            case "=" -> {
                performWeighing(page, wait, new int[]{6}, new int[]{7});
                handleFinalResult(page, driver, 8, 7, 6);
            }
            case "<" -> {
                performWeighing(page, wait, new int[]{0}, new int[]{1});
                handleFinalResult(page, driver, 2, 1, 0);
            }
            case ">" -> {
                performWeighing(page, wait, new int[]{3}, new int[]{4});
                handleFinalResult(page, driver, 5, 4, 3);
            }
        }
    }

    private static void handleFinalResult(FindFakeBarChallengePage page, WebDriver driver, int equalIndex, int greaterIndex, int lessIndex) {
        String result = page.getResult().getText();

        if (result.equals("=")) {
            clickAndAssert(page, driver, equalIndex);
        } else if (result.equals("<")) {
            clickAndAssert(page, driver, lessIndex);
        } else if (result.equals(">")) {
            clickAndAssert(page, driver, greaterIndex);
        }

        getListOfWeighings(page,driver);
    }

    private static void clickAndAssert(FindFakeBarChallengePage page, WebDriver driver, int index) {
        page.getGoldenBar().get(index).click();
        String alertMessage = AlertHandler.handleAlert(driver, Duration.ofSeconds(10));
        Assert.assertEquals("Yay! You find it!", alertMessage);

    }

    private static void getListOfWeighings(FindFakeBarChallengePage page, WebDriver driver){
        for (int i = 0; i < page.getWeightings().size(); i++) {
            System.out.println(page.getWeightings().get(i).getText());
        }
    }
}
