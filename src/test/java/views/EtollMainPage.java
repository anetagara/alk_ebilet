package views;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EtollMainPage {
    private WebDriver driver;
    @FindBy(xpath = "//span[normalize-space()='Kup e-bilet autostradowy']")
    private WebElement eTicketStoreButton;

    public EtollMainPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
    public EtollMainPage openPage()
    {
        driver.get("https://etoll.gov.pl/");
        return new EtollMainPage(driver);
    }
    public void checkAndCloseCookieBar()
    {
        Cookie cookie = new Cookie("cookieBubble", "true");
        driver.manage().addCookie(cookie);
    }
    public BuyTicketMainPage clickETicketStoreButton()
    {
        eTicketStoreButton.click();
        return new BuyTicketMainPage(driver);
    }
}