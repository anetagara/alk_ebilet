package views;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BuyTicketFormPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Osobowe')]")
    private WebElement vehicleCarTypeDropdown;

    @FindBy(xpath = "//*[@id='startDate']/span/input")
    private WebElement startDateInput;

    @FindBy(id = "pr_id_2_label")
    private WebElement highwayTypeDropdown;

    @FindBy(xpath = "//span[@class='ng-star-inserted'][normalize-space()='A4']")
    private WebElement A4Highway;

    @FindBy(xpath = "(//span[@id='pr_id_3_label'])[1]")
    private WebElement startPointDropdown;

    @FindBy(xpath ="//li[@aria-label='Kleszczów']")
    private WebElement selectKleszczow;

    @FindBy(xpath ="//li[@aria-label='Konin Wschód']")
    private WebElement selectKonin;

    @FindBy(xpath = "(//span[@id='pr_id_4_label'])[1]")
    private WebElement endPointDropdown;

    @FindBy(xpath ="//li[@aria-label='Gliwice Sośnica']")
    private WebElement selectGliwieSosnica;

    @FindBy(xpath = "//span[normalize-space()='Oblicz']")
    private WebElement calculateButton;

    @FindBy(xpath = "//p[contains(text(),'Węzeł końcowy trasy nie może być taki sam jak węze')]")
    private WebElement sameNodesError;

    @FindBy(xpath = "//p[contains(text(),'Wypełnij wymagane pola oraz wybierz punkt startowy')]")
    private WebElement emptyNodesError;

    @FindBy(xpath = "//p[contains(text(),'Trasa nie zawiera odcinków płatnych zarządzanych przez GDDKiA')]")
    private WebElement freeRouteError;

    @FindBy(xpath = "//div[@class='ngx-spinner-overlay ng-tns-c6-0 ng-trigger ng-trigger-fadeIn ng-star-inserted']")
    private WebElement spinner;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    LocalDateTime now = LocalDateTime.now();
    private String today = dtf.format(now);

    public BuyTicketFormPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillTicketForFreeRoute()
    {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOf(spinner));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(startDateInput));
        startDateInput.click();
        startDateInput.sendKeys(today);
        startDateInput.sendKeys(Keys.ESCAPE);
        highwayTypeDropdown.click();
        A4Highway.click();
        startPointDropdown.click();
        selectKleszczow.click();
        endPointDropdown.click();
        selectGliwieSosnica.click();
        calculateButton.click();
    }

    public String getFreeRouteError()
    {
        return freeRouteError.getText();
    }

    public void fillTicketForSameNodes()
    {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOf(spinner));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(startDateInput));
        startDateInput.click();
        startDateInput.sendKeys(today);
        startDateInput.sendKeys(Keys.ESCAPE);
        startPointDropdown.click();
        selectKonin.click();
        endPointDropdown.click();
        selectKonin.click();
        calculateButton.click();
    }

    public String getSameNodesError()
    {
        return sameNodesError.getText();
    }

    public void fillTicketForEmptyNodes()
    {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOf(spinner));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(startDateInput));
        startDateInput.click();
        startDateInput.sendKeys(today);
        startDateInput.sendKeys(Keys.ESCAPE);
        calculateButton.click();
    }
    public String getEmptyNodesError()
    {
        return emptyNodesError.getText();
    }

    public String getVehicleType()
    {
        return vehicleCarTypeDropdown.getText();
    }

    public String getHighwayType()
    {
        return highwayTypeDropdown.getText();
    }
}