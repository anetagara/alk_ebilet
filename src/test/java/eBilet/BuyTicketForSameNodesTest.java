package eBilet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import views.BuyTicketFormPage;
import views.BuyTicketMainPage;
import views.EtollMainPage;

import java.time.Duration;

public class BuyTicketForSameNodesTest {
    public WebDriver driver;
    public EtollMainPage etollMainPage;
    public BuyTicketMainPage buyTicketMainPage;
    public BuyTicketFormPage buyTicketFormPage;

    @BeforeClass
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(description = "Test próby zakupu biletu dla takich samych węzłów")
    public void buyTicketForSameNodes()
    {
        //1. Otwórz stronę etoll.gov.pl, zaakceptuj ciasteczka i kliknij przycisk 'Kup e-bilet autostradowy'
        etollMainPage = new EtollMainPage(driver).openPage();
        etollMainPage.checkAndCloseCookieBar();
        buyTicketMainPage = etollMainPage.clickETicketStoreButton();

        //2. Kliknij kup e-bilet autostradowy
        buyTicketFormPage = buyTicketMainPage.clickBuyTicketButton();

        //3. Sprawdź wartości domyślne formularza i uzupełnij formularz wybierając taki sam węzeł początkowy i końcowy trasy i kliknij 'Oblicz'
        Assert.assertEquals(buyTicketFormPage.getVehicleType(), "Osobowe");
        Assert.assertEquals(buyTicketFormPage.getHighwayType(), "A2");
        buyTicketFormPage.fillTicketForSameNodes();

        //4. Sprawdź poprawność komunikatu o błędzie
        String expectedSameNodesAlertText = "Węzeł końcowy trasy nie może być taki sam jak węzeł początkowy trasy.";
        Assert.assertEquals(buyTicketFormPage.getSameNodesError(),expectedSameNodesAlertText);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}