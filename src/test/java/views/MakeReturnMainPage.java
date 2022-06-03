package views;

import com.opencsv.CSVReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;

public class MakeReturnMainPage {
    private WebDriver driver;
    String CSV_PATH = "./src/main/resources/data/returnFailedData.csv";
    private CSVReader csvReader;
    String[] csvCell;
    @FindBy(id = "TicketIdentifier")
    private WebElement eTicketIdInput;

    @FindBy(id = "CarPlate")
    private WebElement vehicleRegistrationNumberInput;

    @FindBy(id = "submitRefundForm")
    private WebElement makeReturnButton;

    public MakeReturnMainPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fulfillReturnForm() throws IOException, CsvValidationException
    {
        csvReader = new CSVReader(new FileReader(CSV_PATH));
        csvCell = csvReader.readNext();
        String ticketID = csvCell[0];
        String registerNo = csvCell[1];
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(eTicketIdInput));
        eTicketIdInput.click();
        eTicketIdInput.sendKeys(ticketID);
        vehicleRegistrationNumberInput.click();
        vehicleRegistrationNumberInput.sendKeys(registerNo);
    }
    public ReturnFailedPage clickReturnTicketButton()
    {
       makeReturnButton.click();
       return new ReturnFailedPage(driver);
    }
}