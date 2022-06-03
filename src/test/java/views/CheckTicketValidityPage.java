package views;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class CheckTicketValidityPage {
    private WebDriver driver;
    private CSVReader csvReader;
    String[] csvCell;
    String CSV_PATH = "./src/main/resources/data/ticketValidityData.csv";
    @FindBy(id = "TicketIdentifier")
    private WebElement eTicketIdInput;
    @FindBy(id = "submitCheckForm")
    private WebElement checkTicketValidityButton;
    @FindBy(xpath= "//div[@class='message']")
    private WebElement validationText;

    public CheckTicketValidityPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
    public void fulfillTicketId() throws IOException, CsvValidationException
    {
        csvReader = new CSVReader(new FileReader(CSV_PATH));
        csvCell = csvReader.readNext();
        String ticketID = csvCell[0];
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(eTicketIdInput));
        eTicketIdInput.click();
        eTicketIdInput.sendKeys(ticketID);
        checkTicketValidityButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(validationText));
    }

    public String getValidationText()
    {
        return validationText.getText();
    }
}