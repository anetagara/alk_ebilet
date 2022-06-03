package eBilet;

import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import views.*;

import java.io.IOException;
import java.time.Duration;

public class MakeReturnTest {
    public WebDriver driver;
    public EtollMainPage etollMainPage;
    public BuyTicketMainPage buyTicketMainPage;
    public MakeReturnMainPage makeReturnMainPage;
    public ReturnFailedPage returnFailedPage;
    public ContactFormPage contactFormPage;

    @BeforeClass
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(description = "Wprowadzenie nieistniejącego identyfikatora biletu, kliknięcie 'Dokonaj zwrotu' i przejście do formularza kontaktowego")
    public void makeReturnInvalidTest() throws IOException, CsvValidationException
    {
        //1. Otwórz stronę etoll.gov.pl, zaakceptuj ciasteczka i wybierz sklep e-bilet autostradowy
        etollMainPage = new EtollMainPage(driver).openPage();
        etollMainPage.checkAndCloseCookieBar();
        buyTicketMainPage = etollMainPage.clickETicketStoreButton();

        //2. Kliknij Dokanaj zwrotu
        makeReturnMainPage = buyTicketMainPage.clickReturnTicketButton();

        //3. Wprowadź nieistniejący identyfikator biletu oraz numer rejestracyjny pojazdu i kliknij Dokonaj zwrotu
        makeReturnMainPage.fulfillReturnForm();
        returnFailedPage = makeReturnMainPage.clickReturnTicketButton();

        //4. Sprawdź czy wyświetlił się właściwy komunikat błędu
        returnFailedPage.waitForAlert();
        String expectedErrorAlertText = "Nie udało się dokonać zwrotu. Skontaktuj się z Biurem Obsługi Klienta. Przejdź";
        Assert.assertEquals(returnFailedPage.getErrorAlertText(), expectedErrorAlertText);

        //5. Przejdź do formularza kontaktowego
        contactFormPage = returnFailedPage.clickGoToButton();
        Assert.assertEquals(contactFormPage.getContactFormText(), "Formularz kontaktowy");
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}