package eBilet;

import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import views.BuyTicketMainPage;
import views.CheckTicketValidityPage;
import views.EtollMainPage;

import java.io.IOException;
import java.time.Duration;
public class TicketValidityTest {
    public WebDriver driver;
    public EtollMainPage etollMainPage;
    public BuyTicketMainPage buyTicketMainPage;
    public CheckTicketValidityPage checkTicketValidityPage;

    @BeforeClass
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(description = "Sprawdzenie ważności nieistniejącego biletu")
    public void checkTicketValidity() throws IOException, CsvValidationException
    {
        //1. Otwórz stronę etoll.gov.pl, zaakceptuj cisateczka i wybierz sklep e-bilet autostradowy
        etollMainPage = new EtollMainPage(driver).openPage();
        etollMainPage.checkAndCloseCookieBar();
        buyTicketMainPage = etollMainPage.clickETicketStoreButton();

        //2. Kliknij Sprawdź ważność biletu
         checkTicketValidityPage = buyTicketMainPage.clickCheckTicketValidityButton();

        //3. Uzupełnij identyfikator nieistniejącego ebiletu i kliknij Sprawdź ważność biletu
        checkTicketValidityPage.fulfillTicketId();

        //4. Sprawdź poprawność komunikatu o błędzie
        String expectedValidationText = "Bilet nieważny. Zwrot niemożliwy.";
        Assert.assertEquals(checkTicketValidityPage.getValidationText(), expectedValidationText);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}