package views;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuyTicketMainPage {
    private WebDriver driver;

    @FindBy(xpath= "//a[@class='btn btn-primary'][normalize-space()='Kup e-bilet autostradowy']")
    private WebElement buyTicketbutton;

    @FindBy(xpath = "//a[@href='/lekkie/e-bilet-autostradowy/e-bilet/dokonaj-zwrotu/']")
    private WebElement returnTicketButton;

    @FindBy(xpath = "//a[@href='/lekkie/e-bilet-autostradowy/e-bilet/sprawdz-waznosc-biletu/']")
    private WebElement checkTicketValidityButton;

    public BuyTicketMainPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public BuyTicketFormPage clickBuyTicketButton()
    {
        buyTicketbutton.click();
        return new BuyTicketFormPage(driver);
    }
    public MakeReturnMainPage clickReturnTicketButton()
    {
        returnTicketButton.click();
        return new MakeReturnMainPage(driver);
    }
    public CheckTicketValidityPage clickCheckTicketValidityButton()
    {
        checkTicketValidityButton.click();
        return new CheckTicketValidityPage(driver);
    }
}