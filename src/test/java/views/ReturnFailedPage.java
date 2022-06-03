package views;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReturnFailedPage {
    private WebDriver driver;
    @FindBy(xpath = "//p[@class='p1-bold']")
    private WebElement alertError;

    @FindBy(xpath = "//a[@class='p1-link']")
    private WebElement goToLink;
    public ReturnFailedPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public void waitForAlert()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(alertError));
    }

    public String getErrorAlertText()
    {
        return alertError.getText();
    }

    public ContactFormPage clickGoToButton()
    {
        goToLink.click();
        return new ContactFormPage (driver);
    }
}