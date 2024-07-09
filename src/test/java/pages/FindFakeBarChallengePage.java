package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

@Getter
public class FindFakeBarChallengePage {

    public FindFakeBarChallengePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "weigh")
    private WebElement weightButton;

    @FindBy(xpath= "//button[text()='Reset']")
    private WebElement resetButton;

    @FindBy(xpath = "//div[text()='left']/..//input")
    private List<WebElement> leftBowlCells;

    @FindBy(xpath = "//div[text()='right']/..//input")
    private List<WebElement> rightBowlCells;

    @FindBy(xpath = "//div/div[text()='Result']/following-sibling::button")
    private WebElement result;

    @FindBy(xpath = "//div[@class='coins']/button")
    private List<WebElement> goldenBar;

    @FindBy(xpath = "//div/ol/li")
    private List<WebElement> weightings;
    public void clickWeighButton() {
        weightButton.click();
    }
    public void clickResetButton() {
        resetButton.click();
    }

}

